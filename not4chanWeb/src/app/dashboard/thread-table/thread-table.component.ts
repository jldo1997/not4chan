import { Component, OnInit } from '@angular/core';
import { thread } from 'src/app/model/thread.model';
import { ThreadResponse } from 'src/app/interfaces/threads-response.interface';
import { ThreadService } from 'src/app/services/thread.service';
import { MatDialog } from '@angular/material';
import { ThreadDeleteComponent } from '../thread-delete/thread-delete.component';

@Component({
  selector: 'app-thread-table',
  templateUrl: './thread-table.component.html',
  styleUrls: ['./thread-table.component.scss']
})
export class ThreadTableComponent implements OnInit {
  rows:  thread[];
  selected = [];
  temp: ThreadResponse;

  constructor(private service: ThreadService, private dialog: MatDialog) { }

  ngOnInit() {
    this.getAllThreads();
  }

  getAllThreads(){
    this.service.getAll().subscribe(listThreads => {
      this.temp = listThreads;
      this.rows = this.temp.rows;
      console.log(this.rows);
    }, error =>  {
      console.log(error);
    });
  }

  openDialogDeledit(selected : thread) {
    const dialogDelete = this.dialog.open(ThreadDeleteComponent, {
      data :{'recurso': selected }
    });
    dialogDelete.afterClosed().subscribe(result =>{
    this.getAllThreads();
    });
  }

  onSelect({ selected }) {
    this.openDialogDeledit(selected[0]);
}

}
