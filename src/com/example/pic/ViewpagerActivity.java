package com.example.pic;

import java.util.ArrayList;
import java.util.List;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewpagerActivity extends FragmentActivity {

	private ViewPager mPager;// ҳ������
	private List<View> listViews; // Tabҳ���б�
	private ImageView cursor;// ����ͼƬ
	private TextView t1, t2, t3, t4;// ҳ��ͷ��
	private int offset = 0;// ����ͼƬƫ����
	private int currIndex = 0;// ��ǰҳ�����
	private int bmpW;// ����ͼƬ���
	private int screenW;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager);

		InitViewPager();
		InitImageView();
		InitTextView();
	}

	/**
	 * ��ʼ��ͷ��
	 */
	private void InitTextView() {
		t1 = (TextView) findViewById(R.id.text1);
		t2 = (TextView) findViewById(R.id.text2);
		t3 = (TextView) findViewById(R.id.text3);
		t4 = (TextView) findViewById(R.id.text4);

		t1.setOnClickListener(new MyOnClickListener(0));
		t2.setOnClickListener(new MyOnClickListener(1));
		t3.setOnClickListener(new MyOnClickListener(2));
		t4.setOnClickListener(new MyOnClickListener(3));
	}
	
	/**
     * ��ʼ������
     */
    private void InitImageView() {
        cursor = (ImageView) findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.cursor)
                .getWidth();// ��ȡͼƬ���
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenW = dm.widthPixels/listViews.size();// ��ȡ�ֱ��ʿ��
        if(bmpW>screenW){
        	cursor.getLayoutParams().width=screenW;
        	bmpW=screenW;
        }
        offset = (screenW - bmpW) / 2;// ����ƫ���� 
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        cursor.setImageMatrix(matrix);// ���ö�����ʼλ��
    }

	public class MyOnClickListener implements OnClickListener {

		private int index = 0;

		public MyOnClickListener(int index) {
			this.index = index;
		}

		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}

	}

	/**
	 * ��ʼ��ViewPager
	 */
	private void InitViewPager() {
		mPager = (ViewPager) findViewById(R.id.vPager);
		listViews = new ArrayList<View>();
		LayoutInflater mInflater = getLayoutInflater();
		listViews.add(mInflater.inflate(R.layout.lay1, null));
		listViews.add(mInflater.inflate(R.layout.lay2, null));
		listViews.add(mInflater.inflate(R.layout.lay3, null));
		listViews.add(mInflater.inflate(R.layout.lay4, null));
		mPager.setAdapter(new MyPagerAdapter(listViews));
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	/**
	 * ҳ���л�����
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

//		int one = offset * 2 + bmpW;// ҳ��1 -> ҳ��2 ƫ����
//		int two = one * 2;// ҳ��1 -> ҳ��3 ƫ����
//		int three = one * 3;// ҳ��1 -> ҳ��4 ƫ����

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			/*switch (arg0) {
			case 0:
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
				}else if(currIndex==3){
					animation = new TranslateAnimation(three, 0, 0, 0);
				}
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, one, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
				}else if(currIndex==3){
					animation = new TranslateAnimation(three, one, 0, 0);
				}
				break;
			case 2:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, two, 0, 0);
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
				}else if(currIndex==3){
					animation = new TranslateAnimation(three, two, 0, 0);
				}
				break;
			case 3:
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, three, 0, 0);
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, three, 0, 0);
				}else if(currIndex==2){
					animation = new TranslateAnimation(two, three, 0, 0);
				}
				break;
			}*/
//			animation = new TranslateAnimation(one*currIndex, one*arg0, 0, 0);//��Ȼ����Ƚϼ�ֻ࣬��һ�д��롣  
			animation = new TranslateAnimation(screenW * currIndex, screenW * arg0 , 0, 0);
			currIndex = arg0;
			animation.setFillAfter(true);// True:ͼƬͣ�ڶ�������λ��
			animation.setDuration(300);
			cursor.startAnimation(animation);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
}
