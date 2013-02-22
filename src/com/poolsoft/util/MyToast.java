package com.poolsoft.util;

import android.content.Context;
import android.content.Intent;

import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.poolsoft.ecuzdan.R;

public class MyToast {
    public void myToast(String text){
    Toast toast = Toast.makeText(getApplicationContext(),"\n"+text+"\n", Toast.LENGTH_LONG);
    toast.setGravity(Gravity.CENTER, 0, 0);
    LinearLayout toastView = (LinearLayout) toast.getView();
    ImageView imageCodeProject = new ImageView(getApplicationContext());
    imageCodeProject.setImageResource(R.drawable.icon);
    toastView.addView(imageCodeProject, 0);
    toast.show();	
    }
    
    public void myToast2(String text){
    Toast toast = Toast.makeText(getApplicationContext(),"\n"+text+"\n", Toast.LENGTH_LONG);
    toast.setGravity(Gravity.CENTER, 0, 0);
    LinearLayout toastView = (LinearLayout) toast.getView();
    ImageView imageCodeProject = new ImageView(getApplicationContext());
    //imageCodeProject.setImageResource(R.drawable.icon);
    //toastView.addView(imageCodeProject, 0);
    toast.show();	
    }

	private Context getApplicationContext() {
		// TODO Auto-generated method stub
		return null;
	}
}