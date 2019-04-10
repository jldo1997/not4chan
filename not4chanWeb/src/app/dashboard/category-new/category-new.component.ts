import { CategoryService } from './../../services/category.service';
import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { categoryDto } from 'src/app/model/dto/category.dto';

@Component({
  selector: 'app-category-new',
  templateUrl: './category-new.component.html',
  styleUrls: ['./category-new.component.scss']
})
export class CategoryNewComponent implements OnInit {
  title: string;

  constructor(private service: CategoryService, public dialogRef: MatDialogRef<CategoryNewComponent>) { }

  ngOnInit() {
  }

  createCategory() {
    const dto = new categoryDto(this.title);

    this.service.createCategory(dto).subscribe(result => {
      this.dialogRef.close();
    }, error => {
      console.log(error);
    });
  }

}
