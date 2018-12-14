package com.epages.interview.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
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
