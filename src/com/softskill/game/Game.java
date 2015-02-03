package com.softskill.game;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Game extends Activity {
	RelativeLayout Rel_main_game;
	View pauseButton;
	View pauseMenu;
	GamePanel game_panel;
	OnClickListener Continue_list = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			pauseMenu.setVisibility(View.GONE);
			pauseButton.setVisibility(View.VISIBLE);
			game_panel.Pause_game = false;
		}
	};
	
	OnClickListener To_Main_Menu_list = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			game_panel.thread.setRunning(false);
			Game.this.finish();
			
		}
	};
	
	OnClickListener Pause_Click = new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			pauseButton.setVisibility(View.GONE);
			pauseMenu.setVisibility(View.VISIBLE);
			
			game_panel.Pause_game = true;
			
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		Rel_main_game =(RelativeLayout)findViewById(R.id.main_game);
		DisplayMetrics dx = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dx);
		
		final int heigth5 = dx.heightPixels;
		final int width5 = dx.widthPixels;
		game_panel = new GamePanel(getApplicationContext(), this,width5,heigth5);
		Rel_main_game.addView(game_panel);
		
		
		LayoutInflater myInflater = (LayoutInflater) 
				getApplicationContext().getSystemService
				(getApplicationContext().LAYOUT_INFLATER_SERVICE);
		
		pauseButton = myInflater.inflate(R.layout.pause,null,false);
		pauseButton.setX(width5-100);
		pauseButton.setY(0);
		Rel_main_game.addView(pauseButton);
		pauseButton.setOnClickListener(Pause_Click);
		
		pauseButton.getLayoutParams().height=100;
		pauseButton.getLayoutParams().width=100;
		
		pauseMenu = myInflater.inflate(R.layout.pause_menu,null,false);
		Rel_main_game.addView(pauseMenu);
		pauseMenu.setVisibility(View.GONE);
		
		ImageView Cont = (ImageView)findViewById(R.id.imageView1);
		ImageView Main_menu = (ImageView) findViewById(R.id.imageView2);
		Cont.setOnClickListener(Continue_list);
		Main_menu.setOnClickListener(To_Main_Menu_list);
		
	}

}
