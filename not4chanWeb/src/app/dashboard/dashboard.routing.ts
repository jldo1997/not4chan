import { Routes } from '@angular/router';

import { DashboardComponent } from './dashboard.component';
import { UserTableComponent } from './user-table/user-table.component';

export const DashboardRoutes: Routes = [{
  path: '',
  children: [{
    path: '',
    component: DashboardComponent
  }, {
    path: 'user-table',
    component: UserTableComponent
  }, ]
  
}];
