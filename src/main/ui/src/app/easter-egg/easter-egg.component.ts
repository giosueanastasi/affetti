import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-easter-egg',
  templateUrl: './easter-egg.component.html',
  styleUrls: ['./easter-egg.component.css']
})
export class EasterEggComponent implements OnInit, OnDestroy {

  confetti: { left: number; delay: number; duration: number; emoji: string }[] = [];
  private confettiInterval: any;

  readonly partyEmojis = ['🎉', '🥳', '🎊', '🪦', '⚰️', '💀', '👻', '🎈', '🏆', '💐', '🕯️', '☠️', '🦴', '🎶'];

  readonly gifs = [
    'https://media.giphy.com/media/l0MYt5jPR6QX5pnqM/giphy.gif',
    'https://media.giphy.com/media/26tOZ42Mg6pbTUPHW/giphy.gif',
    'https://media.giphy.com/media/s2qXK8wAvkHTO/giphy.gif',
    'https://media.giphy.com/media/l46CbZ7KWEhN1oCIg/giphy.gif',
    'https://media.giphy.com/media/3oz8xAFtqoOUUrsh7W/giphy.gif',
    'https://media.giphy.com/media/l0HlBO7eyXzSZkJri/giphy.gif'
  ];

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.spawnConfetti();
    this.confettiInterval = setInterval(() => this.spawnConfetti(), 3000);
  }

  ngOnDestroy(): void {
    if (this.confettiInterval) {
      clearInterval(this.confettiInterval);
    }
  }

  goBack(): void {
    this.router.navigate(['/pitty-in']);
  }

  private spawnConfetti(): void {
    const batch = [];
    for (let i = 0; i < 20; i++) {
      batch.push({
        left: Math.random() * 100,
        delay: Math.random() * 2,
        duration: 3 + Math.random() * 4,
        emoji: this.partyEmojis[Math.floor(Math.random() * this.partyEmojis.length)]
      });
    }
    this.confetti = batch;
  }
}
