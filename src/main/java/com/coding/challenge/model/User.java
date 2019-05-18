package com.coding.challenge.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private PreferredShop preferredShop;


    public User(@NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 100) String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PreferredShop getPreferredShop() {
        return preferredShop;
    }

    public void setPreferredShop(PreferredShop preferredShop) {
        this.preferredShop = preferredShop;
    }
}
