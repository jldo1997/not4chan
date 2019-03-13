import { Component, OnInit } from '@angular/core';
import { UserResponse } from 'src/app/interfaces/users-response.interface';
import { UserService } from 'src/app/services/user.service';
import { user } from 'src/app/model/user';

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.scss']
})
export class UserTableComponent implements OnInit {
  rows:  user[];
  selected = [];
  temp: UserResponse;
  

  constructor(private userService:UserService) { }

  ngOnInit() {
    this.getAllUsers();
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

  openDialogNewProduct() {
  }

  openDialogEdit(selected: user) {
    console.log(selected);
    
  }

  onSelect({ selected }) {
    this.openDialogEdit(selected[0]);
}

updateFilter(event) {
}

}
