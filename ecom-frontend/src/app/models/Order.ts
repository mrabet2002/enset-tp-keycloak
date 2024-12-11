import {ProductItem} from "./ProductItem";

enum OrderStatus {
  PENDING="PENDING",
  COMPLETED="COMPLETED",
  CANCELLED="CANCELLED",
  SHIPPED="SHIPPED",
  DELIVERED="DELIVERED",
}

export interface Order {
  id: number;
  createdAt: string;
  total: number;
  status: OrderStatus;
  productItems: ProductItem[];
}
