package com.hiep.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.hiep.models.Product;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {}
