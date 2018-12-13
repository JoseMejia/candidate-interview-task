package com.epages.interview.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(exclude = "products")
@Entity
@Table(name = "BRAND", indexes = @Index(columnList = "brandName", unique = true)
)
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String brandName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
    private List<Product> products;
}
