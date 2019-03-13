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
  declarations: [ DashboardComponent, UserTableComponent ]
})

export class DashboardModule {}
