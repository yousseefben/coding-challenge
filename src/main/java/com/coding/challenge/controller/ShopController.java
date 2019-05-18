package com.coding.challenge.controller;

import com.coding.challenge.dto.ApiResponse;
import com.coding.challenge.dto.CoordonneDto;
import com.coding.challenge.dto.LikeShopDto;
import com.coding.challenge.dto.ShopDistanceDto;
import com.coding.challenge.exception.ShopNotFoundException;
import com.coding.challenge.security.CurrentUser;
import com.coding.challenge.security.UserPrincipal;
import com.coding.challenge.service.PreferredShopService;
import com.coding.challenge.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);


    @Autowired
    private ShopService shopService;

    @Autowired
    private PreferredShopService preferredShopService;

    @GetMapping("/nearby")
    public ResponseEntity<List<ShopDistanceDto>> getNearbyShopsByDistance(@RequestParam("lat") double latitude,
                                                                          @RequestParam("long") double longitude,
                                                                          @CurrentUser UserPrincipal current)

    {
        CoordonneDto coordonne = new CoordonneDto(longitude, latitude);

        return ResponseEntity.ok(shopService.getNearbyShopByDistance(coordonne));
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<?> likeOrDislikeShop(@CurrentUser UserPrincipal currentUser, @PathVariable Long id,
                                               @Valid @RequestBody LikeShopDto likeShopDto)
    {
        try {
            return ResponseEntity.ok(preferredShopService.addPreferredShop(id, currentUser.getId(),
                    likeShopDto.isLike()));
        } catch (ShopNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shop Not Found");
        }
    }

    @DeleteMapping("/{id}/unlike")
    public ResponseEntity<?> unlikeShop(@CurrentUser UserPrincipal currentUser, @PathVariable Long id)
    {
        preferredShopService.deletePreferredShop(id, currentUser.getId());

        return ResponseEntity.ok(new ApiResponse(true, "Shop Unliked Successfully"));
    }
}
