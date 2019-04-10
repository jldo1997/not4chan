import { Component, OnInit } from '@angular/core';
import { thread } from 'src/app/model/thread.model';
import { ThreadResponse } from 'src/app/interfaces/threads-response.interface';
import { ThreadService } from 'src/app/services/thread.service';
import { MatDialog } from '@angular/material';
import { ThreadDeleteComponent } from '../thread-delete/thread-delete.component';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-thread-table',
  templateUrl: './thread-table.component.html',
  styleUrls: ['./thread-table.component.scss']
})
export class ThreadTableComponent implements OnInit {
  rows:  thread[];
  selected = [];
  temp: ThreadResponse;

  constructor(private service: ThreadService, private titleService: Title, private dialog: MatDialog) { }

  ngOnInit() {
    this.getAllThreads();
    this.titleService.setTitle('3chan admin panel - List of threads');
  }

  getAllThreads(){
    this.service.getAll().subscribe(listThreads => {
      this.temp = listThreads;
      this.rows = this.temp.rows;
      this.rows.forEach(element => {
        var cont:number = 0;
        element.comments.forEach(element => {
          cont = cont + 1;
        });
        if(cont != 0){ cont = cont + 1; }
        element.numberComments = cont;
      });
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
