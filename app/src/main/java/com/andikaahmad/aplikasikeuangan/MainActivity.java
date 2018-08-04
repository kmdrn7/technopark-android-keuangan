package com.andikaahmad.aplikasikeuangan;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.andikaahmad.aplikasikeuangan.Config.Database;

public class MainActivity extends AppCompatActivity {

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        db = new Database(this) ;

        createTable();
        setText() ;
        askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,0x3);
    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission)) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            }
        }
    }

    public void setText(){
        String sql = "SELECT * FROM tblidentitas" ;
        TextView title1 = (TextView) findViewById(R.id.title1);
        TextView title2 = (TextView) findViewById(R.id.title2);

        if (db.countRows(sql) > 0){
            Cursor c = db.select(sql) ;
            c.moveToNext();

            title1.setText(c.getString(c.getColumnIndex("nama")));
            title2.setText(c.getString(c.getColumnIndex("alamat")));
        }
    }

    public void reset(View v){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this) ;
        alertBuilder.setMessage("Apakah anda yakin akan mereset database ?") ;
        alertBuilder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                reset2();
            }
        }) ;
        alertBuilder.setNegativeButton("Tidak",null) ;
        AlertDialog alertDialog = alertBuilder.create() ;
        alertDialog.show();
    }

    public void reset2(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this) ;
        alertBuilder.setMessage("Apakah anda Setuju akan mereset database ?") ;
        alertBuilder.setPositiveButton("Tidak", null) ;
        alertBuilder.setNegativeButton("Setuju",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                resetAkhir() ;
            }
        }) ;
        AlertDialog alertDialog = alertBuilder.create() ;
        alertDialog.show();
    }

    public void resetAkhir(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this) ;
        alertBuilder.setMessage("Apakah anda yakin akan mereset database ?") ;
        alertBuilder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String sql = "delete from tbltransaksi" ;
                if (db.execution(sql)){
                    Toast.makeText(MainActivity.this, "Berhasil reset database. Silahkan restart aplikasi", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Gagal reset database", Toast.LENGTH_SHORT).show();
                }
            }
        }) ;
        alertBuilder.setNegativeButton("Tidak",null) ;
        AlertDialog alertDialog = alertBuilder.create() ;
        alertDialog.show();
    }

    public void createTable(){
        if (!db.createTable()){
            Toast.makeText(this, "Tabel dan Database gagal dibuat!", Toast.LENGTH_SHORT).show();
        }
    }

    public void master(View view) {
//        Intent intent = new Intent(this,ActivityMaster.class) ;
//        startActivity(intent);
    }

    public void transaksi(View v){
//        Intent intent = new Intent(this,ActivityTransaksi.class) ;
//        startActivity(intent);
    }

    public void backup(View v){
//        Intent intent = new Intent(this,ActivityBackup.class) ;
//        startActivity(intent);
    }

    public void restore(View v){
//        Intent intent = new Intent(this,ActivityRestore.class) ;
//        startActivity(intent);
    }

    public void laporan(View v){
//        Intent intent = new Intent(this, ActivityLaporan.class) ;
//        startActivity(intent);
//        Intent i = new Intent(this, ActivityCetak.class) ;
//        i.putExtra("faktur","000000001") ;
//        startActivity(i);
    }

    public void petunjuk(View v){
//        Intent i = new Intent(this, ActivityPetunjuk.class) ;
//        startActivity(i);
    }
//
    public void guide(View v){
//        Intent i = new Intent(this, ActivityPenggunaan.class) ;
//        startActivity(i);
    }

    public void tentang(View v){
//        Intent i = new Intent(this, ActivityTentang.class) ;
//        startActivity(i);
    }
}
