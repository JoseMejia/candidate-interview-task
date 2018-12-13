package com.epages.interview.repository;

import com.epages.interview.InterviewApplicationTests;
import com.epages.interview.domain.Brand;
import com.epages.interview.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.epages.interview.helpers.BrandHelper.a_brand;
import static com.epages.interview.helpers.ProductHelper.Sale.NO_SALE;
import static com.epages.interview.helpers.ProductHelper.Sale.ON_SALE;
import static com.epages.interview.helpers.ProductHelper.a_product;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;


@Transactional
public class BrandRepositoryTest extends InterviewApplicationTests {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        brandRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void shouldPersistBrand() {
        // Given
        final Brand adidas = a_brand("adidas");

        // When
        final Brand savedBrand = brandRepository.save(adidas);

        // Then
        final List<Brand> allBrands = brandRepository.findAll();
        assertThat(allBrands).containsOnly(savedBrand);
    }

    @Test
    public void shouldRetrieveBrandsSortedByName() {
        // Given
        final Brand puma = brandRepository.save(a_brand("puma"));
        final Brand adidas = brandRepository.save(a_brand("adidas"));

        final Product product1 = a_product("puma shirt", 13.0, ON_SALE, puma);
        final Product product2 = a_product("adidas shirt", 15.0, NO_SALE, adidas);
        final Product product3 = a_product("adidas shoes", 11.0, NO_SALE, adidas);

        productRepository.saveAll(asList(product1, product2, product3));

        // When
        final List<Brand> allBrands = brandRepository.findBrandByOrderByBrandNameAsc();

        // Then
        assertThat(allBrands).hasSize(2);
        final List<String> orderedBrandNames = allBrands.stream().map(Brand::getBrandName).collect(Collectors.toList());
        assertThat(orderedBrandNames).containsExactly("adidas", "puma");
        final List<Product> products = allBrands.stream().flatMap(it -> it.getProducts().stream()).collect(Collectors.toList());
        assertThat(products).containsExactly(product2, product3, product1);
    }
}