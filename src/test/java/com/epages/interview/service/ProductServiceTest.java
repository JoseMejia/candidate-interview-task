package com.epages.interview.service;

import com.epages.interview.domain.Brand;
import com.epages.interview.repository.BrandRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private BrandRepository brandRepositoryMock;

    @InjectMocks
    private ProductService productService;

    @Test
    public void shouldRetrieveSortedBrandsWithProducts() {
        // Given
        final Brand brandMock = mock(Brand.class);

        when(brandRepositoryMock.findBrandByOrderByBrandNameAsc())
                .thenReturn(singletonList(brandMock));

        // When
        final List<Brand> brands = productService.retrieveSortedBrands();

        // Then
        assertThat(brands).containsExactly(brandMock);
        verify(brandRepositoryMock).findBrandByOrderByBrandNameAsc();
        verifyNoMoreInteractions(brandRepositoryMock);
    }
}