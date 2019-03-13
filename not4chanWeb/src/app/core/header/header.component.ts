import { Component, EventEmitter, Output } from '@angular/core';

import * as screenfull from 'screenfull';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent {

  @Output() toggleSidenav = new EventEmitter<void>();
  @Output() toggleNotificationSidenav = new EventEmitter<void>();

  constructor(private authService: AuthService, private router: Router) {
  }

  fullScreenToggle(): void {
    if (screenfull.enabled) {
      screenfull.toggle();
    }
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }

  gotoprofile() {
    this.router.navigate(['/dashboard/profile']);
  }
}
