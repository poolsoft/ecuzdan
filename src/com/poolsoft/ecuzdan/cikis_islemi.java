package com.poolsoft.ecuzdan;

import com.poolsoft.ecuzdan.R;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class cikis_islemi extends Activity {

		  @Override
		  public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.cikis_islemi);

		    Button orderButton = (Button) findViewById(R.id.cikis_iptal);

		    orderButton.setOnClickListener(new View.OnClickListener() {

		      @Override
		      public void onClick(View view) {
		        finish();
		      }

		    });
		  }
		}