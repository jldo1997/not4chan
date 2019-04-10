import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatIconModule, MatCardModule, MatButtonModule, MatListModule, MatProgressBarModule, MatMenuModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';

import { DashboardComponent } from './dashboard.component';
import { DashboardRoutes } from './dashboard.routing';
import { UserTableComponent } from './user-table/user-table.component';

import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import {MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import { FormsModule } from '@angular/forms';
import { UserDeleditComponent } from './user-deledit/user-deledit.component';
import { UserNewComponent } from './user-new/user-new.component';
import { ThreadTableComponent } from './thread-table/thread-table.component';
import { ThreadDeleteComponent } from './thread-delete/thread-delete.component';
import { CommentTableComponent } from './comment-table/comment-table.component';
import { CommentDeleditComponent } from './comment-deledit/comment-deledit.component';
import { CategoryTableComponent } from './category-table/category-table.component';
import { CategoryDeleteComponent } from './category-delete/category-delete.component';
import { CategoryNewComponent } from './category-new/category-new.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(DashboardRoutes),
    MatIconModule,
    MatCardModule,
    MatButtonModule,
    MatListModule,
    MatProgressBarModule,
    MatMenuModule,
    FlexLayoutModule,
    NgxDatatableModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    FormsModule
  ],
  entryComponents: [UserDeleditComponent, UserNewComponent, ThreadDeleteComponent, CommentDeleditComponent, CategoryDeleteComponent, CategoryNewComponent],
  declarations: [ DashboardComponent, UserTableComponent, UserDeleditComponent, UserNewComponent, ThreadTableComponent, ThreadDeleteComponent, CommentTableComponent, CommentDeleditComponent, CategoryTableComponent, CategoryDeleteComponent, CategoryNewComponent]
})

export class DashboardModule {}
