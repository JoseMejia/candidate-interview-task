package com.epages.interview.service;

import com.epages.interview.domain.Brand;
import com.epages.interview.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final BrandRepository brandRepository;

    @Autowired
    public ProductService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> retrieveSortedBrands() {
        return brandRepository.findBrandByOrderByBrandNameAsc();
    }
}
