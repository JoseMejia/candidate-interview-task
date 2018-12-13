package com.epages.interview.repository;

import com.epages.interview.InterviewApplicationTests;
import com.epages.interview.domain.Brand;
import com.epages.interview.helpers.BrandHelper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
public class BrandRepositoryTest extends InterviewApplicationTests {

    @Autowired
    private BrandRepository brandRepository;

    @Before
    public void setUp() {
        brandRepository.deleteAll();
    }

    @Test
    public void shouldPersistBrand() {
        // Given
        final Brand adidas = BrandHelper.a_brand("adidas");

        // When
        final Brand savedBrand = brandRepository.save(adidas);

        // Then
        final List<Brand> allBrands = brandRepository.findAll();
        assertThat(allBrands).containsOnly(savedBrand);
    }
}