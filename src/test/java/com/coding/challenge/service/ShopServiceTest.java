package com.coding.challenge.service;

import com.coding.challenge.dto.CoordonneDto;
import com.coding.challenge.dto.ShopDistanceDto;
import com.coding.challenge.model.Shop;
import com.coding.challenge.repository.ShopRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ShopServiceTest {

    @Mock
    private ShopRepository shopRepository;

    @InjectMocks
    private ShopService shopService;

    @Test
    public void getNearbyShopTest() {

        List<Shop> shopDtos = new ArrayList<>();

        Shop shop = new Shop("title 1", "image 1", -6.846833, 33.996452);
        shopDtos.add(shop);
        shop = new Shop("title 2", "image 2", -1.846833, 2.996452);
        shopDtos.add(shop);

        when(shopRepository.findAll()).thenReturn(shopDtos);

        List<ShopDistanceDto> result = shopService.getNearbyShop(new CoordonneDto(33.99679, -6.846654));

        assertEquals(1, result.size());
    }
}