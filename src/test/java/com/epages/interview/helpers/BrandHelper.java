package com.epages.interview.helpers;

import com.epages.interview.domain.Brand;

import java.util.ArrayList;

public class BrandHelper {

    private BrandHelper() {
    }

    public static Brand a_brand(final String brandName) {
        return Brand.builder().brandName(brandName).products(new ArrayList<>()).build();
    }
}
