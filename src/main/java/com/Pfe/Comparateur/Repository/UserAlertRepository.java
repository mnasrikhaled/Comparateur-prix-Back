package com.Pfe.Comparateur.Repository;

import com.Pfe.Comparateur.Models.User_alert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserAlertRepository extends MongoRepository<User_alert, String> {

}
