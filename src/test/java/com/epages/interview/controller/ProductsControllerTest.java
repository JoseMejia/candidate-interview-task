package com.epages.interview.controller;

import com.epages.interview.converter.ProductRepresentationConverter;
import com.epages.interview.domain.Brand;
import com.epages.interview.domain.Product;
import com.epages.interview.representation.ProductRepresentation;
import com.epages.interview.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.epages.interview.helpers.BrandHelper.a_brand;
import static com.epages.interview.helpers.ProductHelper.Sale.NO_SALE;
import static com.epages.interview.helpers.ProductHelper.Sale.ON_SALE;
import static com.epages.interview.helpers.ProductHelper.a_product;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductsControllerTest {

    @Mock
    private ProductService productServiceMock;

    @Mock
    private ProductRepresentationConverter productRepresentationConverterMock;

    @InjectMocks
    private ProductsController productsController;

    @Test
    public void shouldGetSortedBrandsWithProducts() {
        // Given
        final Brand adidas = a_brand("adidas");
        final Brand puma = a_brand("puma");

        final Product adidasShirt = a_product(1L, "tricot", 12.77, NO_SALE, adidas);
        final Product adidasShoes = a_product(2L, "shoes", 1.00, ON_SALE, adidas);

        when(productServiceMock.retrieveSortedBrands())
                .thenReturn(asList(adidas, puma));

        final ProductRepresentation productRepresentation1 = ProductRepresentation.builder()
                .id(2L)
                .name("shoes")
                .price(new BigDecimal("1.00"))
                .build();

        final ProductRepresentation productRepresentation2 = ProductRepresentation.builder()
                .id(1L)
                .name("tricot")
                .price(new BigDecimal("12.77"))
                .build();

        when(productRepresentationConverterMock.convert(adidasShirt))
                .thenReturn(productRepresentation2);
        when(productRepresentationConverterMock.convert(adidasShoes))
                .thenReturn(productRepresentation1);

        // When

        final Map<String, List<ProductRepresentation>> response = productsController.getSortedBrandsWithProducts();

        // Then
        assertThat(response).containsOnlyKeys("adidas", "puma");
        assertThat(response.get("adidas")).containsExactly(productRepresentation1, productRepresentation2);
        assertThat(response.get("puma")).isEmpty();
        verify(productServiceMock).retrieveSortedBrands();
        verify(productRepresentationConverterMock, times(2)).convert(any(Product.class));
        verifyNoMoreInteractions(productServiceMock, productRepresentationConverterMock);
    }
}