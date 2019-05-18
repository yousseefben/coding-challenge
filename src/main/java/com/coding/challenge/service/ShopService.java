package com.coding.challenge.service;

import com.coding.challenge.dto.CoordonneDto;
import com.coding.challenge.dto.ShopDistanceDto;
import com.coding.challenge.mapper.ShopMapper;
import com.coding.challenge.model.Shop;
import com.coding.challenge.repository.ShopRepository;
import com.coding.challenge.utils.ShopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopService {

    private final double NEARBY_DISTANCE = 100.0;

    @Autowired
    private ShopRepository shopRepository;

    public List<ShopDistanceDto> getNearbyShop(CoordonneDto coordonnee) {

        return shopRepository.findAll().stream()
                .filter(shop ->
                        getDistanceUserAndShop(shop, coordonnee) <= NEARBY_DISTANCE
                ).map(shop -> ShopMapper.mapShopToShopDto(shop, coordonnee))
                .sorted(Comparator.comparingDouble(ShopDistanceDto::getDistance))
                .collect(Collectors.toList());

    }

    private double getDistanceUserAndShop(Shop shop, CoordonneDto coordonneDto) {
        return ShopUtils.distance(shop.getLatitude(), coordonneDto.getLatitude(), shop.getLongitude(),
                coordonneDto.getLongitude());
    }

}
