package com.Pfe.Comparateur.Repository;

import com.Pfe.Comparateur.Models.ProductC;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCRepository extends MongoRepository<ProductC, String> {
    Optional<ProductC> findById(String _id);

}
