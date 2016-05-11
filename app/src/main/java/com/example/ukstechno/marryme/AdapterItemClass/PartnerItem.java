package com.example.ukstechno.marryme.AdapterItemClass;

import java.util.ArrayList;

/**
 * Created by verma on 3/29/2016.
 */
public class PartnerItem {
    private String uri;
    private String firstname;
    private String lastname;
    private String email;
    private String city;
    private String contact;

    public PartnerItem() {
    }

  /*  public PartnerItem(String name, String thumbnailUrl, String lastname, String city, String email, String contact)
                        {
        this.firstname = name;
        this.uri = thumbnailUrl;
        this.lastname = lastname;
        this.city = city;
        this.email = email;
        this.contact = contact;
    }*/

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


}
