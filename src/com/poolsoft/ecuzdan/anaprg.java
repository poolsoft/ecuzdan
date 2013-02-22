package com.poolsoft.ecuzdan;

import java.util.ArrayList;
import java.util.HashMap;

import com.poolsoft.util.LazyAdapter;
import com.poolsoft.util.MyHakkinda;
import com.poolsoft.ecuzdan.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.*;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.poolsoft.ecuzdan.veritabani;
import com.poolsoft.ecuzdan.anaekran;


public class anaprg extends Activity {
	
	static final String KEY_ACIKLAMA = "Aciklama"; // parent node
	static final String KEY_ID = "id";
	static final String KEY_TARIH = "Tarih";
	static final String KEY_HAREKET = "Hareket";
	static final String KEY_TIP = "Tip";
	static final String KEY_TUTAR = "Tutar";
	
	private static final int DIALOG_REALLY_EXIT_ID = 0;
	
	private anaekran anaekran;

	private ListView lv = null;
	
	private String[] field_hareketler = {"hareket_id","kasa_id","hesap_id","hareket_turu","tutar","tarih","bakiye","aciklama"};
	
	private ArrayList<HashMap<String, String>> mylist1 = new ArrayList<HashMap<String, String>>();
	private HashMap<String, String> map1 = new HashMap<String, String>();
	public static  boolean Kapansinmi = false;
	
	
	
	private veritabani vt1;
    SQLiteDatabase db1;
	
	ListView list;
    LazyAdapter adapter;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.anaprg);
		setTitle("eCuzdan [Kasa Görünüm]");
		
		ListView lv = (ListView) findViewById(R.id.listView1);
		
		vt1 = new veritabani(this);
		db1 = vt1.getReadableDatabase();
        Cursor cursor = db1.query("hareketler", field_hareketler, "hareket_id=1", null, null, null, null);   
        startManagingCursor(cursor);
        
        
        ArrayList<HashMap<String, String>> mylist1 = new ArrayList<HashMap<String, String>>();  
    	map1 = new HashMap<String, String>();
    	
    	while(cursor.moveToNext()){  
        		map1.put("Açýklama", cursor.getString((cursor.getColumnIndex("aciklama"))));
        		map1.put("Hareket", cursor.getString((cursor.getColumnIndex("hareket_turu"))));
        		map1.put("Tarih", cursor.getString((cursor.getColumnIndex("tarih"))));
        		map1.put("Tip", "B");
        		map1.put("Tutar", cursor.getString((cursor.getColumnIndex("tutar"))));
        		mylist1.add(map1);
            }

          	    
    		SimpleAdapter adapter = new SimpleAdapter(this, mylist1, R.layout.list_row,new String[] {KEY_ACIKLAMA, KEY_TARIH,KEY_HAREKET,KEY_TIP,KEY_TUTAR}, new int[] {R.id.txt_aciklama,R.id.txt_tarih, R.id.HAREKET, R.id.TIP, R.id.txt_tutar});
  
    		lv.setAdapter(adapter);
    		
    		lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);	
        
		
        
		//db_yenile();
		
//		anaekran.myToast("Bilgileri Alýnýyor.");
/*		
		 vt1 = new veritabani(this);		
		 Cursor cursor = KayitGetir();
         KayitGoster(cursor); 
		
		
				
		
        StringBuilder builder = new StringBuilder("Kayitlar:\n");
        
		for (int i=1;i<=30;i++){
		map1 = new HashMap<String, String>();
		
		if (i <= 9)
			map1.put("Tarih", "0"+i+".09.2011");
		else
			map1.put("Tarih", i+".09.2011");
		
		
		map1.put("Aciklama", i+ " nolu kart ödemesi");
				
		if (i % 2 == 0)
			map1.put("Tip", "B");
		else
			map1.put("Tip", "A");
		
		
		map1.put("Tutar", i+"00 TL");
		mylist1.add(map1);
		}
		
		System.out.println(mylist1.toString());
		
		SimpleAdapter adapter = new SimpleAdapter(this, mylist1, R.layout.listbox,new String[] {KEY_ACIKLAMA, KEY_HAREKET, KEY_TARIH,KEY_TIP,KEY_TUTAR}, new int[] {R.id.ACIKLAMA, R.id.HAREKET, R.id.TARIH,R.id.TIP, R.id.TUTAR});
		//adapter = new LazyAdapter(this, songsList);  
		
		lv.setAdapter(adapter);
		lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
*/
	}
/*	  
@Override
public void onStart()
	{
		try
		{
		super.onStart();
			
		//Cursor cursor = KayitGetir();
        //KayitGoster(cursor); 	

		//never close cursor
		}
		catch(Exception ex)
		{
			CatchError(ex.toString());
		}
	}
*/
	
void CatchError(String Exception)
{
	Dialog diag=new Dialog(this);
	diag.setTitle("Hata!");
	TextView txt=new TextView(this);
	txt.setText(Exception);
	diag.setContentView(txt);
	diag.show();
}

		@Override
		public boolean onCreateOptionsMenu (Menu menu2){
		        super.onCreateOptionsMenu(menu2);
		        MenuInflater menutanim2 = getMenuInflater();
		        menutanim2.inflate(R.menu.prgmenu, menu2);
		        return true;
		        }
		 
		    @Override
			public boolean onOptionsItemSelected(MenuItem item2){
		        switch (item2.getItemId()){
		        
		        case R.id.menuprg_GirisEkle:
		            startActivity (new Intent(anaprg.this,giris_islemi.class));
		        return true;  
		        
		        case R.id.menuprg_CikisEkle:
		            startActivity (new Intent(anaprg.this,cikis_islemi.class));
		        return true;  
		        
		        case R.id.menuprg_Raporlar:
		            startActivity (new Intent(anaprg.this,ayarlar.class));
		        return true;  

		        case R.id.menuprg_hakkinda:
		        	MyHakkinda myhk = new MyHakkinda(); 
		        	myhk.myHakkinda(anaprg.this);
		        return true;
		            
		        case R.id.menuprg_ayarlar:
		            startActivity (new Intent(anaprg.this,ayarlar.class));
		        return true;    
		        
		    	case R.id.menuprg_kapat:
		    		showDialog(DIALOG_REALLY_EXIT_ID);
		    	    
		    	return true;    
        		
		        }
		        return false;
		    }
		    
		    
		 // creates Dialogs for this Activity
		    @Override
		    protected Dialog onCreateDialog(int id) {
		        final Dialog dialog;
		        switch(id) {
		        case DIALOG_REALLY_EXIT_ID:
		            dialog = new AlertDialog.Builder(this).setMessage(
		                                "Çýkmak istediðinize eminmisiniz?")
		            .setCancelable(false)
		            .setPositiveButton("Evet",
		                    new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int id) {
		                			                	
		                	//System.exit(0);
		                    //android.os.Process.killProcess(android.os.Process.myPid());
		                	finish();
		                }
		            })
		            .setNegativeButton("Hayýr",
		                    new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int id) {
		                    dialog.cancel();
		                }
		            }).create();
		            break;
		        default:
		            dialog = null;
		        }
		        return dialog;
		    }
		 
		    // manages key presses not handled in other Views from this Activity
		    @Override
		    public boolean onKeyDown(int keyCode, KeyEvent event) {
		        if (keyCode == KeyEvent.KEYCODE_BACK)
		            showDialog(DIALOG_REALLY_EXIT_ID);
		 
		        if (keyCode == KeyEvent.KEYCODE_MENU)
		        	openOptionsMenu();
		        
		        if (keyCode == KeyEvent.KEYCODE_SEARCH)
		        	openOptionsMenu();

	
		  	        
		        return true;
		        // use this instead if you want to preserve onKeyDown() behavior
		        // return super.onKeyDown(keyCode, event);
		    }	

		    
		    
		    
}


