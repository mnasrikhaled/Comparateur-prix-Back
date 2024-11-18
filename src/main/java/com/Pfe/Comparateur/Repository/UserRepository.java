package com.Pfe.Comparateur.Repository;
import com.Pfe.Comparateur.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findById(String id);

    void deleteById(String id);

    Optional<User> findByPassword(String password);

    boolean existsByPassword(String password);

//    List<User_alert> findByBudgetGreaterThan(double budget);



//     List<User> getBudget(User budget);

}
