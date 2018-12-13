package com.epages.interview.repository;

import com.epages.interview.InterviewApplicationTests;
import com.epages.interview.domain.Brand;
import com.epages.interview.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.epages.interview.helpers.BrandHelper.a_brand;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class ProductRepositoryTest extends InterviewApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Before
    public void setUp() {
        brandRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void shouldPersistProduct() {
        // Given
        final Brand adidas = brandRepository.save(a_brand("ADIDAS"));

        final Product shirt = Product.builder()
                .brand(adidas)
                .name("Panama Tricot")
                .onSale(true)
                .price(12.00)
                .build();
        adidas.getProducts().add(shirt);

        // When
        productRepository.save(shirt);

        // Then
        final List<Product> allProducts = productRepository.findAll();
        assertThat(allProducts).hasSize(1);
        assertThat(allProducts.get(0)).isEqualToIgnoringGivenFields(shirt, "id");
    }
}