package com.example.Cherry.Home;

import com.example.Cherry.Util.VerificationCode;
import com.example.pic.R;
import com.example.pic.R.layout;
import com.example.pic.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;

public class FindPasswordActivity extends Activity {
     
	ImageView ver_code_image;
	VerificationCode code;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_password);
		ver_code_image=(ImageView) findViewById(R.id.Ver_Code);
		ver_code_image.setImageBitmap(code.getInstance().CreateBitmap());//画验证码的图片
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find_password, menu);
		return true;
	}

}
