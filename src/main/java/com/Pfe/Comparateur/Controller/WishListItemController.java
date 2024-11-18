package com.Pfe.Comparateur.Controller;

import com.Pfe.Comparateur.Models.WishlistItem;
import com.Pfe.Comparateur.Service.Iml.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/wishlist")
public class WishListItemController {
    @Autowired
    private WishlistService wishlistService;

    @GetMapping("/{id}")
    public List<WishlistItem> getUserWishlist(@PathVariable String id) {
        return wishlistService.getUserWishlist(id);
    }

    @PostMapping("/{id}/add/{_id}")
    public WishlistItem addItemToWishlist(@PathVariable String id, @PathVariable String _id) {
        return wishlistService.addItemToWishlist(id, _id);
    }
}

