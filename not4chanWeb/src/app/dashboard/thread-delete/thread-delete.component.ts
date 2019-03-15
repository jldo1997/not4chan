import { Component, OnInit, Inject } from '@angular/core';
import { ThreadService } from 'src/app/services/thread.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-thread-delete',
  templateUrl: './thread-delete.component.html',
  styleUrls: ['./thread-delete.component.scss']
})
export class ThreadDeleteComponent implements OnInit {
  id: string;
  delete: string;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private service: ThreadService,
  public dialogRef: MatDialogRef<ThreadDeleteComponent>) { }

  ngOnInit() {
    this.id = this.data.recurso.id;
  }

  deleteUser() {
    if(this.delete == "DELETE"){
    this.service.deleteThread(this.id).subscribe(recurso =>{
        this.dialogRef.close(); 
      } , error => {
        console.log(error);
      });
    } else {

    }
}

}
