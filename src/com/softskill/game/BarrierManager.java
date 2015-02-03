package com.softskill.game;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class BarrierManager {
	Bitmap center;
	int birdHeight;
	int num;
	int screen;
	int dl;
	int TargetY = -1;
	int dos;
	public GamePanel game_panel;

	ArrayList<Barrier> TopWalls = null;
	ArrayList<Barrier> BottomWalls = null;

	public BarrierManager(Bitmap decodeResource, GamePanel game_panel) {
		// TODO Auto-generated constructor stub
		center = decodeResource;
		this.game_panel = game_panel;
	}

	void setBird(int h) {
		birdHeight = h;
	}

	public void setScreen(int width, int height) {
		screen = height;
		num = (width) / center.getWidth() + 4;
		TopWalls = new ArrayList<Barrier>();
		BottomWalls = new ArrayList<Barrier>();
		for (int i = 0; i < num + 1; i++) {
			Barrier BB = new Barrier(center, width + 200 + center.getWidth()
					* i, 0);
			BB.setManager(this);
			TopWalls.add(BB);
			Barrier BBB = new Barrier(center, width + 200 + center.getWidth()
					* i, 0);
			BBB.setManager(this);
			BottomWalls.add(BBB);

		}
		Generater();
	}

	private void Generater() {
		// TODO Auto-generated method stub
		int h = center.getHeight() / 2;
		dl = screen;
		dos = screen / 2;
		int new_dl = screen * 3 / 4;
		int inc = (dl - new_dl) / num;
		for (int i = 0; i < num + 1; i++) {
			dl = dl - inc;
			h = TopWalls.get(i).getBitmap().getHeight() / 2;
			TopWalls.get(i).setY(dos - dl / 2 - h);
			BottomWalls.get(i).setY(dos + dl / 2 + h);
		}
	}

	public void draw(Canvas canvas) {
		for (int i = 0; i < num + 1; i++) {
			TopWalls.get(i).draw(canvas);
			BottomWalls.get(i).draw(canvas);
		}
	}

	public void update(float dt) {
		for (int i = 0; i < num + 1; i++) {
			TopWalls.get(i).update(dt, true);
			BottomWalls.get(i).update(dt, false);
		}
	}
}
