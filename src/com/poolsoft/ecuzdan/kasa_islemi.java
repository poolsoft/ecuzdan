package com.poolsoft.ecuzdan;

import com.poolsoft.ecuzdan.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class kasa_islemi extends Activity {
	
	private veritabani vt1;
    SQLiteDatabase db1;
    private String[] field_hareketler = {"hareket_id","kasa_id","hesap_id","hareket_turu","tutar","tarih","bakiye","aciklama"};

		  @Override
		  public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.kasa_islemi);
		    
		    vt1 =new veritabani(this);

		    
		  //veritabanı nesnemizi oluşturuyoruz.
		   
		  final EditText txtadi=(EditText)findViewById(R.id.txtkasaadi);
		   
		  final EditText txtsoyadi=(EditText)findViewById(R.id.txtkasaaciklama);
		   
		  //txtadi ve txtsoyadi tanımlanıyor.
		   
		  Button kaydetbuton=(Button)findViewById(R.id.kaydetbuton);
		   
		  //butonu tanımlıyoruz.
		   
		  final TextView sonuc=(TextView)findViewById(R.id.sonuc);
		   
		  //gösterilecek sonuçlar için textview tanımlanıyor.
		   
		  kaydetbuton.setOnClickListener(new View.OnClickListener() {
		   
		  public void onClick(View v) {
		   
		  // TODO Auto-generated method stub
		   
		  kayitekle(txtadi.getText().toString(),txtsoyadi.getText().toString());
		   
		  //kayitekle methodu çalıştırılıyor, içerisine txtadi ve txtsoyadi parametreleri giriliyor.
		   
		 // Cursor cursor = KayitGetir();
		   
		  //cursor tanımlanıyor. (imleç) tablo içerisindeki verilerde dolaşmak için kullanılan bi nesne
		   
		 /// KayitGoster(cursor);
		   
		  //kayitgoster methodu çağırılıyor.
		   
		  }
		   
		  });
		  
		  
		  }
		  
   public void kayitekle(String hareket_turu,String tutar)//kayitekle methodunu oluşturuyoruz

   {
		   
		  SQLiteDatabase db=vt1.getWritableDatabase();
		   
		  ContentValues veriler = new ContentValues();
		   
		  veriler.put("hareket_turu", hareket_turu);
		   
		  veriler.put("tutar",tutar);
		   
		  db.insertOrThrow("ogrenci", null, veriler);
		   
		   
		  }
		
}