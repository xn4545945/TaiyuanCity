package com.taiyuancity.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.taiyuancity.R;
import com.taiyuancity.widget.MyScrollLayout;
import com.taiyuancity.widget.OnViewChangeListener;

/**
 * 关于
 * 
 * @author neng
 * @date
 */
public class AboutActivity extends Activity implements OnViewChangeListener
{
	private MyScrollLayout mScrollLayout;
	private ImageView[] imgs;
	private int count;
	private int currentItem;
	private Button startBtn;
	private LinearLayout pointLLayout;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		initView();
	}

	private void initView()
	{
		mScrollLayout = (MyScrollLayout) findViewById(R.id.ScrollLayout);
		pointLLayout = (LinearLayout) findViewById(R.id.llayout);
		startBtn = (Button) findViewById(R.id.startBtn);
		startBtn.setOnClickListener(listener);

		count = mScrollLayout.getChildCount();
		imgs = new ImageView[count];
		//小点点
		for (int i = 0; i < count; i++)
		{
			imgs[i] = (ImageView) pointLLayout.getChildAt(i);
			imgs[i].setEnabled(true);
			imgs[i].setTag(i);
		}
		currentItem = 0;
		imgs[currentItem].setEnabled(false);

		mScrollLayout.SetOnViewChangeListener(this);
	}

	private View.OnClickListener listener = new View.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			Intent intent = new Intent(AboutActivity.this, MainActivity.class);
			startActivity(intent);
			//应该关闭其他所有activity
			AboutActivity.this.finish();
		}
	};

	@Override
	public void OnViewChange(int position)
	{
		setcurrentPoint(position);
	}

	private void setcurrentPoint(int position)
	{
		if (position < 0 || position > count - 1 || currentItem == position)
		{
			return;
		}
		imgs[currentItem].setEnabled(true);
		imgs[position].setEnabled(false);
		currentItem = position;
	}

}
