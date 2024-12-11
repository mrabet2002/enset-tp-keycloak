import {API_CONFIG} from "../api.config";

export class OrderEndpoints {
  private static readonly root = () => `${API_CONFIG.ORDER_SERVICE.BASE_URL}/orders`;
  public static readonly getAll = () => `${OrderEndpoints.root()}`;
  public static readonly getOne = (id: number) => `${OrderEndpoints.root()}/${id}`;
}
