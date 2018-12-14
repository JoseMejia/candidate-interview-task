package com.epages.interview.representation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder(toBuilder = true)
public class ProductRepresentation {

    private long id;
    private String name;
    private BigDecimal price;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String event;
}
