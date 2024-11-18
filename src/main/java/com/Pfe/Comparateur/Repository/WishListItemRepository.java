package com.Pfe.Comparateur.Repository;

import com.Pfe.Comparateur.Models.Product;
import com.Pfe.Comparateur.Models.User_alert;
import com.Pfe.Comparateur.Models.WishlistItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface WishListItemRepository extends MongoRepository<WishlistItem, String> {


    void deleteById(String wishlistItemId);

    List<WishlistItem> findByUserId(String id);
}
