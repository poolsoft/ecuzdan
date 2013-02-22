package com.poolsoft.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;

import android.text.Html;
import android.text.util.Linkify;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.poolsoft.ecuzdan.R;
import com.poolsoft.ecuzdan.anaekran;

public class MyHakkinda {
	private final static Context mContext = null;
	
	public void myHakkinda(Context myContext){
    	//set up dialog

        final Dialog dialog = new Dialog(myContext);
        dialog.setContentView(R.layout.hakkinda);
        dialog.setTitle("eCuzdan [Hakkinda]");
        dialog.setCancelable(true);
        //there are a lot of settings, for dialog, check them all out!
/*       
        TextView tv = (TextView)dialog.findViewById(R.id.legal_text);
        tv.setText(readRawTextFile(R.raw.legal));
        tv = (TextView)dialog.findViewById(R.id.info_text);
        tv.setText(Html.fromHtml(readRawTextFile(R.raw.info)));
        tv.setLinkTextColor(Color.WHITE);
        Linkify.addLinks(tv, Linkify.ALL);
*/
        //set up text1
        TextView text = (TextView) dialog.findViewById(R.id.text);
        text.setText(Html.fromHtml(readRawTextFile(myContext,R.raw.info)));
        text.setLinkTextColor(Color.WHITE);
        Linkify.addLinks(text, Linkify.ALL);

        //set up image view
        ImageView img = (ImageView) dialog.findViewById(R.id.image);
        img.setImageResource(R.drawable.icon);

        //set up button
        Button button = (Button) dialog.findViewById(R.id.dialogButtonOK);
        button.setOnClickListener(new OnClickListener() {
        @Override
            public void onClick(View v) {
        		dialog.dismiss();
            }
        });
        //now that the dialog is set up, it's time to show it    
        dialog.show();
    }
	
	public static String readRawTextFile(Context c, int resId)
    {
         InputStream inputStream = c.getResources().openRawResource(resId);

            InputStreamReader inputreader = new InputStreamReader(inputStream);
            BufferedReader buffreader = new BufferedReader(inputreader);
             String line;
             StringBuilder text = new StringBuilder();

             try {
               while (( line = buffreader.readLine()) != null) {
                   text.append(line);
                   text.append('\n');
                 }
           } catch (IOException e) {
               return null;
           }
             return text.toString();
    }
	



	private Context getApplicationContext() {
		// TODO Auto-generated method stub
		return null;
	}	 
}