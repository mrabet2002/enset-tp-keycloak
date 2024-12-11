import { Component } from '@angular/core';
import {OrderService} from "../../services/order.service";
import {ActivatedRoute} from "@angular/router";
import {Order} from "../../models/Order";

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrl: './order-details.component.css'
})
export class OrderDetailsComponent {
  order$ = this.orderService.observeOrder();

  constructor(
    private orderService: OrderService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.orderService.getOrder(Number(id))
  }

  getTotal(order: Order) {
    return order.productItems.reduce((acc, item) => acc + item.price, 0)
  }
}
