package com.Pfe.Comparateur.Repository;

import com.Pfe.Comparateur.Models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
}
