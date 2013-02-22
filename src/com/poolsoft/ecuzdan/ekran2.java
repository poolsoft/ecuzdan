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


public class ekran2 extends Activity {
	
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
		
		setContentView(R.layout.ekran2);
		
		setTitle("Ekran2");
		
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
        
		
        
	}

	
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
		        menutanim2.inflate(R.menu.ekran2menu, menu2);
		        return true;
		        }
		 
		    @Override
			public boolean onOptionsItemSelected(MenuItem item2){
		        switch (item2.getItemId()){
		        
		        case R.id.ekran2_GirisEkle:
		            startActivity (new Intent(ekran2.this,giris_islemi.class));
		        return true;  
		        
		        case R.id.ekran2_CikisEkle:
		            startActivity (new Intent(ekran2.this,cikis_islemi.class));
		        return true;  
		        
		        case R.id.ekran2_Raporlar:
		            startActivity (new Intent(ekran2.this,ayarlar.class));
		        return true;  

		        case R.id.ekran2_hakkinda:
		        	MyHakkinda myhk = new MyHakkinda(); 
		        	myhk.myHakkinda(ekran2.this);
		        return true;
		            
		        case R.id.ekran2_ayarlar:
		            startActivity (new Intent(ekran2.this,ayarlar.class));
		        return true;    
		        
		    	case R.id.ekran2_kapat:
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


