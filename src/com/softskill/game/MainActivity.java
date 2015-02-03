package com.softskill.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity {
	RelativeLayout btn;
	TextView txt;
	ImageView imgBtn;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        
        btn =(RelativeLayout)findViewById(R.id.buttonStart);
        txt = (TextView)findViewById(R.id.start_game);
        imgBtn = (ImageView)findViewById(R.id.conti);
        
        btn.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				return false;
			}
		});
        
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent ps = new Intent(MainActivity.this,Game.class);
				startActivity(ps);
				
			}
		});
    }


  
}

