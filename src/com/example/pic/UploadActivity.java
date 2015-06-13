package com.example.pic;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UploadActivity extends Activity {

	private String url="http://192.168.202.109:8080/PicServer/uploadServlet";
	private String filename="1.png";
	private String uploadFile="/sdcard/image.png";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		final Button btn=(Button) this.findViewById(R.id.button1);
//		btn.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				uploadFile();
//			}
//		});
	}

	private void uploadFile(){
		String end="\r\n";
		String twoH="--";
		String boundary="***";
		try {
			HttpURLConnection con=(HttpURLConnection) new URL(url).openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestMethod("Post");
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "utf-8");
			con.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
			
			DataOutputStream out=new DataOutputStream(con.getOutputStream());
			StringBuffer sb=new StringBuffer();
			sb.append(twoH+boundary+end);
			sb.append("Content-Disposition:form-data;name=\"file1\";filename=\""+filename+"\""+end);
			sb.append(end);
			
			out.write(sb.toString().getBytes());
			
			FileInputStream fis=new FileInputStream(uploadFile);
			int len=-1;
			byte[] buffer=new byte[1024];
			while((len=fis.read(buffer))!=-1){
				out.write(buffer,0,len);
			}
			out.write(end.getBytes());
			out.writeBytes(twoH+boundary+twoH+end);
			
			fis.close();
			out.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}