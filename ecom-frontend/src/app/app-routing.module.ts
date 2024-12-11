import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProductsComponent} from "./components/products/products.component";
import {AuthGuard} from "./gurads/auth.guard";
import {OrdersComponent} from "./components/orders/orders.component";
import {OrderDetailsComponent} from "./components/order-details/order-details.component";

const routes: Routes = [
  {
    path: 'products',
    component: ProductsComponent,
    // canActivate: [AuthGuard],
  },
  {
    path: 'orders',
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        component: OrdersComponent,
      },
      {
        path: ':id',
        component: OrderDetailsComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
