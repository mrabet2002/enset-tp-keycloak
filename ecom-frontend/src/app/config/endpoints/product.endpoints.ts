import { API_CONFIG } from "../api.config";

export class ProductEndpoints {
    private static readonly root = () =>  `${API_CONFIG.BASE_URL}/products`;
    public static readonly getAll = () => `${ProductEndpoints.root()}`;
}
