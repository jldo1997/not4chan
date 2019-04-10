import { Component, OnInit } from '@angular/core';
import { UserResponse } from 'src/app/interfaces/users-response.interface';
import { UserService } from 'src/app/services/user.service';
import { user } from 'src/app/model/user';
import { MatDialog } from '@angular/material';
import { UserDeleditComponent } from '../user-deledit/user-deledit.component';
import { UserNewComponent } from '../user-new/user-new.component';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.scss']
})
export class UserTableComponent implements OnInit {
  rows:  user[];
  selected = [];
  temp: UserResponse;
  

  constructor(private titleService: Title, private userService:UserService, private dialog: MatDialog) { }

  ngOnInit() {
    this.getAllUsers();
    this.titleService.setTitle('3chan admin panel - List of users');
  }

  getAllUsers() {
    this.userService.getAll().subscribe(listUsers => {
      this.temp = listUsers;
      this.rows = this.temp.rows;
      console.log(this.rows);
    }, error =>  {
      console.log(error);
    });
  }

  openDialogNewAdminUser() {
    const dialogoNewProduct = this.dialog.open(UserNewComponent);

    dialogoNewProduct.afterClosed().subscribe(result => {
      this.getAllUsers();
    });
  }

  openDialogDeledit(selected: user) {
    const dialogEdit = this.dialog.open(UserDeleditComponent, {
      data :{'recurso': selected }
    });
    
    dialogEdit.afterClosed().subscribe(result =>{
    this.getAllUsers();
  });
  }

  onSelect({ selected }) {
    this.openDialogDeledit(selected[0]);
}

updateFilter(event) {
}

}
