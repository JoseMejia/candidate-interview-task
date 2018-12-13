package com.epages.interview.representation;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ProductRepresentation {

    private long id;
    private String name;
    private double price;
    private String event;
}
