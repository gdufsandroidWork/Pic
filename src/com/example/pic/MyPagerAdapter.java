package com.example.pic;

import java.util.List;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/**
 * ViewPager  ≈‰∆˜
 */
public class MyPagerAdapter extends PagerAdapter {
	public List<View> mListViews;

	public MyPagerAdapter(List<View> mListViews) {
		this.mListViews = mListViews;
	}

	 @Override  
     public void destroyItem(ViewGroup container, int position, Object object)   {     
         container.removeView(mListViews.get(position));  
     }  

	@Override
	public void finishUpdate(View arg0) {
	}

	@Override
	public int getCount() {
		return mListViews.size();
	}

	 @Override  
     public Object instantiateItem(ViewGroup container, int position) {            
          container.addView(mListViews.get(position), 0);  
          return mListViews.get(position);  
     }  

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == (arg1);
	}

}