import { Title } from '@angular/platform-browser';
import { CategoryService } from './../../services/category.service';
import { CategoriesResponse } from './../../interfaces/categories-response.interface';
import { Component, OnInit } from '@angular/core';
import { category } from 'src/app/model/category.model';
import { MatDialog } from '@angular/material';
import { CategoryDeleteComponent } from '../category-delete/category-delete.component';
import { CategoryNewComponent } from '../category-new/category-new.component';

@Component({
  selector: 'app-category-table',
  templateUrl: './category-table.component.html',
  styleUrls: ['./category-table.component.scss']
})
export class CategoryTableComponent implements OnInit {

  rows:  category[];
  selected = [];
  temp: CategoriesResponse;

  constructor(private titleService: Title, private service: CategoryService, private dialog: MatDialog) { }

  ngOnInit() {
    this.getAllCategories();
    this.titleService.setTitle('3chan admin panel - List of categories');
  }

  getAllCategories(){
    this.service.getAll().subscribe(listCategories => {
      this.temp = listCategories;
      this.rows = this.temp.rows;
      console.log(this.rows);
    }, error =>  {
      console.log(error);
    });
  }

  openDialogDelete(selected: category) {
    const dialogDelete = this.dialog.open(CategoryDeleteComponent, {
      data :{'recurso': selected }
    });
    dialogDelete.afterClosed().subscribe(result =>{
    this.getAllCategories();
    });
  }

  openDialogNewCategory() {
    const dialogoNewProduct = this.dialog.open(CategoryNewComponent);

    dialogoNewProduct.afterClosed().subscribe(result => {
      this.getAllCategories();
    });
    
  }

  onSelect({ selected }) {
    this.openDialogDelete(selected[0]);
}

}
