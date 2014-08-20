package com.taiyuancity.util;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import com.taiyuancity.R;
import com.taiyuancity.ui.OpenDoorActivity;

/**
 * 传感器摇晃检测, 与效果播放
 * 
 * @author neng
 * @date 2013-2-15
 */
public class ShakeListenerUtils implements SensorEventListener
{
	private Activity context;

	public ShakeListenerUtils(Activity context)
	{
		super();
		this.context = context;
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		int sensorType = event.sensor.getType();
		//values[0]:X轴，values[1]：Y轴，values[2]：Z轴  
		float[] values = event.values;

		if (sensorType == Sensor.TYPE_ACCELEROMETER)
		{

			/*正常情况下，任意轴数值最大就在9.8~10之间，只有在突然摇动手机 
			  的时候，瞬时加速度才会突然增大或减少。   监听任一轴的加速度大于17即可
			*/
			if ((Math.abs(values[0]) > 17 || Math.abs(values[1]) > 17 || Math
					.abs(values[2]) > 17))
			{
				context.overridePendingTransition(R.anim.zoom_out_enter,
						R.anim.zoom_out_exit);
				//检测到晃动后启动OpenDoor效果
				Intent intent = new Intent(context, OpenDoorActivity.class);
				context.startActivity(intent);
				context.finish();
			}
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
		//当传感器精度改变时回调该方法，Do nothing. 
	}

}
