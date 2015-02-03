package com.softskill.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {
	Bitmap Backbitmap;
	int x, y;
	int Screenwitdh;
	int Count_Background;
	GamePanel root_gamepanel;

	public Background(Bitmap bitmap, int Screen_w, GamePanel Game_panel) {
		this.Backbitmap = bitmap;
		this.x = 0;
		this.y = 0;
		this.Screenwitdh = Screen_w;
		Count_Background = Screenwitdh / Backbitmap.getWidth() + 1;
		root_gamepanel = Game_panel;
	}

	public void draw(Canvas canvas) {
		for (int i = 0; i < Count_Background + 1; i++) {
			if (canvas != null)
				canvas.drawBitmap(Backbitmap, Backbitmap.getWidth() * i + x, y,
						null);
		}
		if (Math.abs(x) > Backbitmap.getWidth()) {
			x = x + Backbitmap.getWidth();
		}
	}

	public void update(float dt) {
		x = (int) (x - root_gamepanel.ShipSpeed * dt);

	}

}
