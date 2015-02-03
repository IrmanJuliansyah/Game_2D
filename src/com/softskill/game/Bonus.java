package com.softskill.game;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

public class Bonus {
	private Bitmap bitmap;
	private int x;
	private int y;
	BarrierManager BM;
	public Bonus(Bitmap decodeResource, int x, int y) {
		this.bitmap = decodeResource;
		this.x = x;
		this.y = y;
	}
	public void setBarrierManager(BarrierManager kondition){
		BM=kondition;
	}
	public Bitmap getBitmap(){
		return bitmap;
	}
	public ArrayList<Point> getArray() {
		Point TL = new Point() , TR = new Point(),BL = new Point(), BR = new Point();
		TL.x = x - bitmap.getWidth()/2;
		TL.y = y - bitmap.getHeight()/2;
		
		TR.x = x + bitmap.getWidth()/2;
		TR.y = y - bitmap.getHeight()/2;
		
		BL.x = x - bitmap.getWidth()/2;
		BL.y = y + bitmap.getHeight()/2;
		
		BR.x = x + bitmap.getWidth()/2;
		BR.y = y + bitmap.getHeight()/2;
		
		ArrayList<Point> temp = new ArrayList<Point>();
		temp.add(TL);
		temp.add(TR);
		temp.add(BL);
		temp.add(BR);
		
		return temp;
	}
	public void draw(Canvas canvas){
		canvas.drawBitmap(bitmap, x - (bitmap.getWidth()/2), y - (bitmap.getHeight()/2), null);
	}
	public void update(float dt){
		if(x <- BM.game_panel.ScreenWidth/4){
			x = BM.game_panel.ScreenWidth+bitmap.getWidth();
			Random r = new Random();
			y = r.nextInt(BM.dl)+BM.dos - BM.dl/2;
		}
		x -= BM.game_panel.ShipSpeed*dt;
	}
	public void setX(int x) {
		this.x = x;
		
	}
	public void setY(int y) {
		this.y = y;
		
	}

}
