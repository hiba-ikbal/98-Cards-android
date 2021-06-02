package cherkaoui.ikbal.hiba.a98cards20;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Vector;

public class BDHelper extends SQLiteOpenHelper {
    private static BDHelper instance; // instance de notre singleton

    private SQLiteDatabase database;

    public static BDHelper getInstance(Context contexte)
    {
        if ( instance == null )
            instance = new BDHelper ( contexte);
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE score ( _id INTEGER PRIMARY KEY AUTOINCREMENT, score INTEGER);" );

    }
    public void ajouterScore ( Score i )
    {
        ContentValues cv = new ContentValues();
        cv.put("score", i.score);
        database.insert("score", null, cv );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table score");
        onCreate(db);
    }



    private BDHelper(@Nullable Context context) {
        super(context, "db", null, 1);
        ouvrirConnexionBD();
    }


    public void ouvrirConnexionBD()
    {
        database = this.getWritableDatabase();
    }

    public void fermerConnexionBD ()
    {
        database.close();
    }



}
