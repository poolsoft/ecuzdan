package com.poolsoft.ecuzdan;

import android.app.Activity;
import android.app.Dialog;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;

import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.poolsoft.ecuzdan.veritabani;

import com.poolsoft.ecuzdan.R;
import com.poolsoft.util.MyHakkinda;


public class anaekran extends Activity implements OnClickListener {
    private Button btn_giris;
    private Button btn_iptal;
    private CheckBox cb_sifregoster;

    private String db_kul_sifre = "";
    private TextView kul_adi = null;	
    private TextView kul_pass = null;
    private veritabani vt;
    SQLiteDatabase db;
	
	private String[] SELECT = {"id", "kul_adi", "kul_sifre"};
	
	//************************************************************
	
	//******************************************************
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anaekran);
        
        vt = new veritabani(this);
        
        btn_giris = (Button)findViewById(R.id.main_btn_giris);
        btn_iptal = (Button)findViewById(R.id.main_btn_iptal);
        cb_sifregoster = (CheckBox)findViewById(R.id.main_cb_sifregoster);

		kul_pass = (TextView)findViewById(R.id.main_txt_kull_sifre);
        
		btn_giris.setOnClickListener(this);
		btn_iptal.setOnClickListener(this);
		cb_sifregoster.setOnClickListener(this);
		kul_pass.requestFocus();
		
  }

	@Override
	public void onClick(View v) {
		
		//Intent i = new Intent(anaekran.this, anaprg.class);
		//startActivity(i);
		
    	if (v==btn_giris) {
    		Cursor cursor = KayitGetir();
            KayitGoster(cursor); 
    		
    	//if( kul_adi.getText().toString().equals(db_kul_adi) && kul_pass.getText().toString().equals(db_kul_sifre) ) {
            if( kul_pass.getText().toString().equals(db_kul_sifre) ) {
            

        	kul_pass.setText("");
    		
        	Intent i = new Intent(anaekran.this, anaprg.class);
    		startActivity(i);
    		
    		stopManagingCursor(cursor);
    		cursor.close();
    		vt.close();    		
    		
		} else {
			//Toast.makeText(this, "Hata : Kullanýcý Adý veya Þifre hatalý." , Toast.LENGTH_SHORT).show();
			//myToast("Þifreyi hatalý girdiniz.");	
			kul_pass.requestFocus();
			kul_pass.setError("Þifreyi hatalý girdiniz.");
	    }
	    
    	}
    	
    	if (v==btn_iptal) {
    		kul_pass.setText("");
    		kul_pass.requestFocus();
    		System.exit(0);
    	}
    	
    	if (v==cb_sifregoster) {
    		if (cb_sifregoster.isClickable()==true)
    			kul_pass.setInputType(InputType.TYPE_CLASS_TEXT);
    		else
    			kul_pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
    	}
    	
    } 
    
    
    
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		
	}


	public boolean onCreateOptionsMenu (Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater menutanim = getMenuInflater();
        menutanim.inflate(R.menu.anamenu, menu);
        return true;
        }
 
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

        case R.id.menu_hakkinda:
        	MyHakkinda myhk = new MyHakkinda(); 
        	myhk.myHakkinda(anaekran.this);
            return true;
        }
        return false;
    }
    
    @Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		System.exit(0);
		finish();
        android.os.Process.killProcess(android.os.Process.myPid());
	}

    
    
    private Cursor KayitGetir(){
    SQLiteDatabase db = vt.getReadableDatabase();
    Cursor cursor = db.query("sifreler", SELECT, "id=1", null, null, null, null);
    
    startManagingCursor(cursor);
    return cursor;
    }
    

    private void KayitGoster(Cursor cursor){
            while(cursor.moveToNext()){        
        db_kul_sifre = cursor.getString((cursor.getColumnIndex("kul_sifre")));
        }
   }
    
    public void myToast(String text){
    Toast toast = Toast.makeText(getApplicationContext(),"\n"+text+"\n", Toast.LENGTH_LONG);
    toast.setGravity(Gravity.BOTTOM, 0, 0);
    LinearLayout toastView = (LinearLayout) toast.getView();
    ImageView imageCodeProject = new ImageView(getApplicationContext());
    //imageCodeProject.setImageResource(R.drawable.icon);
    //toastView.addView(imageCodeProject, 0);
    toast.show();	
    }
  
}