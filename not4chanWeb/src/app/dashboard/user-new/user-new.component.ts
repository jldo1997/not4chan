import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { UserService } from 'src/app/services/user.service';
import { AdvUserDto } from 'src/app/model/dto/advancedUser.dto';

@Component({
  selector: 'app-user-new',
  templateUrl: './user-new.component.html',
  styleUrls: ['./user-new.component.scss']
})
export class UserNewComponent implements OnInit {
  name: string;
  email: string;
  role: string;
  roles = ["admin", "moderator"];

  constructor(private userService: UserService, public dialogRef: MatDialogRef<UserNewComponent>) { }

  ngOnInit() {
  }

  createUser() {
    const dto = new  AdvUserDto(this.email, this.name, this.role);

    this.userService.createUser(dto).subscribe(result => {
      this.dialogRef.close();
    }, error => {
      console.log(error);
    });
  }
  validateEmail(): boolean {
    const emailRegExpr = new RegExp('[a-zA-Z0-9-]{1,}@([a-zA-Z\.])?[a-zA-Z]{1,}\.[a-zA-Z]{1,4}');
    return emailRegExpr.test(this.email);
  }

}
