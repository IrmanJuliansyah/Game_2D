package com.softskill.game;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
	public MainThread thread;
	public boolean Pause_game;
	public Background background;
	public JungleBird jungle_bird;
	public BarrierManager BM;
	public float ShipSpeed;
	public Bonus coin;
	public int ScreenWidth;
	public int ScreenHeight;

	public GamePanel(Context context, Game game, int ScreenWidth,
			int ScreenHeight) {
		super(context);
		getHolder().addCallback(this);
		thread = new MainThread(getHolder(), this);
		background = new Background(BitmapFactory.decodeResource(
				getResources(), R.drawable.bckground_play), ScreenWidth, this);
		jungle_bird = new JungleBird(BitmapFactory.decodeResource(
				getResources(), R.drawable.flappy_class), 100, 0, ScreenWidth,
				ScreenHeight);
		BM = new BarrierManager(BitmapFactory.decodeResource(getResources(),
				R.drawable.barier), this);
		coin = new Bonus(BitmapFactory.decodeResource(getResources(),
				R.drawable.coin_class), -200, -200);
		ArrayList<Bitmap> animation = new ArrayList<Bitmap>();
		animation.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.explosion_class));
		animation.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.coin_class));
		animation.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.flappy_class));
		animation.add(BitmapFactory.decodeResource(getResources(),
				R.drawable.explosion_class));

		jungle_bird.setBoomAnimation(animation);

		coin.setBarrierManager(BM);
		BM.setScreen(ScreenWidth, ScreenHeight);
		setFocusable(true);
		ShipSpeed = ScreenWidth / 2.f;
		this.ScreenWidth = ScreenWidth;
		this.ScreenHeight = ScreenHeight;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			jungle_bird.up = true;
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
			jungle_bird.up = false;
		}
		return true;
	}

	void Draw(Canvas canvas) {
		if (!Pause_game)
			if (canvas != null) {
				background.draw(canvas);
				coin.draw(canvas);
				jungle_bird.draw(canvas);
				BM.draw(canvas);

			}
	}

	void Update(float dt) {

		jungle_bird.update(dt);

		if (!jungle_bird.death) {
			background.update(dt);
			coin.update(dt);
			BM.update(dt);

			ArrayList<Point> coin_point = new ArrayList<Point>(coin.getArray());
			if (jungle_bird.bump(coin_point.get(0), coin_point.get(1),
					coin_point.get(2), coin_point.get(3))) {
				coin.setX(-200);
				coin.setY(-200);
			}
			for (int i = 0; i < BM.TopWalls.size(); i++) {
				ArrayList<Point> temp = new ArrayList<Point>(BM.TopWalls.get(i)
						.getArray());
				ArrayList<Point> temp2 = new ArrayList<Point>(BM.BottomWalls
						.get(i).getArray());

				if ((jungle_bird.bump(temp.get(0), temp.get(1), temp.get(2),
						temp.get(3)))
						|| (jungle_bird.bump(temp2.get(0), temp2.get(1),
								temp2.get(2), temp2.get(3)))) {
					jungle_bird.death = true;
				}
			}
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int heigth) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		thread.setRunning(true);
		thread.start();

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		boolean retry = true;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {

			}

		}

	}

}
