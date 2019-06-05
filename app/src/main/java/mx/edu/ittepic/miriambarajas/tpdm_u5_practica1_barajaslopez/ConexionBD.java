package mx.edu.ittepic.miriambarajas.tpdm_u5_practica1_barajaslopez;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionBD extends SQLiteOpenHelper {
    public ConexionBD (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE MARCAM(NOMBRE VARCHAR(200),DESCUENTO VARCHAR(600))");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
