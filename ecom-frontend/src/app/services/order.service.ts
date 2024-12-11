import { Injectable } from '@angular/core';
import {ParentListService} from "./parent-list.service";
import {Product} from "../models/Product";
import {HttpClient} from "@angular/common/http";
import {ProductEndpoints} from "../config/endpoints/product.endpoints";
import {OrderEndpoints} from "../config/endpoints/order.endpoints";
import {Order} from "../models/Order";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class OrderService extends ParentListService<Order>{

  order$ = new BehaviorSubject<Order | undefined>(undefined);

  constructor(private http: HttpClient) {
    super()
  }


  getAll() {
    this.http?.get<Order[]>(OrderEndpoints.getAll())
      .subscribe({
        next: res => this.notify(res),
        error: err => this.notifyError(err)
      })
  }

  observeOrder() {
    return this.order$.asObservable();
  }

  setOrder(order: Order) {
    this.order$.next(order)
  }

  notifyOrderError(err: any) {
    this.notifyError(err)
  }

  resetOrder() {
    this.order$.next(undefined)
  }

  getOrder(id: number) {
    this.http?.get<Order>(OrderEndpoints.getOne(id))
      .subscribe({
        next: res => this.setOrder(res),
        error: err => this.notifyOrderError(err)
      })
  }
}
