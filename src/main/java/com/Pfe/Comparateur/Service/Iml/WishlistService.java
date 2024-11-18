package com.Pfe.Comparateur.Service.Iml;

import com.Pfe.Comparateur.Models.WishlistItem;

import java.util.List;

public interface WishlistService {
    List<WishlistItem> getUserWishlist(String id);
    WishlistItem addItemToWishlist(String id, String _id);
}
