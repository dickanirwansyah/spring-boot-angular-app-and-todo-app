import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CategoryComponent } from './category/category.component';
import { HomeComponent } from './home/home.component';
import { ProductComponent } from './product/product.component';


export const router: Routes = [
  { path: 'home', redirectTo: '', pathMatch: 'full'},
  { path: 'category', component: CategoryComponent },
  { path: 'product', component: ProductComponent }
];

export const routes: ModuleWithProviders = RouterModule.forRoot(router);
