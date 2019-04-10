import { CategoryService } from './../../services/category.service';
import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-category-delete',
  templateUrl: './category-delete.component.html',
  styleUrls: ['./category-delete.component.scss']
})
export class CategoryDeleteComponent implements OnInit {
  id: string;
  delete: string;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private service: CategoryService,
  public dialogRef: MatDialogRef<CategoryDeleteComponent>) { }

  ngOnInit() {
    this.id = this.data.recurso.id;
  }

  deleteCategory() {
    if(this.delete == "DELETE"){
    this.service.deleteCategory(this.id).subscribe(recurso =>{
        this.dialogRef.close(); 
      } , error => {
        console.log(error);
      });
    } else {
      //do nothing
    }
  }
}
