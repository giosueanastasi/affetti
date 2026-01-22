import { Component, Output, EventEmitter, AfterViewInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import * as L from 'leaflet';
import { icon, Marker } from 'leaflet';

declare var $: any;

// Icone marker Leaflet con webpack
const iconRetinaUrl = 'assets/leaflet/marker-icon-2x.png';
const iconUrl = 'assets/leaflet/marker-icon.png';
const shadowUrl = 'assets/leaflet/marker-shadow.png';
const iconDefault = icon({
  iconRetinaUrl,
  iconUrl,
  shadowUrl,
  iconSize: [25, 41],
  iconAnchor: [12, 41],
  popupAnchor: [1, -34],
  tooltipAnchor: [16, -28],
  shadowSize: [41, 41]
});
Marker.prototype.options.icon = iconDefault;

interface NominatimResult {
  lat: string;
  lon: string;
  display_name: string;
  address?: {
    city?: string;
    town?: string;
    village?: string;
    province?: string;
    postcode?: string;
  };
}

@Component({
  selector: 'app-coordinate-map-modal',
  templateUrl: './coordinate-map-modal.component.html',
  styleUrls: ['./coordinate-map-modal.component.css']
})
export class CoordinateMapModalComponent implements AfterViewInit {
  @Input() readOnly: boolean = false;
  @Output() save = new EventEmitter<{latitudine: number, longitudine: number}>();

  map!: L.Map;
  marker?: L.Marker;
  selectedLat: number | null = null;
  selectedLng: number | null = null;
  searchQuery: string = '';
  searchResults: NominatimResult[] = [];
  isSearching: boolean = false;
  private searchSubject = new Subject<string>();

  // Opzioni Leaflet ottimizzate per performance
  options = {
    layers: [
      L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '© OpenStreetMap contributors',
        updateWhenIdle: true,
        updateWhenZooming: false,
        keepBuffer: 2
      })
    ],
    zoom: 13,
    center: L.latLng(41.9028, 12.4964),
    preferCanvas: true,
    zoomControl: true
  };

  constructor(private http: HttpClient) {
    // Configura la ricerca con debounce di 300ms
    this.searchSubject.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap(query => {
        if (!query || query.trim().length < 2) {
          this.searchResults = [];
          this.isSearching = false;
          return [];
        }

        this.isSearching = true;

        const url = `https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(query)}&countrycodes=it&addressdetails=1&limit=10`;

        return this.http.get<NominatimResult[]>(url, {
          headers: {
            'User-Agent': 'Affetti Cemetery Management'
          }
        });
      })
    ).subscribe({
      next: (results) => {
        this.searchResults = results;
        this.isSearching = false;
      },
      error: (error) => {
        console.error('Errore nella ricerca:', error);
        this.searchResults = [];
        this.isSearching = false;
      }
    });
  }

  ngAfterViewInit() {
    // Intercetta TUTTE le chiusure della modale (X, click fuori, ESC, programmatica)
    $('#coordinateModal').on('hidden.bs.modal', () => {
      this.cleanupModal();
    });
  }

  onMapReady(map: L.Map) {
    this.map = map;

    // Listener per click sulla mappa (solo se non è readOnly)
    this.map.on('click', (e: L.LeafletMouseEvent) => {
      if (!this.readOnly) {
        this.selectCoordinate(e.latlng.lat, e.latlng.lng);
      }
    });
  }

  selectCoordinate(lat: number, lng: number) {
    this.selectedLat = lat;
    this.selectedLng = lng;

    // Rimuovi marker esistente se presente
    if (this.marker) {
      this.map.removeLayer(this.marker);
    }

    // Aggiungi nuovo marker
    this.marker = L.marker([lat, lng]).addTo(this.map);
  }

  showMapModal(initialLat?: number, initialLng?: number, readOnly: boolean = false) {
    this.readOnly = readOnly;
    $('#coordinateModal').modal('show');

    // Attendi che la modale sia completamente visibile, poi ridimensiona la mappa
    setTimeout(() => {
      if (this.map) {
        this.map.invalidateSize(); // Fix per mappa in modale nascosta

        // Se ci sono coordinate iniziali, centra la mappa e aggiungi marker
        if (initialLat && initialLng) {
          this.map.setView([initialLat, initialLng], 15);
          this.selectCoordinate(initialLat, initialLng);
        }
      }
    }, 300);
  }

  // Chiamato quando l'utente digita nell'input
  onSearchInput(query: string) {
    this.searchSubject.next(query);
  }

  // Vai alla località selezionata dai risultati
  goToLocation(result: NominatimResult) {
    const lat = parseFloat(result.lat);
    const lng = parseFloat(result.lon);

    if (this.map) {
      this.map.setView([lat, lng], 16);
      this.selectCoordinate(lat, lng);
    }

    // Imposta il testo della località selezionata e chiudi i suggerimenti
    this.searchQuery = result.display_name;
    this.searchResults = [];
  }

  saveCoordinates() {
    if (this.selectedLat !== null && this.selectedLng !== null) {
      this.save.emit({
        latitudine: this.selectedLat,
        longitudine: this.selectedLng
      });
      $('#coordinateModal').modal('hide');
    }
  }

  private cleanupModal() {
    // Reset search
    this.searchQuery = '';
    this.searchResults = [];

    // Fix per rimuovere backdrop e body class che rimangono dopo la chiusura
    $('.modal-backdrop').remove();
    $('body').removeClass('modal-open');
    $('body').css('padding-right', '');
  }
}
