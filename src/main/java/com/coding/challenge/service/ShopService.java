package com.coding.challenge.service;

import com.coding.challenge.dto.CoordonneDto;
import com.coding.challenge.dto.ShopDistanceDto;
import com.coding.challenge.mapper.ShopMapper;
import com.coding.challenge.model.Shop;
import com.coding.challenge.model.User;
import com.coding.challenge.repository.ShopRepository;
import com.coding.challenge.repository.UserRepository;
import com.coding.challenge.utils.ShopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopService {

    private final double NEARBY_DISTANCE = 100.0;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ShopDistanceDto> getNearbyShop(CoordonneDto coordonnee) {

        return shopRepository.findAll().stream()
                .filter(shop ->
                        getDistanceUserAndShop(shop, coordonnee) <= NEARBY_DISTANCE
                ).map(shop -> ShopMapper.mapShopToShopDto(shop, coordonnee))
                .collect(Collectors.toList());
    }

    public List<ShopDistanceDto> getNearbyShopByDistance(CoordonneDto coordonnee) {
        return getNearbyShop(coordonnee).stream()
                .sorted(Comparator.comparingDouble(ShopDistanceDto::getDistance))
                .collect(Collectors.toList());
    }

    public List<Shop> getLikedShopByUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email : " + email)
        );

        return shopRepository.findLikedShopByUser(user.getId());
    }


    private double getDistanceUserAndShop(Shop shop, CoordonneDto coordonneDto) {
        return ShopUtils.distance(shop.getLatitude(), coordonneDto.getLatitude(), shop.getLongitude(),
                coordonneDto.getLongitude());
    }

}
