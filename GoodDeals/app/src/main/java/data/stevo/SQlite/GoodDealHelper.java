package data.stevo.SQlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by stevo on 02/03/16.
 * Class permettant de creer la base de donnee
 */
public class GoodDealHelper extends SQLiteOpenHelper {
    /**
     * Constante contenant le nom de notre table
     */
    public static final String TABLE_OFFRES = "offres";
    /**
     * Constante representant le nom de la colonne id dans notre table
     */
    public static final String COLUMN_ID = "_id";
    /**
     * Constante representant le nom de la colonne titre dans notre table
     */
    public static final String COLUMN_TITRE = "titre";
    /**
     * Constante representant le nom de la colonne image dans notre table
     */
    public static final String COLUMN_IMAGE = "image";
    /**
     * Constante representant le nom de la colonne description dans notre table
     */
    public static final String COLUMN_DESCRIPTIOM = "description";
    /**
     * Constante representant le nom de la colonne categorie dans notre table
     */
    public static final String COLUMN_CATEGORIE = "categorie";
    /**
     * Constante representant le nom de la colonne magasin dans notre table
     */
    public static final String COLUMN_MAGASIN = "magasin";
    /**
     * Constante representant le nom de la colonne dateFin dans notre table
     */
    public static final String COLUMN_DATE_FIN = "dateFin";
    /**
     * Commande sql pour la création de la base de données
     */
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_OFFRES + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITRE + " TEXT, "
            + COLUMN_IMAGE + " TEXT, "
            + COLUMN_DESCRIPTIOM + " TEXT, "
            + COLUMN_CATEGORIE + " TEXT,"
            + COLUMN_MAGASIN + " TEXT,"
            + COLUMN_DATE_FIN + " TEXT); ";
    /**
     * Commande sql de suppression de notre table si elle existe
     */
    public static final String OFFRES_TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_OFFRES + ";";
    /**
     * Constante representant le nom de notre BDD
     */
    private static final String DATABASE_NAME = "goodDealDB.db";
    /**
     * Constante representant la version de notre base de donnée
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructeur prenant un context en parametre et permettant de creer notre base de donnée via le constructeur de la classe mere SQLiteOpenHelper
     */
    public GoodDealHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Methode appelee automatiquement pour la creaation de notre table offres
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    /**
     * Methode permettant de supprimer(ou mettre a jour) notre table et de la recreer en cas de changemet de version de la base de donnee de notre application dans le code.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(OFFRES_TABLE_DROP);
        onCreate(db);
    }

}