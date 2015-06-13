package com.example.Cherry.Util;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.graphics.Paint;

public class VerificationCode {
	private static final char[] CHARS = { '2', '3', '4', '5', '6', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm',
			'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A',
			'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private static VerificationCode bmpCode;

	// singleton Model
	public static VerificationCode getInstance() {
		if (bmpCode == null) {
			bmpCode = new VerificationCode();
		}
		return bmpCode;
	}

	// default settings
	private static final int DEFAULT_CODE_LENGTH = 3;
	private static final int DEFAULT_FONT_SIZE = 25;
	private static final int DEFAULT_LINE_NUMBER = 2;
	private static final int BASE_PADDING_LEFT = 5, RANGE_PADDING_LEFT = 15,
			BASE_PADDING_TOP = 15, RANGE_PADDING_TOP = 20;
	private static final int DEFAULT_WIDTH = 60, DEFAULT_HEIGHT = 40;
	
	//settings decided by the layout xml  
    //canvas width and height  
    private int width = DEFAULT_WIDTH, height = DEFAULT_HEIGHT;   
      
    //random word space and pading_top  
    private int base_padding_left = BASE_PADDING_LEFT, range_padding_left = RANGE_PADDING_LEFT,   
            base_padding_top = BASE_PADDING_TOP, range_padding_top = RANGE_PADDING_TOP;  
      
    //number of chars, lines; font size  
    private int codeLength = DEFAULT_CODE_LENGTH, line_number = DEFAULT_LINE_NUMBER, font_size = DEFAULT_FONT_SIZE;  
      
    //variables  
    private String code;  //随机产生的验证码
    private int padding_left, padding_top;  
    private Random random = new Random();  
    
    //产生具体位数的随机码
    private String createCode(){
    	StringBuilder buff=new StringBuilder();
    	for (int i = 0; i < codeLength; i++) {
			buff.append(CHARS[random.nextInt(CHARS.length)]);
		}
    	return buff.toString();
    }
   //随机产生验证码的颜色
    private int randomColor(int rate) {  
        int red = random.nextInt(256) / rate;  
        int green = random.nextInt(256) / rate;  
        int blue = random.nextInt(256) / rate;  
        return Color.rgb(red, green, blue);  
    } 
   
    //随机画直线，Canvas是画布，Paint是画图工具
    private void drawLine(Canvas canvas,Paint paint){
    	int color = randomColor(1);  
        int startX = random.nextInt(width);  
        int startY = random.nextInt(height);  
        int stopX = random.nextInt(width);  
        int stopY = random.nextInt(height);  
        paint.setStrokeWidth(1);  //设置笔宽
        paint.setColor(color);  //设置笔的颜色
        canvas.drawLine(startX, startY, stopX, stopY, paint); //随机在某位置画线 
    }
    //随机画形式
    private void randomTextStyle(Paint paint) {  
        int color = randomColor(1);  
        paint.setColor(color);  
        paint.setFakeBoldText(random.nextBoolean());  //true为粗体，false为非粗体  
        float skewX = random.nextInt(11) / 10;  
        skewX = random.nextBoolean() ? skewX : -skewX;  
        paint.setTextSkewX(skewX); //float类型参数，负数表示右斜，整数左斜  
//      paint.setUnderlineText(true); //true为下划线，false为非下划线  
//      paint.setStrikeThruText(true); //true为删除线，false为非删除线  
    }  
    //随机设数字跟框的内边距
    private void randomPadding() {  
        padding_left += base_padding_left + random.nextInt(range_padding_left);  
        padding_top = base_padding_top + random.nextInt(range_padding_top);  
    } 
    public Bitmap CreateBitmap(){
    	padding_left=0;
    	
    	Bitmap bp=Bitmap.createBitmap(width,height,Config.ARGB_8888);
    	Canvas c=new Canvas(bp);//Canvas类就是表示一块画布，你可以在上面画你想画的东西
    	code=createCode();
    	c.drawColor(Color.WHITE);
    	Paint paint=new Paint();
    	paint.setTextSize(font_size);//设验证码大小
    	
    	for (int i = 0; i < code.length(); i++) {  
            randomTextStyle(paint);  
            randomPadding();  
            c.drawText(code.charAt(i) + "", padding_left, padding_top, paint);//写验证码  
        }  
    	
    	 for (int i = 0; i < line_number; i++) {  
             drawLine(c, paint);  //画两条直线
         } 
    	 
    	 c.save( Canvas.ALL_SAVE_FLAG );//保存    
         c.restore();//
    	return bp;
    }
}
