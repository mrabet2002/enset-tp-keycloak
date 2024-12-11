import { Component } from '@angular/core';
import {ProductService} from "../../services/product.service";
import {OrderService} from "../../services/order.service";

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent {
  orders$ = this.orderService.observeStream();

  constructor(
    private orderService: OrderService
  ) {}

  ngOnInit() {
    this.orderService.getAll()
  }
}
