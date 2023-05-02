import { Component, EventEmitter, Input, Output } from '@angular/core';
import { User } from 'src/app/app-state/models';
import { AppService } from 'src/app/app.service';


declare var $ : any;

@Component({
  selector: 'app-user-model',
  templateUrl: './user-model.component.html',
  styleUrls: ['./user-model.component.css']
})
export class UserModelComponent {


  @Input() user: User = new User();
  @Output() save = new EventEmitter<any>();

  constructor(private appService: AppService) { }

  showUserModal(){
    $('#userModal').modal('show');
  }

  saveUser() {
    debugger;
    this.appService.saveContraente(this.user).pipe().subscribe(data => {
      this.save.emit(data);
      $('#userModal').modal('hide');
    });
  }
}
