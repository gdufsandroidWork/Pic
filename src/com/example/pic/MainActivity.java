package com.example.pic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new Handler().postDelayed(new Runnable(){
			@Override
			public void run(){
				Intent intent = new Intent (MainActivity.this,ViewpagerActivity.class);			
				startActivity(intent);			
				MainActivity.this.finish();
			}
		}, 1000);
		
	}
}
