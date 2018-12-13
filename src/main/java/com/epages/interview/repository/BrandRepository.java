package com.epages.interview.repository;

import com.epages.interview.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findBrandByOrderByBrandNameAsc();

}
