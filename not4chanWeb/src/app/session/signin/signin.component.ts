import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { LoginDto } from 'src/app/model/dto/login.dto';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {

  email: string;
  password: string;
  userInvalidFlag: boolean;

  public form: FormGroup;
  constructor(private fb: FormBuilder, private router: Router, private authService: AuthService) {}

  ngOnInit() {
    this.userInvalidFlag = false;
    this.form = this.fb.group ( {
      uname: [null , Validators.compose ( [ Validators.required ] )] , password: [null , Validators.compose ( [ Validators.required ] )]
    } );
  }

  onSubmit() {
    this.email = this.form.get('uname').value;
    this.password = this.form.get('password').value;
    const dto = new LoginDto(this.email, this.password);
    this.authService.doLogin(dto).subscribe(loginResp => {
      if(loginResp.user.role == "admin"){
        console.log(loginResp);
        this.authService.setLoginData(loginResp);
        this.router.navigate ( [ '/dashboard' ] );
      } else {
        this.userInvalidFlag = true;
      }
    }, error => {
      console.log('Error en petición de login');
      console.log(error);
      this.userInvalidFlag = true;
      }
    );
  }

}
