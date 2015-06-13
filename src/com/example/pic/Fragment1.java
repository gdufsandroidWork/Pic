package com.example.pic;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Fragment1 extends Fragment {

	private GridView gridView; 
     //图片ID数组 
     private int[] imgs = new int[] {        
             R.drawable.grid_view_01, R.drawable.grid_view_02, R.drawable.grid_view_03,  
             R.drawable.grid_view_04, R.drawable.grid_view_05, R.drawable.grid_view_06,  
             R.drawable.grid_view_07, R.drawable.grid_view_08,R.drawable.grid_view_09,  
             R.drawable.grid_view_10, R.drawable.grid_view_11,R.drawable.grid_view_12,  
             R.drawable.grid_view_13, R.drawable.grid_view_14,R.drawable.grid_view_15,  
             R.drawable.grid_view_16, R.drawable.grid_view_17,R.drawable.grid_view_18  
     }; 
     //图片编号数组 
     private String[] tits = new String[] {        
             "picture_01", "picture_02", "picture_03",  
             "picture_04", "picture_05", "picture_06",  
             "picture_07", "picture_08", "picture_09",  
             "picture_10", "picture_11", "picture_12",  
             "picture_13", "picture_14", "picture_15",  
             "picture_16", "picture_17", "picture_18"  
     }; 
  
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	View v=inflater.inflate(R.layout.lay1, null);
    	GridView gridview=(GridView) v.findViewById(R.id.gridView1);
    	gridview.setAdapter(new PictureAdapter(tits, imgs, getActivity().getApplicationContext()));
    	return v;
    }
} 

//自定义PictureAdapter适配器 
class PictureAdapter extends BaseAdapter{ 
     private LayoutInflater inflater; 
     private List<Picture> pictures; 
  
     public PictureAdapter(String[] titles, int[] images, Context context) { 
         super(); 
         pictures = new ArrayList<Picture>(); 
         inflater = LayoutInflater.from(context); 
         for (int i = 0; i < images.length; i++) 
         { 
             Picture pic = new Picture(titles[i], images[i]); 
             pictures.add(pic); 
         } 
     } 
  
     @Override
     public int getCount() { 
         if (null != pictures) { 
             return pictures.size(); 
         } else { 
             return 0; 
         } 
     } 
     @Override
     public Object getItem(int position) { 
         return pictures.get(position); 
     } 
     @Override
     public long getItemId(int position) { return position; } 
     @Override
     public View getView(int position, View convertView, ViewGroup parent) 
     { 
         ViewHolder viewHolder; 
         if (convertView == null) 
         { 
             convertView = inflater.inflate(R.layout.pic_item, null); 
             viewHolder = new ViewHolder(); 
             viewHolder.title = (TextView) convertView.findViewById(R.id.title); 
             viewHolder.image = (ImageView) convertView.findViewById(R.id.image); 
             convertView.setTag(viewHolder); 
         } else
         { 
             viewHolder = (ViewHolder) convertView.getTag(); 
         } 
         viewHolder.title.setText(pictures.get(position).getTitle()); 
         viewHolder.image.setImageResource(pictures.get(position).getImageId()); 
         return convertView; 
     } 
} 
 
//定义类ViewHolder
class ViewHolder { 
     public TextView title; 
     public ImageView image; 
} 
 
//定义类Picture  
class Picture { 
     private String title; 
     private int imageId; 
     
     public Picture() { 
         super(); 
     } 
     
     public Picture(String title, int imageId) { 
         super(); 
         this.title = title; 
         this.imageId = imageId; 
     } 
  
     public String getTitle() { return title; } 
     public void setTitle(String title) { this.title = title; } 
     public int getImageId() { return imageId; } 
     public void setImageId(int imageId) { this.imageId = imageId; } 
} 
