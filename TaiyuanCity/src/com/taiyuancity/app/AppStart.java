package com.taiyuancity.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.taiyuancity.R;
import com.taiyuancity.ui.AboutActivity;
import com.taiyuancity.ui.MainActivity;

/**
 * 应用程序启动
 * 
 * @author neng
 * @date
 */
public class AppStart extends Activity
{
	private boolean firstTime = false;
	private SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_start);

		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		firstTime = prefs.getBoolean("first_time", true);

		if (firstTime)
		{
			// 第一次启动动作
			firstStartAction();
		} else
		{
			//2000ms后启动主界面
			pauseTheActivity(AppConfig.startPicturePauseTime);
			// 播放启动画面
			playStartAnimation();
		}

	}

	/**
	 * 停止Activity
	 * 
	 * @param displayTime
	 */
	private void pauseTheActivity(int displayTime)
	{
		//displayTime后启动主界面
		new Handler().postDelayed(new Runnable()
		{
			public void run()
			{
				Intent mainIntent = new Intent(AppStart.this,
						MainActivity.class);
				AppStart.this.startActivity(mainIntent);
				AppStart.this.finish();
			}
		}, displayTime);
	}

	private void playStartAnimation()
	{
		//标题的动画
		ImageView start_title = (ImageView) findViewById(R.id.start_title);
		AlphaAnimation alpha = new AlphaAnimation(0, 1);
		//RotateAnimation rotate1 = new RotateAnimation(0, 360,
		//		Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
		//		0.5f);
		TranslateAnimation translate = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, 0.8f, Animation.RELATIVE_TO_SELF,
				0f);
		AnimationSet animationSet = new AnimationSet(true);
		animationSet.addAnimation(alpha);
		animationSet.addAnimation(translate);
		//animationSet.addAnimation(rotate1);
		animationSet.setDuration(1300);
		animationSet.setStartOffset(100);
		start_title.startAnimation(animationSet);
	}

	private void firstStartAction()
	{
		//将第一次启动参数设置为false
		Editor pEdit = prefs.edit();
		pEdit.putBoolean("first_time", false);
		pEdit.commit();
		//启动关于帮助
		this.finish();//解决播放画面
		Intent aboutIntent = new Intent(this, AboutActivity.class);
		startActivity(aboutIntent);

	}

}
