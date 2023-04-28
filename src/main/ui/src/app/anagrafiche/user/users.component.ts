import { Component, EventEmitter, Input, OnDestroy, OnInit, Output, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from '../../app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { User } from 'src/app/app-state/models';
import { UserModelComponent } from '../user-model/user-model.component';



@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit, OnDestroy {

  selectedUser: User = new User();

  constructor(private appService: AppService) {}

  title = 'angular-nodejs-example';

  @ViewChild(UserModelComponent) child: UserModelComponent | undefined;

  userForm = new FormGroup({
    username: new FormControl('', Validators.nullValidator && Validators.required),
    password: new FormControl('', Validators.nullValidator && Validators.required),
    ruolo: new FormControl('', Validators.nullValidator && Validators.required),
    fk_comune: new FormControl('', Validators.nullValidator && Validators.required)
  });

  users: any[] = [];
  userCount = 0;

  destroy$: Subject<boolean> = new Subject<boolean>();

  // onSubmit() {
  //   this.appService.addUser(this.userForm.value, this.userCount + 1).pipe(takeUntil(this.destroy$)).subscribe(data => {
  //     console.log('message::::', data);
  //     this.userCount = this.userCount + 1;
  //     console.log(this.userCount);
  //     this.userForm.reset();
  //     this.getAllUsers();
  //   });
  // }

  cercaUser() {
    this.appService.cercaUser(this.userForm.value).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
      this.users = data.users;
    });
  }

  createUserRequest(){
    this.selectedUser = new User();
    this.child?.showUserModal();
  }

  editUserRequest(item: User){
    this.selectedUser = Object.assign({},item);
    this.child?.showUserModal();
  }




  getAllUsers() {
    this.appService.getUsers().pipe(takeUntil(this.destroy$)).subscribe((users: any[]) => {
		    this.userCount = users.length;
        this.users = users;
    });
  }

  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }
  
/*
  @Input() users: any[];
*/
  ngOnInit() {
    console.log('esegui all user on init');
    this.getAllUsers();
    }

    saveUserWatcher(user: User){
      let userIndex = this.users.findIndex(item => item.username === user.username);
      if(userIndex !==-1){
        this.users[userIndex] = user;
      }else{
        this.users.push(user);
      }
    }
}
