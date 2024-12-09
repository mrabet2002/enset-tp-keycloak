import { Injectable } from '@angular/core';
import {ParentListService} from "./parent-list.service";
import {Product} from "../models/Product";
import {HttpClient} from "@angular/common/http";
import {ProductEndpoints} from "../config/endpoints/product.endpoints";

@Injectable({
  providedIn: 'root'
})
export class ProductService extends ParentListService<Product>{

  constructor(private http: HttpClient) {
    super()
  }


  getAll() {
    this.http?.get<Product[]>(ProductEndpoints.getAll())
      .subscribe({
        next: res => this.notify(res),
        error: err => this.notifyError(err)
      })
  }
}
