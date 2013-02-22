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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class giris_islemi extends Activity {
	
	private veritabani vt1;
    SQLiteDatabase db1;
    private String[] field_hareketler = {"hareket_id","kasa_id","hesap_id","hareket_turu","tutar","tarih","bakiye","aciklama"};

		  @Override
		  public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.giris_islemi);
		    /*
		    vt1 =new veritabani(this);

		    
		  //veritabaný nesnemizi oluþturuyoruz.
		   
		  final EditText txtadi=(EditText)findViewById(R.id.txtkasaadi);
		   
		  final EditText txtsoyadi=(EditText)findViewById(R.id.txtkasaadi);
		   
		  //txtadi ve txtsoyadi tanýmlanýyor.
		   
		  Button kaydetbuton=(Button)findViewById(R.id.kaydetbuton);
		   
		  //butonu tanýmlýyoruz.
		   
		  final TextView sonuc=(TextView)findViewById(R.id.sonuc);
		   
		  //gösterilecek sonuçlar için textview tanýmlanýyor.
		   
		  kaydetbuton.setOnClickListener(new View.OnClickListener() {
		   
		  public void onClick(View v) {
		   
		  // TODO Auto-generated method stub
		   
		  kayitekle(txtadi.getText().toString(),txtsoyadi.getText().toString());
		   
		  //kayitekle methodu çalýþtýrýlýyor, içerisine txtadi ve txtsoyadi parametreleri giriliyor.
		   
		  Cursor cursor = KayitGetir();
		   
		  //cursor tanýmlanýyor. (imleç) tablo içerisindeki verilerde dolaþmak için kullanýlan bi nesne
		   
		  KayitGoster(cursor);
		   
		  //kayitgoster methodu çaðýrýlýyor.
		   
		  }
		   
		  });
		  
		  */
		  }
		  
   public void kayitekle(String hareket_turu,String tutar)//kayitekle methodunu oluþturuyoruz

   {
		   
		  SQLiteDatabase db=vt1.getWritableDatabase();
		   
		  ContentValues veriler = new ContentValues();
		   
		  veriler.put("hareket_turu", hareket_turu);
		   
		  veriler.put("tutar",tutar);
		   
		  db.insertOrThrow("ogrenci", null, veriler);
		   
		   
		  }
   
   private Cursor KayitGetir(){
   	// {"hareket_id","kasa_id","hesap_id","hareket_turu","tutar","tarih","bakiye","aciklama"};
       db1 = vt1.getReadableDatabase();
       Cursor cursor = db1.query("hareketler", field_hareketler, "hareket_id=1", null, null, null, null);   
       startManagingCursor(cursor);
       return cursor;
       }

   private void KayitGoster(Cursor cursor){
             
   
   }
		
}