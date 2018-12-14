package com.epages.interview.converter;

import com.epages.interview.domain.Product;
import com.epages.interview.representation.ProductRepresentation;
import org.junit.Test;

import java.math.BigDecimal;

import static com.epages.interview.helpers.BrandHelper.a_brand;
import static com.epages.interview.helpers.ProductHelper.Sale.NO_SALE;
import static com.epages.interview.helpers.ProductHelper.Sale.ON_SALE;
import static com.epages.interview.helpers.ProductHelper.a_product;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductRepresentationConverterTest {

    private ProductRepresentationConverter converter = new ProductRepresentationConverter();

    @Test
    public void shouldConvertToProductRepresentationOnSale() {
        // Given
        final Product shirt = a_product(1L, "Panama Tricot", 12.0, ON_SALE, a_brand("ADIDAS"));

        // When
        final ProductRepresentation actual = converter.convert(shirt);

        // Then
        final ProductRepresentation expected = ProductRepresentation.builder().id(1L).name("Panama Tricot").price(new BigDecimal("12.00")).event("ON SALE").build();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldConvertToProductRepresentation() {
        // Given
        final Product shirt = a_product(1L, "Panama Tricot", 12.0, NO_SALE, a_brand("ADIDAS"));

        // When
        final ProductRepresentation actual = converter.convert(shirt);

        // Then
        final ProductRepresentation expected = ProductRepresentation.builder().id(1L).name("Panama Tricot").price(new BigDecimal("12.00")).build();
        assertThat(actual).isEqualTo(expected);
    }
}