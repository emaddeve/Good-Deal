package org.unicaen.dnr2i.GoodDealsWS.model;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.unicaen.dnr2i.GoodDealsWS.service.Base64;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class OfferManager {

    
    public void createAndStoreOffer(String location) throws IOException {
        Geometry geom = wktToGeometry(location);

        if (!geom.getGeometryType().equals("Point")) {
            throw new RuntimeException("Geometry must be a point. Got a " + geom.getGeometryType());
        }

        EntityManager em = JPAUtil.createEntityManager();

        em.getTransaction().begin();

        Offers offer = new Offers();
        offer.setCategory("food");
        offer.setDescription("for eat");
        offer.setName("meat");
        offer.setLocation((Point)geom);
        String encoded = Base64.encodeFromFile("/home/emad/Desktop/Desktop/img/emadooo.jpg");
        offer.setImageString(encoded);
        em.persist(offer);
        em.getTransaction().commit();
        em.close();
    }

    private Geometry wktToGeometry(String wktPoint) {
        WKTReader fromText = new WKTReader();
        Geometry geom = null;
        try {
            geom = fromText.read(wktPoint);
        } catch (ParseException e) {
            throw new RuntimeException("Not a WKT string:" + wktPoint);
        }
        return geom;
    }

    /**
     * Utility method to assemble all arguments save the first into a String
     */
    private static String assemble(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            builder.append(args[i]).append(" ");
        }
        return builder.toString();
    }

}