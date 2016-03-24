package data.stevo.SQlite;

import android.graphics.Bitmap;

import com.emad.gooddeals.ImageToJson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by stevo on 02/03/16.
 * Classe representant une offre
 */
public class Offres {
    private int id;
    private String titre;
    private Bitmap bitmapImage;
    private String description;
    private String categorie;
    private String magasin;
    private Date dateFin;
    private JSONObject jsonObject;
    private ImageToJson imageToJson=new ImageToJson();

    public Offres(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public Offres() {

    }
    /**
     *constructeur de test
     */
    public Offres(String titre, String image, String description,Date date) {
        this.titre = titre;
        this.description = description;
        this.dateFin=date;

    }

    //GETTERS et SETTERS

    public int getId() throws JSONException {
        if (jsonObject==null){
            return id
                    ;
        }
        else{
        try {
            id = jsonObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        }
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        if (jsonObject==null){
            return titre;
        }
        else{
        try {
                titre = jsonObject.getString("name");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        }
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Bitmap getBipmapImage() {
        if (jsonObject==null){
            return bitmapImage;
        }else{
        try {
            bitmapImage = imageToJson.getBitmapFromString(jsonObject.getString("imageString"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        }
        return bitmapImage;
    }

    public void setBipmapImage(String pictureData) {
        this.bitmapImage = imageToJson.getBitmapFromString(pictureData);
    }

    public String getDescription() {
        if (jsonObject==null){
            return description;
        }
        else{
       try {
            description = jsonObject.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        if (jsonObject==null){
            return categorie;
        }
        else{
        try {
            categorie = jsonObject.getString("category");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        }
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Date getDateFin() {
        if (jsonObject==null){
            return dateFin;
        }
        else{
        try {
            dateFin = OffresDao.convertStringToDate(jsonObject.getString("datefin"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        }
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getMagasin() {
        if (jsonObject==null){
            return magasin;
        }
        else{
        try {
            magasin = jsonObject.getString("magasin");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        }
        return titre;
    }

    public void setMagasin(String magasin) {
        this.magasin = magasin;
    }

    @Override
    public String toString() {
        return "L'offre " + titre + "ayant  la description: " + description +
                " est de la categorie " + categorie +
                " a ete prise dans le magasin: " + magasin  +
                " et s'acheve le " + dateFin;
    }
}



