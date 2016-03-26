package com.emad.gooddeals;

/**
 * Created by emad on 25/03/16.
 */
import data.stevo.SQlite.Offres;
public class Friends {
    Offres offer;
    String name;
    public Friends (String name,Offres offer){
        this.name=name;
        this.offer=offer;
    }

    public String getName() {
        return name;
    }

    public Offres getOffer() {
        return offer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOffer(Offres offer) {
        this.offer = offer;
    }
}
