import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { CommentService } from 'src/app/services/comment.service';
import { user } from 'src/app/model/user';
import { CommentDto, ExtendCommentDtoR, ExtendCommentDtoP, ExtendCommentDtoPR } from 'src/app/model/dto/comment.dto';
import { photo } from 'src/app/model/photo.model';
import { comment } from 'src/app/model/comment.model';

@Component({
  selector: 'app-comment-deledit',
  templateUrl: './comment-deledit.component.html',
  styleUrls: ['./comment-deledit.component.scss']
})
export class CommentDeleditComponent implements OnInit {
  id: string;
  user: user;
  photo: photo;
  responseTo: comment;
  content: string;
  dto: CommentDto;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private service: CommentService,
  public dialogRef: MatDialogRef<CommentDeleditComponent>) { }

  ngOnInit() {
    this.id = this.data.recurso.id;
    this.user = this.data.recurso.user;
    this.photo = this.data.recurso.photo;
    this.responseTo = this.data.recurso.responseTo;
    this.content = this.data.recurso.content;
  }

//When I wrote this, only God and I understood what I was doing
//Now, God only knows

  editComment() {
    
    if(typeof this.photo === 'undefined' && typeof this.responseTo === 'undefined'){
      this.dto = new CommentDto(this.user.id, this.content);
    } else if (typeof this.photo !== 'undefined' && typeof this.responseTo === 'undefined'){
      this.dto = new ExtendCommentDtoP(this.user.id, this.photo.id, this.content);
    }else if (typeof this.photo === 'undefined' && typeof this.responseTo !== 'undefined'){
      this.dto = new ExtendCommentDtoR(this.user.id, this.responseTo.id, this.content);
    }else if (typeof this.photo !== 'undefined' && typeof this.responseTo !== 'undefined'){
      this.dto = new ExtendCommentDtoPR(this.user.id, this.photo.id, this.responseTo.id, this.content);
    }
    
    this.service.editComment(this.id, this.dto).subscribe(recurso =>{
      this.dialogRef.close(); 
      console.log(Response);
    }, error => {
      console.log(error);
    });
  }

  deleteComment() {
    this.service.deleteComment(this.id).subscribe(recurso =>{
      this.dialogRef.close(); 
      console.log(recurso);
      
    }, error => {
      console.log(error);
    });
  }

}
