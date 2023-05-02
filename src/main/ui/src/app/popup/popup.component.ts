import { Component } from '@angular/core';

declare var $ : any;
@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.css']
})
export class PopupComponent {
  showPopupModal(){
    $('#popUpModal').modal('show');
  }
}
