package com.coding.challenge.repository;

import com.coding.challenge.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    @Query("SELECT s FROM Shop s WHERE s.id NOT IN( SELECT s.id FROM Shop s, PreferredShop p WHERE p.user.id = :user_id AND s.id=p.shop.id AND (TIMESTAMPDIFF(HOUR,p.updatedAt, NOW()) < 2 OR p.like = false))")
    List<Shop> findShopsNotLikedOrDislikedbyUser(@Param("user_id") Long id);

    @Query("SELECT s FROM Shop s WHERE s.id IN(SELECT s.id FROM Shop s, PreferredShop p WHERE p.user.id = :user_id " +
            "AND s.id=p.shop.id AND p.like = true)")
    List<Shop> findLikedShopByUser(@Param("user_id") Long id);

}
