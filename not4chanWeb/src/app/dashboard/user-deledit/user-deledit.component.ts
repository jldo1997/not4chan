import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { UserService } from 'src/app/services/user.service';
import { UserDto } from 'src/app/model/dto/user.dto';

@Component({
  selector: 'app-user-deledit',
  templateUrl: './user-deledit.component.html',
  styleUrls: ['./user-deledit.component.scss']
})
export class UserDeleditComponent implements OnInit {

  id: string;
  name: string;
  email: string;
  role: string;
  banFlag: boolean;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private userService: UserService,
  public dialogRef: MatDialogRef<UserDeleditComponent>) { }

  ngOnInit() {
    this.id = this.data.recurso.id;
    this.name = this.data.recurso.name;
    this.email = this.data.recurso.email;
    this.role = this.data.recurso.role;

    if(this.role == "banned"){
      this.banFlag = true;
    } else {
      this.banFlag = false;
    }
  }

  editUser() {
    const dto = new UserDto(this.name, this.email);
    this.userService.editUser(dto, this.id).subscribe(recurso =>{
      this.dialogRef.close(); 
    }, error => {
      console.log(error);
    });
  }

  deleteUser() {
    this.userService.delUser(this.id).subscribe(recurso =>{
      this.dialogRef.close(); 
    }, error => {
      console.log(error);
    });
  }

  banUser() {
    this.userService.banUser(this.id).subscribe(recurso =>{
      this.dialogRef.close(); 
    }, error => {
      console.log(error);
    });
  }

  unbanUser() {
    this.userService.unbanUser(this.id).subscribe(recurso =>{
      this.dialogRef.close(); 
    }, error => {
      console.log(error);
    });
  }

}
