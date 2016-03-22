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
    public Offres(String titre, String image, String description) {
        this.titre = titre;
        this.description = description;

    }

    //GETTERS et SETTERS

    public int getId() throws JSONException {
        try {
            id = jsonObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        try {
            titre = jsonObject.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Bitmap getBipmapImage() {
        try {
            bitmapImage = imageToJson.getBitmapFromString(jsonObject.getString("image"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bitmapImage;
    }

    public void setBipmapImage(String pictureData) {
        this.bitmapImage = imageToJson.getBitmapFromString(pictureData);
    }

    public String getDescription() {

       try {
            description = jsonObject.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {

        try {
            categorie = jsonObject.getString("category");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Date getDateFin() {

        try {
            dateFin = OffresDao.LongToDate(jsonObject.getLong("datefin"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getMagasin() {
        try {
            magasin = jsonObject.getString("magasin");
        } catch (JSONException e) {
            e.printStackTrace();
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

/**    public static byte[] drawableToByteArray(Drawable d) {

 if (d != null) {
 Bitmap imageBitmap = ((BitmapDrawable) d).getBitmap();
 ByteArrayOutputStream baos = new ByteArrayOutputStream();
 imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
 byte[] byteData = baos.toByteArray();

 return byteData;
 } else
 return null;

 }


 public static Drawable byteToDrawable(byte[] data) {

 if (data == null)
 return null;
 else
 return new BitmapDrawable(BitmapFactory.decodeByteArray(data, 0, data.length));
 }*/
}



