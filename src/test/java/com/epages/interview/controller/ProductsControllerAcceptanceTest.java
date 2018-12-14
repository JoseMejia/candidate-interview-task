package com.epages.interview.controller;

import com.epages.interview.InterviewApplicationTests;
import com.epages.interview.domain.Brand;
import com.epages.interview.domain.Product;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static com.epages.interview.helpers.BrandHelper.a_brand;
import static com.epages.interview.helpers.ProductHelper.Sale.NO_SALE;
import static com.epages.interview.helpers.ProductHelper.Sale.ON_SALE;
import static com.epages.interview.helpers.ProductHelper.a_product;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Transactional
public class ProductsControllerAcceptanceTest extends InterviewApplicationTests {

    @Test
    public void shouldGetSortedBrandsMediatype() throws Exception {
        // Given

        final Brand puma = brandRepository.save(a_brand("puma"));
        final Brand adidas = brandRepository.save(a_brand("adidas"));

        final Product product1 = productRepository.save(a_product("puma shirt", 13.0, ON_SALE, puma));
        final Product product2 = productRepository.save(a_product("adidas shirt", 15.0, NO_SALE, adidas));

        // When
        final MvcResult mvcResult = mockMvc.perform(get("/products/brands")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        // Then
        final MockHttpServletResponse response = mvcResult.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentType()).isEqualTo("application/vnd.brands.api.v1+json;charset=UTF-8");
        assertThat(response.getContentAsString()).isEqualTo("{\"adidas\":[{\"id\":"
                + product2.getId() +
                ",\"name\":\"adidas shirt\",\"price\":15.00}],\"puma\":[{\"id\":" +
                product1.getId() + ",\"name\":\"puma shirt\",\"price\":13.00,\"event\":\"ON SALE\"}]}");

    }
}