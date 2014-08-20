package com.taiyuancity.ui;

import android.app.Activity;
import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.taiyuancity.R;
import com.taiyuancity.util.ShakeListenerUtils;

/**
 * "摇我喜欢"主控类. 负责动画.
 * 
 * @author neng
 * @date
 */
public class ShakeILikeActivity extends Activity
{

	private ShakeListenerUtils shakeUtils;
	private SensorManager mSensorManager; //定义sensor管理器, 注册监听器用

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shake_i_like);

		shakeUtils = new ShakeListenerUtils(this);
	}

	@Override
	protected void onResume()
	{
		super.onResume();

		//获取传感器管理服务 
		mSensorManager = (SensorManager) this
				.getSystemService(Service.SENSOR_SERVICE);
		//加速度传感器  
		mSensorManager.registerListener(shakeUtils,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				//还有SENSOR_DELAY_UI、SENSOR_DELAY_FASTEST、SENSOR_DELAY_GAME等，  
				//根据不同应用，需要的反应速率不同，具体根据实际情况设定  
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause()
	{
		mSensorManager.unregisterListener(shakeUtils);
		this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);//回退时闪一下
		super.onPause();
	}

}
