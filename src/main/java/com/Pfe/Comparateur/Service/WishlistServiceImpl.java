package com.Pfe.Comparateur.Service;

import com.Pfe.Comparateur.Models.Product;
import com.Pfe.Comparateur.Models.User;
import com.Pfe.Comparateur.Models.WishlistItem;
import com.Pfe.Comparateur.Repository.WishListItemRepository;
import com.Pfe.Comparateur.Service.Iml.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishListItemRepository wishListItemRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @Override
    public List<WishlistItem> getUserWishlist(String id) {
        return wishListItemRepository.findByUserId(id);
    }


    @Override
    public WishlistItem addItemToWishlist(String id, String _id) {
        // Récupérer l'utilisateur et le produit correspondant aux IDs fournis
        User user = userService.getUserById(id);
        Optional<Product> productOptional = productService.getProductById(_id);

        if (user == null || !productOptional.isPresent()) {
            return null;
        }

        Product product = productOptional.get(); // Extraction de la valeur du Optional

        // Créer un nouvel objet WishlistItem
        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setUser(user);
        wishlistItem.setProduct(product);

        // Enregistrer l'objet WishlistItem dans la base de données
        return wishListItemRepository.save(wishlistItem);
    }

}

