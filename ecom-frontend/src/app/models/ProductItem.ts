import {Product} from "./Product";

export interface ProductItem {
  id: number;
  price: number;
  quantity: number;
  product: Product;
}
