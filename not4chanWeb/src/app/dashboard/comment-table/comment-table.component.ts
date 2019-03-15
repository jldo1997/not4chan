import { Component, OnInit } from '@angular/core';
import { CommentService } from 'src/app/services/comment.service';
import { MatDialog } from '@angular/material';
import { comment } from 'src/app/model/comment.model';
import { CommentResponse } from 'src/app/interfaces/comments-response.interface';
import { CommentDeleditComponent } from '../comment-deledit/comment-deledit.component';

@Component({
  selector: 'app-comment-table',
  templateUrl: './comment-table.component.html',
  styleUrls: ['./comment-table.component.scss']
})
export class CommentTableComponent implements OnInit {
  rows:  comment[];
  selected = [];
  temp: CommentResponse;

  constructor(private service: CommentService, private dialog: MatDialog) { }

  ngOnInit() {
    this.getAllComments();
  }

  getAllComments() {
    this.service.getAll().subscribe(listComments => {
      this.temp = listComments;
      this.rows = this.temp.rows;
      console.log(this.rows);
    }, error =>  {
      console.log(error);
    });
  }
  openDialogDeledit(selected : comment) {
    const dialogDeledit = this.dialog.open(CommentDeleditComponent, {
      data :{'recurso': selected }
    });

    dialogDeledit.afterClosed().subscribe(result =>{
      this.getAllComments();
      });
  }

  onSelect({ selected }) {
    this.openDialogDeledit(selected[0]);
}

}
