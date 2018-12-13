package com.epages.interview.converter;

import com.epages.interview.domain.Product;
import com.epages.interview.representation.ProductRepresentation;
import org.springframework.stereotype.Component;

@Component
public class ProductRepresentationConverter {

    public ProductRepresentation convert(final Product product) {
        return ProductRepresentation.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .event(product.isOnSale()?"ON SALE": null)
                .build();
        }
}
