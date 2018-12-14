package com.epages.interview.service;

import com.epages.interview.domain.Brand;
import com.epages.interview.exceptions.TechnicalException;
import com.epages.interview.repository.BrandRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final BrandRepository brandRepository;

    @Autowired
    public ProductService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> retrieveSortedBrands() throws TechnicalException {
        try {
            return brandRepository.findBrandByOrderByBrandNameAsc();
        } catch (final Exception e) {
            final String errorMessage = "unable to retrieve sorted brands";
            LOGGER.error(errorMessage, e);
            throw new TechnicalException(errorMessage, e);
        }
    }
}
