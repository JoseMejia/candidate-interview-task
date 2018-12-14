package com.epages.interview.converter;

import com.epages.interview.domain.Product;
import com.epages.interview.representation.ProductRepresentation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductRepresentationConverter {

    public ProductRepresentation convert(final Product product) {
        return ProductRepresentation.builder()
                .id(product.getId())
                .name(product.getName())
                .price(new BigDecimal(product.getPrice()).setScale(2, BigDecimal.ROUND_UP))
                .event(product.isOnSale()?"ON SALE": null)
                .build();
        }
}
