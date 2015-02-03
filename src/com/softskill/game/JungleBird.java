package com.softskill.game;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

public class JungleBird {
	private Bitmap bitmap;
	private int x;
	private int y;
	private int Speed;
	private int inc;
	private int ScreenWidth;
	private int ScreenHeight;
	public ArrayList<Bitmap> Booms = null;
	boolean death;
	boolean up;
	float VarSpeed;
	float aniTime = 0;
	float totalAnitime = 1;
	float numFrames;
	
	public JungleBird(Bitmap decodeResource, int x, int y, int screenWidth , int screenHeight) {
		this.bitmap = decodeResource;
		this.x = x;
		this.y = y;
		Speed =1;
		inc = 0;
		death = false;
		ScreenWidth = screenWidth;
		ScreenHeight = screenHeight;
		VarSpeed = 0;
	}
	public void setBoomAnimation(ArrayList<Bitmap> animation){
		Booms = new ArrayList<Bitmap>(animation);
		numFrames = Booms.size();
	}
	public void draw(Canvas canvas){
		if(!death){
			canvas.drawBitmap(bitmap,x - bitmap.getWidth()/2,y - bitmap.getHeight()/2 , null );
			
		}else{
			int index = (int) (aniTime/totalAnitime*numFrames);
			if(index<numFrames)
				canvas.drawBitmap(Booms.get(index),x-bitmap.getWidth()/2, y - bitmap.getHeight()/2, null );
		}
	}
	public void update(float dt){
		if(death){
			aniTime += dt;
		}else{
			VarSpeed += ScreenHeight/2*dt;
			if(up)
				VarSpeed -= ScreenHeight*dt*2;
			y+=VarSpeed*dt;
			
			if(y - (bitmap.getHeight()/2)>ScreenWidth)
				y = 0 -(bitmap.getHeight()/2);
		}
	}
	
	public boolean bump(Point OTL, Point OTR , Point OBL , Point OBR){
		Point TL = new Point() , TR = new Point(),BL = new Point(), BR = new Point();
		
		ArrayList<Point> PointList = new ArrayList<Point>();
		PointList.add(OTL);
		PointList.add(OTR);
		PointList.add(OBL);
		PointList.add(OBR);
		
		getPoint(TL,TR,BL,BR);
		
		for(int i=0; i<PointList.size(); i++){
			if(BR.x >= PointList.get(i).x)
				if(TL.x <= PointList.get(i).x)
					if(PointList.get(i).y >= TL.y)
						if(PointList.get(i).y <= BR.y)
			return true;
		}
		
		PointList.clear();
		PointList.add(TL);
		PointList.add(TR);
		PointList.add(BL);
		PointList.add(BR);
		for(int i=0; i<PointList.size(); i++){
			if(OBR.x >= PointList.get(i).x)
				if(OTL.x <= PointList.get(i).x)
					if(PointList.get(i).y >= OTL.y)
						if(PointList.get(i).y <= OBR.y)
			return true;
		}
		
		return false;
		
	}
	private void getPoint(Point TL, Point TR, Point BL, Point BR) {
		// TODO Auto-generated method stub
		TL.x = x - bitmap.getWidth()/2;
		TL.y = y - bitmap.getHeight()/2;
		
		TR.x = x + bitmap.getWidth()/2;
		TR.y = y - bitmap.getHeight()/2;
		
		BL.x = x - bitmap.getWidth()/2;
		BL.y = y + bitmap.getHeight()/2;
		
		BR.x = x + bitmap.getWidth()/2;
		BR.y = y + bitmap.getHeight()/2;
		
	}
	

}
