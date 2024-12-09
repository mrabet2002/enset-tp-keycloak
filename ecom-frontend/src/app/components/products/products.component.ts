import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../services/product.service";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit {
  products$ = this.productService.observeStream();

  constructor(
    private productService: ProductService
  ) {}

  ngOnInit() {
    this.productService.getAll()
  }
}
