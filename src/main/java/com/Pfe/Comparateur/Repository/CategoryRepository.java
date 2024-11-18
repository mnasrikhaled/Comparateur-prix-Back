package com.Pfe.Comparateur.Repository;

import com.Pfe.Comparateur.Dto.ProductDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<ProductDto, String> {

}
