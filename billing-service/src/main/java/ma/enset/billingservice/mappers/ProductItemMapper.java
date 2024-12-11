package ma.enset.billingservice.mappers;

import ma.enset.billingservice.dtos.ProductItemDto;
import ma.enset.billingservice.entities.ProductItem;

public class ProductItemMapper {
    public static ProductItemDto toDto(ProductItem productItem) {
        ProductItemDto productItemDto = new ProductItemDto();
        productItemDto.setId(productItem.getId());
        productItemDto.setPrice(productItem.getPrice());
        productItemDto.setQuantity(productItem.getQuantity());
        productItemDto.setProduct(productItem.getProduct());
        return productItemDto;
    }


}
