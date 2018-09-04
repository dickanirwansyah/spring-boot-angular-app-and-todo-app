import { Component, OnInit } from '@angular/core';
import { Product } from '../product/Product';
import { ProductService } from '../product/ProductService';
import { CategoryService } from '../category/CategoryService';
import { Observable } from 'rxjs';
import { Category } from '../category/Category';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products: Observable<Product[]>
  product: Product = new Product();
  categorys=[]
  constructor(
    private _productService: ProductService,
    private _categoryService: CategoryService) { }

  ngOnInit() {
    this.getProductComponentList()
    this.getCategoryComponent()
  }

  getSaveProduct(){
    this._productService.getProductSave(this.product)
    .subscribe(data => console.log(data), error => console.log(error));
    this.product = new Product();
  }

  onSubmited(){
    this.getSaveProduct();
  }

  getCategoryComponent(){
    this._categoryService.getCategoryList()
    .subscribe(data => this.categorys = data, error => console.error(error));
  }

  getProductComponentList(){
    this.products = this._productService.getProductList();
  }
}
