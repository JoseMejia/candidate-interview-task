package com.epages.interview.controller;

import com.epages.interview.converter.ProductRepresentationConverter;
import com.epages.interview.domain.Brand;
import com.epages.interview.domain.Product;
import com.epages.interview.representation.ProductRepresentation;
import com.epages.interview.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductService productService;

    private final ProductRepresentationConverter productRepresentationConverter;

    @Autowired
    public ProductsController(final ProductService productService, ProductRepresentationConverter productRepresentationConverter) {
        this.productService = productService;
        this.productRepresentationConverter = productRepresentationConverter;
    }

    @GetMapping(value = "/brands", produces = "application/vnd.brands.api.v1+json")
    public Map<String, List<ProductRepresentation>> getSortedBrandsWithProducts() {
        final List<Brand> brands = productService.retrieveSortedBrands();
        return brands.stream()
                .collect(TreeMap::new,
                        (m, brand) -> m.put(brand.getBrandName(),
                                toProductRepresentations(brand.getProducts())),
                        (m, u) -> {});
    }

    private List<ProductRepresentation> toProductRepresentations(final List<Product> products) {
        return products.stream().sorted(comparing(Product::getPrice))
                .map(productRepresentationConverter::convert)
                .collect(toList());
    }
}
