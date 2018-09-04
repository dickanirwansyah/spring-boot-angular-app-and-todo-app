import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../category/CategoryService';
import { Category } from '../category/Category';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  category: Category = new Category();
  statuses = [
    {
      "id" : true,
      "value" : "true"
    },
    {
      "id" : false,
      "value" : "false"
    }
  ];
  categorys: Observable<Category[]>;

  constructor(private _categoryService: CategoryService) { }

  ngOnInit() {
    this.getCategoryComponentList();
  }

  /**
  cancelUpdate(){
    window.location.reload();
  }
  **/

  getSave(){
    this._categoryService.getNewCategory(this.category)
      .subscribe(data => console.log(data), error => console.log(error));
      this.category = new Category();
  }

  /**
  getUpdate(idcategory: number){
    this._categoryService.getUpdateCategory(this.category, idcategory)
    .subscribe(data => console.log(data), error => console.log(error));
    this.category = new Category();
  }
  **/

  getUpdateById(idcategory: number){
    this._categoryService.getCategoryId(idcategory)
      .subscribe(data => {
        this.category = {
          id : data.id,
          name : data.name,
          status : data.status
        },console.log(data)
      },
      error => console.log(error));
  }

  getById(id: number){
    console.log('idproduct : '+id);
    this._categoryService.getCategoryId(id)
    .subscribe(data => console.log(data),
     error => console.log(error));
  }

  onSubmited(){
    this.getSave();
  }

  getCategoryComponentList(){
      this.categorys = this._categoryService.getCategoryList();
  }

}
