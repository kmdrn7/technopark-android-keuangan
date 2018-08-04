package com.andikaahmad.aplikasikeuangan.Config;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// ALT + ENTER
public class Database extends SQLiteOpenHelper {

    final static String DATABASE_NAME = "AplikasiKeuangan";
    final static Integer VERSION = 1;
    Context context;
    SQLiteDatabase db;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, VERSION);

        this.context = context;
        this.db = this.getWritableDatabase() ;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean execution(String query){
        try{
            db.execSQL(query);

            return true ;
        }catch (Exception e){
            return false ;
        }
    }

    public Cursor select(String query){
        try{
            Cursor c = db.rawQuery(query, null) ;

            return c ;
        }catch (Exception e){
            return null ;
        }
    }

    public Integer countRows(String query){
        try{
            return select(query).getCount() ;
        }catch (Exception e){
            return 0 ;
        }
    }

    public boolean createTable(){
        try{
            String tblidentitas = "CREATE TABLE IF NOT EXISTS `tblidentitas` (" +
                    "`nama`TEXT," +
                    "`alamat`TEXT," +
                    "`notelp`TEXT," +
                    "`caption1`TEXT," +
                    "`caption2`TEXT," +
                    "`caption3`TEXT" +
                    ");" ;
            String tbltransaksi = "CREATE TABLE IF NOT EXISTS `tbltransaksi` (" +
                    "`idtransaksi`INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "`faktur`TEXT," +
                    "`tgltransaksi`TEXT," +
                    "`debet`REAL," +
                    "`kredit`REAL," +
                    "`saldo`REAL," +
                    "`keterangan`TEXT," +
                    "`notransaksi`INTEGER," +
                    "`kelompok`TEXT," +
                    "`nama`TEXT," +
                    "`tglmix`INTEGER" +
                    ");" ;

            if (execution(tblidentitas) && execution(tbltransaksi)){
                return true ;
            } else {
                return false ;
            }
        }catch (Exception e){
            return false ;
        }
    }
}
