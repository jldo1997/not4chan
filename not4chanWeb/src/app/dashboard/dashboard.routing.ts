import { Routes } from '@angular/router';

import { DashboardComponent } from './dashboard.component';
import { UserTableComponent } from './user-table/user-table.component';
import { ThreadTableComponent } from './thread-table/thread-table.component';
import { CommentTableComponent } from './comment-table/comment-table.component';

export const DashboardRoutes: Routes = [{
  path: '',
  children: [{
    path: '',
    component: DashboardComponent
  }, {
    path: 'user-table',
    component: UserTableComponent
  }, {
    path: 'thread-table',
    component: ThreadTableComponent
  }, {
    path: 'comment-table',
    component: CommentTableComponent
  }]
  
}];
