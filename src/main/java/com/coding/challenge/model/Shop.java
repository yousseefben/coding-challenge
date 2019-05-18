package com.coding.challenge.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String image;
    private double latitude;
    private double longitude;


    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private Set<PreferredShop> preferredShops;

    public Shop() {
    }

    public Shop(String name, String image, Double latitude, Double longitude) {
        this.name = name;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Set<PreferredShop> getPreferredShops() {
        return preferredShops;
    }

    public void setPreferredShops(Set<PreferredShop> preferredShops) {
        this.preferredShops = preferredShops;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
