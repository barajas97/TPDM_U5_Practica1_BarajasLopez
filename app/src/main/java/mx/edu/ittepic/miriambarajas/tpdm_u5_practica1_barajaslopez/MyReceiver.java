package mx.edu.ittepic.miriambarajas.tpdm_u5_practica1_barajaslopez;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    ConexionBD basecita;

    public void onReceive(Context context, Intent intent){
        basecita = new ConexionBD(context,"Marcas",null,1);
        insercion();
        Bundle mas = intent.getExtras();
        Object[] pdus = (Object[]) mas.get("pdus");
        SmsMessage msj = SmsMessage.createFromPdu((byte[])pdus[0]);
        if(msj.getMessageBody().startsWith("MK")){
            if(msj.getMessageBody().split("-").length==2){
                String m = msj.getMessageBody().split("-")[1];
                Toast.makeText(context, "Marca: "+m+": "+buscarMarca(m),Toast.LENGTH_LONG).show();
                enviarMSJ(msj.getOriginatingAddress(),"Marca: "+m+": "+buscarMarca(m),context);
            }
        }
        else{
            enviarMSJ(msj.getOriginatingAddress(),"Mensaje no reconocido, favor de verificar los datos",context);
        }
    }

    public String buscarMarca (String marca){
        try{
            SQLiteDatabase base = this.basecita.getReadableDatabase();
            String[] marcas ={marca};
            Cursor c = base.rawQuery("SELECT * FROM MARCAM WHERE NOMBRE =?",marcas);
            System.out.println(c.getCount());
            if(c.moveToFirst()){
                return (c.getString(1));
            }else{
                return ("No se encontraron coincidencias");
            }
        }catch (SQLiteException e){
            return (e.getMessage());
        }
    }

    private void enviarMSJ(String n, String m, Context c){
        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(n,null,m,null, null);
        }catch (Exception e){
            Toast.makeText(c, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void insercion(){
        SQLiteDatabase db = this.basecita.getWritableDatabase();
        db.execSQL("INSERT INTO MARCAM VALUES('ANASTASIA','VAULT COLLECTION 40% DESC, LABIALES 3X2')");
        db.execSQL("INSERT INTO MARCAM VALUES('AZTEC','PALETAS DE SOMBRAS P/OJOS 10%')");
        db.execSQL("INSERT INTO MARCAM VALUES('BADHABIT','TODA LA TIENDA AL 2 X 1 1/2')");
        db.execSQL("INSERT INTO MARCAM VALUES('BENEFIT','POLVOS COMPACTOS AL 20% DESC')");
        db.execSQL("INSERT INTO MARCAM VALUES('BHCOSMETICS','ENVÍO GRATIS SIN MINIMO DE COMPRA')");
        db.execSQL("INSERT INTO MARCAM VALUES('COLOURPOP','VAULT LIPSTICK 60% DESC')");
        db.execSQL("INSERT INTO MARCAM VALUES('CLINIQUE','RECIBE 7 REGALOS FULL SIZE EN CUALQUIER COMPRA')");
        db.execSQL("INSERT INTO MARCAM VALUES('DOSE','RECIBE UN LABIAL GRATIS EN COMPRAS MINIMAS A $500.00')");
        db.execSQL("INSERT INTO MARCAM VALUES('ECOTOOLS','BROCHAS Y ESPONJAS AL 30% DESC')");
        db.execSQL("INSERT INTO MARCAM VALUES('FARSALI','RECIBE GRATIS UN PRODUCTO MINI A ELEGIR')");
        db.execSQL("INSERT INTO MARCAM VALUES('GLOSSIER','ENVÍO GRATIS SIN MINIMO DE COMPRA')");
        db.execSQL("INSERT INTO MARCAM VALUES('HUDA','NUDE PALLETE AL 2 x 1 1/2')");
        db.execSQL("INSERT INTO MARCAM VALUES('INGLOT','REACTIVADOR DE MAQUILLAJE AL 50% DESC')");
        db.execSQL("INSERT INTO MARCAM VALUES('ITCOSMETICS','BBCREAM GRATIS EN CUALQUIER COMPRA')");
        db.execSQL("INSERT INTO MARCAM VALUES('JUVIAS','COMPRA 3 PALETAS Y RECIBE UNA MINI GRATIS (A ELEGIR)')");
        db.execSQL("INSERT INTO MARCAM VALUES('JEFFRE','MYSTERY BOX MINI GRATIS EN CUALQUIER COMPRA')");
        db.execSQL("INSERT INTO MARCAM VALUES('LIMECRIME','ENVÍO GRATIS SIN MINIMO DE COMPRA')");
        db.execSQL("INSERT INTO MARCAM VALUES('MAC','PREP+FIX MINI GRATIS EN COMPRAS MINIMAS A $300')");
        db.execSQL("INSERT INTO MARCAM VALUES('MORPHE','MORPHE x JAMES CHARLES CON 10% DESC')");
        db.execSQL("INSERT INTO MARCAM VALUES('NARS','BLUSH LIQUIDO MINI GRATIS EN CUALQUIER COMPRA')");
        db.execSQL("INSERT INTO MARCAM VALUES('PIXI','VAULT LIPSTICK COLLECTION 45% DESC')");
        db.execSQL("INSERT INTO MARCAM VALUES('RCMA','POLVO TRASLÚCIDO AL 2 x 1')");
        db.execSQL("INSERT INTO MARCAM VALUES('STILA','SOMBRAS LIQUIDAS SHIMMERS GRATIS EN COMPRAS MINIMAS A $1000')");
        db.execSQL("INSERT INTO MARCAM VALUES('WETNWILD','PHOTOFOCUS COLLECTION AL 2X1')");

    }
}
