package com.epages.interview.helpers;

import com.epages.interview.domain.Brand;
import com.epages.interview.domain.Product;

public class ProductHelper {

    private ProductHelper() {
    }

    public static Product a_product(final Long id, final String name, final double price, final Sale sale, final Brand brand) {
        final Product product = Product.builder().id(id).name(name).price(price).onSale(sale.value).brand(brand).build();
        brand.getProducts().add(product);
        return product;
    }

    public static Product a_product(final String name, final double price, final Sale sale, final Brand brand) {
        return a_product(null, name, price, sale, brand);
    }

    public enum Sale {ON_SALE(true), NO_SALE(false);

        private final boolean value;

        Sale(final boolean val) {
            this.value = val;
        }
    }
}
