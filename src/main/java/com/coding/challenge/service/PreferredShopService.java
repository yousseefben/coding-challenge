package com.coding.challenge.service;


import com.coding.challenge.exception.ShopNotFoundException;
import com.coding.challenge.model.PreferredShop;
import com.coding.challenge.model.Shop;
import com.coding.challenge.model.User;
import com.coding.challenge.repository.PreferredShopRepository;
import com.coding.challenge.repository.ShopRepository;
import com.coding.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PreferredShopService {

    @Autowired
    private PreferredShopRepository preferredShopRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private UserRepository userRepository;

    public PreferredShop addPreferredShop(Long shopId, Long userId, boolean like) throws ShopNotFoundException {
        Shop shop = shopRepository.findById(shopId).orElseThrow(ShopNotFoundException::new);

        User user = userRepository.findById(userId).orElseThrow(() ->
                new UsernameNotFoundException("User not found with Id : " + userId)
        );
        PreferredShop preferredShop = new PreferredShop();
        preferredShop.setLike(like);
        preferredShop.setShop(shop);
        preferredShop.setUser(user);

        return preferredShopRepository.save(preferredShop);
    }

    public void deletePreferredShop(Long shopId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UsernameNotFoundException("User not found with id : " + userId)
        );
        PreferredShop preferredShop = preferredShopRepository.findPreferredShopByUserAndShop(user.getId(), shopId);

        preferredShopRepository.delete(preferredShop);
    }

}

