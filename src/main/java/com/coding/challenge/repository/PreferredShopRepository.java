package com.coding.challenge.repository;

import com.coding.challenge.model.PreferredShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PreferredShopRepository extends JpaRepository<PreferredShop, Long> {
    @Query("SELECT p FROM PreferredShop p WHERE p.user.id = :user_id AND p.shop.id = :shop_id")
    PreferredShop findPreferredShopByUserAndShop(@Param("user_id") Long userId, @Param("shop_id") Long shopId);

}
