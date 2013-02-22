package com.poolsoft.ecuzdan;

import com.poolsoft.ecuzdan.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.Gravity;
import android.view.View;


public class ayarlar  extends Activity implements OnClickListener {
    private Button btn_kaydet;
    private Button btn_iptal;
    private CheckBox cb_sifregoster;
    

    private String db_kul_sifre = "";

    private TextView kul_pass = null;
    private veritabani vt;
    SQLiteDatabase db;
	
	private String[] SELECT = {"id", "kul_adi", "kul_sifre"};

		  @Override
		  public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.ayarlar);
		    vt = new veritabani(this);
		    
	        btn_kaydet = (Button)findViewById(R.id.ayarlar_btn_kaydet);
	        btn_iptal = (Button)findViewById(R.id.ayarlar_btn_iptal);
	        cb_sifregoster = (CheckBox)findViewById(R.id.ayarlar_cb_sifregoster);
	        
			kul_pass = (TextView)findViewById(R.id.ayarlar_txt_kull_sifre);
	        
			btn_kaydet.setOnClickListener(this);
			btn_iptal.setOnClickListener(this);
			cb_sifregoster.setOnClickListener(this);
			
			Cursor cursor = KayitGetir();
	        KayitGoster(cursor); 

		  }

@Override
public void onClick(View v) {
	
	//Intent i = new Intent(anaekran.this, anaprg.class);
	//startActivity(i);
	
	if (v==btn_kaydet) {
		SQLiteDatabase db = vt.getReadableDatabase();
		db.execSQL("update sifreler set kul_sifre='"+kul_pass.getText()+"' where id=1"); 
		db.close();
		
		myToast("Þifre deðiþtirildi.");	
		finish();
    }
	
	
	if (v==cb_sifregoster) {
		if (cb_sifregoster.isClickable()==true)
			kul_pass.setInputType(InputType.TYPE_CLASS_TEXT);
		else
			kul_pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
	}
    

	
	if (v==btn_iptal) {
		System.exit(0);
	}
	
	
} 
		

private Cursor KayitGetir(){
    SQLiteDatabase db = vt.getReadableDatabase();
    Cursor cursor = db.query("sifreler", SELECT, "id=1", null, null, null, null);

startManagingCursor(cursor);
return cursor;
}

private void KayitGoster(Cursor cursor){
        while(cursor.moveToNext()){           		
        	kul_pass.setText(cursor.getString(cursor.getColumnIndex("kul_sifre")));
        }
   }
    

public void myToast(String text){
    Toast toast = Toast.makeText(getApplicationContext(),"\n"+text+"\n", Toast.LENGTH_LONG);
	toast.setGravity(Gravity.CENTER, 0, 0);
	LinearLayout toastView = (LinearLayout) toast.getView();
	ImageView imageCodeProject = new ImageView(getApplicationContext());
	imageCodeProject.setImageResource(R.drawable.icon);
	toastView.addView(imageCodeProject, 0);
	toast.show();	
}


}

