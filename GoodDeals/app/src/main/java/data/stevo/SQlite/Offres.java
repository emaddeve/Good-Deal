package data.stevo.SQlite;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.util.Date;

/**
 * Created by stevo on 02/03/16.
 */
public class Offres {
    private int id;
    private String titre;
    private Drawable image;
    private String description;
    private String categorie;
    private String magasin;
    private Date datePublication;
    private Date dateFin;


    public Offres() {
    }

    public Offres(String titre,Drawable image, String description, String categorie, Date datePublication, Date dateFin, String magasin) {
        this.titre = titre;
        this.image = image;
        this.description = description;
        this.categorie = categorie;
        this.datePublication = datePublication;
        this.dateFin = dateFin;
        this.magasin = magasin;
    }

    //GETTERS
    public int getId() {
        return id;
    }

    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {

        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Date getDatePublication() {

        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public Date getDateFin() {

        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getMagasin() {
        return magasin;
    }

    public void setMagasin(String magasin) {
        this.magasin = magasin;
    }

    @Override
    public String toString() {
        return "L'offre " +  titre+   "ayant  la description: "   +description+
                  " est de la categorie " +  categorie+
                  " a ete prise dans le magasin: "+   magasin+
                  " le "+   datePublication+
                  " et s'acheve le "+   dateFin;
    }


    public void setImage(byte[] pictureData) {
        image = byteToDrawable(pictureData);
    }

    public byte[] getImage(){
        return drawableToByteArray(image);
    }
    public static byte[] drawableToByteArray(Drawable d) {

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
    }
}



