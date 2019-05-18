package com.coding.challenge.model;

import javax.persistence.*;

@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String image;
    private double latitude;
    private double longitude;

    @ManyToOne
    @JoinColumn
    private PreferredShop preferredShop;


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

    public PreferredShop getPreferredShop() {
        return preferredShop;
    }

    public void setPreferredShop(PreferredShop preferredShop) {
        this.preferredShop = preferredShop;
    }
}
