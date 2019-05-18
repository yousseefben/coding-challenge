package com.coding.challenge.mapper;

import com.coding.challenge.dto.CoordonneDto;
import com.coding.challenge.dto.ShopDistanceDto;
import com.coding.challenge.model.Shop;
import com.coding.challenge.utils.ShopUtils;

public class ShopMapper {

    public static ShopDistanceDto mapShopToShopDto(Shop shop, CoordonneDto coordonneDto) {
        ShopDistanceDto shopDistanceDto = new ShopDistanceDto();

        shopDistanceDto.setName(shop.getName());
        shopDistanceDto.setImage(shop.getImage());
        shopDistanceDto.setDistance(ShopUtils.distance(shop.getLatitude(), coordonneDto.getLatitude(), shop.getLongitude(),
                coordonneDto.getLongitude()));

        return shopDistanceDto;
    }
}
