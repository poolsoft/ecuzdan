package com.poolsoft.ecuzdan;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class veritabani extends SQLiteOpenHelper {
	// Database, tablo ve tabodaki column adlari

	//public static final String DB_PATH = "/data/data/poolsoft/databases/";     
	public static final String DB_NAME = "eCuzdan.db";
	public static final int DB_VERSION = 1;
	
	public static final String TBL_SIFRELER = "CREATE TABLE sifreler (id INTEGER PRIMARY KEY AUTOINCREMENT, kul_adi VARCHAR(50) not null, kul_sifre VARCHAR(8) not null, aciklama VARCHAR(250));";
	public static final String TBL_HAREKETLER = "CREATE TABLE hareketler (hareket_id INTEGER PRIMARY KEY AUTOINCREMENT, kasa_id INTEGER not null, hesap_id INTEGER not null, hareket_turu VARCHAR(2) not null, tutar REAL not null, tarih DATE not null, bakiye REAL, aciklama VARCHAR(250));";
	public static final String TBL_HESAPLAR = "CREATE TABLE hesaplar (hesap_id INTEGER PRIMARY KEY AUTOINCREMENT, adi VARCHAR(50)  not null, soyadi VARCHAR(50) not null, tel VARCHAR(17), mail VARCHAR(250), aciklama VARCHAR(250));";
	public static final String TBL_KASA = "CREATE TABLE kasa (kasa_id INTEGER PRIMARY KEY AUTOINCREMENT, hesap_id INTEGER not null, kasa VARCHAR(50), gelir REAL, gider REAL, bakiye REAL);";		
	public static final String TBL_KULLANICI = "CREATE TABLE kullanici (kullanici_id INTEGER PRIMARY KEY AUTOINCREMENT, hesap_id INTEGER not null, kul_adi VARCHAR(50) not null, kul_sifre VARCHAR(8) not null);";
	public static final String TBL_KARTLAR = "CREATE TABLE kartlar (id INTEGER PRIMARY KEY AUTOINCREMENT, kart_adi VARCHAR(50) not null, Son_Odeme_Tar date not null, aciklama VARCHAR(250));";
	public static final String TBL_TAKSITLER = "CREATE TABLE taksitler (taksit_id INTEGER PRIMARY KEY AUTOINCREMENT, hesap_id INTEGER,kisi_adi VARCHAR(100) not null, kisi_tel VARCHAR(17) not null,  kisi_aciklama VARCHAR(250));";
	
	long usid;
	

	public static final String KISI_ADI = "AD";
	public static final String KISI_SOYAD = "SOYAD";
	public static final String KISI_YAS = "DTARIHI";
	public static final String KISI_DTARIHI = "DTARIHI";
	public static final String F_TEL1 = "TEL1";
	public static final String F_TEL2 = "TEL2";
	public static final String F_TEL3 = "TEL3";
	public static final String F_TEL4 = "TEL4";
	public static final String F_TEL5 = "TEL5";
	


	public veritabani(Context con){
		super (con,DB_NAME,null,DB_VERSION);
	}

	// table ve alanlarini olsuturuyor.Dikkat + ile string eklediginizde
	// oncekinden ayirmak
	// icin bosluk birazkin. Mesela sql="CREATE TABLE bosluk"+TABLE_NAME gibi
	@Override
	public void onCreate(SQLiteDatabase dbObject) {
						
		dbObject.execSQL(TBL_SIFRELER);
		Log.d("EventsData", "onCreate	: " + TBL_SIFRELER+" \n");			
		dbObject.execSQL(TBL_HAREKETLER);
		Log.d("EventsData", "onCreate	: " +TBL_HAREKETLER+" \n");		
		dbObject.execSQL(TBL_HESAPLAR);
		Log.d("EventsData", "onCreate	: " +TBL_HESAPLAR+" \n");
		dbObject.execSQL(TBL_KASA);
		Log.d("EventsData", "onCreate	: " +TBL_KASA+" \n");
		dbObject.execSQL(TBL_KARTLAR);
		Log.d("EventsData", "onCreate	: " + TBL_KARTLAR+" \n");
		
		dbObject.execSQL(TBL_KULLANICI);
		Log.d("EventsData", "onCreate	: " +TBL_KULLANICI+" \n");
		
		dbObject.execSQL(TBL_TAKSITLER);
		Log.d("EventsData", "onCreate	: " +TBL_TAKSITLER+" \n");
		
		String sql = "INSERT INTO sifreler (id, kul_adi,kul_sifre) VALUES (1,'ecuzdan','ecuzdan')";
		dbObject.execSQL(sql);
		Log.d("EventsData", "default	: " + sql);
		
		sql = "INSERT INTO hareketler (hareket_id,kasa_id,hesap_id,hareket_turu,tutar,tarih,bakiye,aciklama) VALUES (1,1,1,1,0,DATETIME('NOW'),0,'Açýlýþ iþlemi')";
		dbObject.execSQL(sql);
		Log.d("EventsData", "default	: " + sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion >= newVersion)
			return;

		  //db.execSQL("DROP TABLE IF EXISTS sifreler");
		  db.execSQL("DROP TABLE IF EXISTS kullanici");
		  
		  db.execSQL("DROP TABLE IF EXISTS kasa");
		  db.execSQL("DROP TABLE IF EXISTS hesaplar");
		  
		  db.execSQL("DROP TABLE IF EXISTS hareketler");


		  db.execSQL("DROP TABLE IF EXISTS kartlar");
		  db.execSQL("DROP TABLE IF EXISTS taksitler");
		  /*
		  db.execSQL("DROP TRIGGER IF EXISTS dept_id_trigger");
		  db.execSQL("DROP TRIGGER IF EXISTS dept_id_trigger22");
		  db.execSQL("DROP TRIGGER IF EXISTS fk_empdept_deptid");
		  db.execSQL("DROP VIEW IF EXISTS "+viewEmps);
		  */
		  onCreate(db);

	}
	
	
	
}