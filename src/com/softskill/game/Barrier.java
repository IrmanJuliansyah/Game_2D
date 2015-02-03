package com.softskill.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

public class Barrier {
	private Bitmap bitmap;
	private int x;
	private int y;

	BarrierManager BM;
	boolean dult;

	public Barrier(Bitmap center, int x, int y) {
		bitmap = center;
		this.x = x;
		this.y = y;
	}

	public void setManager(BarrierManager barriermanager) {
		BM = barriermanager;
	}

	public Bitmap getBitmap() {
		// TODO Auto-generated method stub
		return bitmap;
	}

	public void setY(int y) {
		this.y = y;

	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2),
				y - (bitmap.getHeight() / 2), null);

	}

	public void update(float dt, boolean b) {
		if (x < -bitmap.getWidth()) {
			if (b) {
				if (Math.abs(BM.TargetY - BM.dos) < 50)
					dult = true;
				if ((BM.TargetY == -1) || dult) {
					BM.TargetY = new Random().nextInt(BM.screen - BM.dl / 2)
							+ BM.dl / 4;
				}
				if (BM.dos < BM.TargetY)
					BM.dos = BM.dos + new Random().nextInt(15);
				else
					BM.dos = BM.dos - new Random().nextInt(15);
				y = BM.dos - BM.dl / 2 - bitmap.getHeight() / 2;
			} else {
				y = BM.dos + BM.dl / 2 + bitmap.getHeight() / 2;
			}
			x = (int) (x + bitmap.getWidth() * (BM.TopWalls.size() - 1));
		}
		x = (int) (x - BM.game_panel.ShipSpeed * dt);
	}

	public ArrayList<Point> getArray() {
		Point TL = new Point(), TR = new Point(), BL = new Point(), BR = new Point();
		TL.x = x - bitmap.getWidth() / 2;
		TL.y = y - bitmap.getHeight() / 2;

		TR.x = x + bitmap.getWidth() / 2;
		TR.y = y - bitmap.getHeight() / 2;

		BL.x = x - bitmap.getWidth() / 2;
		BL.y = y + bitmap.getHeight() / 2;

		BR.x = x + bitmap.getWidth() / 2;
		BR.y = y + bitmap.getHeight() / 2;

		ArrayList<Point> temp = new ArrayList<Point>();
		temp.add(TL);
		temp.add(TR);
		temp.add(BL);
		temp.add(BR);

		return temp;
	}

}
