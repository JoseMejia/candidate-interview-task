package com.epages.interview.repository;

import com.epages.interview.InterviewApplicationTests;
import com.epages.interview.domain.Brand;
import com.epages.interview.domain.Product;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.epages.interview.helpers.BrandHelper.a_brand;
import static com.epages.interview.helpers.ProductHelper.Sale.ON_SALE;
import static com.epages.interview.helpers.ProductHelper.a_product;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class ProductRepositoryTest extends InterviewApplicationTests {

    @Test
    public void shouldPersistProduct() {
        // Given
        final Brand adidas = brandRepository.save(a_brand("ADIDAS"));

        final Product shirt = a_product("Panama Tricot", 12.00, ON_SALE, adidas);

        // When
        productRepository.save(shirt);

        // Then
        final List<Product> allProducts = productRepository.findAll();
        assertThat(allProducts).hasSize(1);
        assertThat(allProducts.get(0)).isEqualToIgnoringGivenFields(shirt, "id");
    }
}