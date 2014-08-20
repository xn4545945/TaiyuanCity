package com.taiyuancity.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.taiyuancity.R;
import com.taiyuancity.adapter.MainIconAdapter;
import com.taiyuancity.app.AppConstant;

/**
 * 启动后显示的主Activity.
 * 
 * @author neng
 * @date
 */
public class MainActivity extends Activity
{
	private GridView gridView;
	private TextView title;
	private MainIconAdapter adapter_lcty;
	private MainIconAdapter adapter_xzty;
	private MainIconAdapter adapter_wzty;
	private RadioButton radioButton_lcty;
	private RadioButton radioButton_wzty;
	private RadioButton radioButton_xzty;
	private RadioButton radioButton_shak_i_like;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViews();

		radioButton_lcty.setOnCheckedChangeListener(listener);
		radioButton_wzty.setOnCheckedChangeListener(listener);
		radioButton_xzty.setOnCheckedChangeListener(listener);
		radioButton_shak_i_like.setOnCheckedChangeListener(listener);

		adapter_lcty = new MainIconAdapter(AppConstant.title_lcty,
				AppConstant.imageIcon_lcty, this);
		adapter_wzty = new MainIconAdapter(AppConstant.title_wzty,
				AppConstant.imageIcon_wzty, this);
		adapter_xzty = new MainIconAdapter(AppConstant.title_xzty,
				AppConstant.imageIcon_xzty, this);

		gridView.setAdapter(adapter_lcty);//默认适配器

		// 为单个Item添加单击事件。
		gridView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				Intent intent = new Intent(MainActivity.this,
						ChildActivity.class);
				//传递对应的位置, 直接传递的是R中layout对应的int值
				if (adapter_lcty == gridView.getAdapter())
				{
					intent.putExtra("id", AppConstant.layout_lcty[position]);
				} else if (adapter_wzty == gridView.getAdapter())
				{
					intent.putExtra("id", AppConstant.layout_wzty[position]);
				} else if (adapter_xzty == gridView.getAdapter())
				{
					intent.putExtra("id", AppConstant.layout_xzty[position]);
				}
				startActivity(intent);
			}
		});
	}

	private void findViews()
	{
		title = (TextView) findViewById(R.id.main_title);
		radioButton_lcty = (RadioButton) findViewById(R.id.main_footbar_lcty);
		radioButton_wzty = (RadioButton) findViewById(R.id.main_footbar_wzty);
		radioButton_xzty = (RadioButton) findViewById(R.id.main_footbar_xzty);
		radioButton_shak_i_like = (RadioButton) findViewById(R.id.main_footbar_shake_i_like);
		gridView = (GridView) findViewById(R.id.gridview);
	}

	private OnCheckedChangeListener listener = new OnCheckedChangeListener()
	{
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked)
		{
			switch (buttonView.getId())
			{
			//龙城太原
			case R.id.main_footbar_lcty:
				//改变按钮状态
				if (buttonView.isChecked())
				{
					radioButton_wzty.setChecked(false);
					radioButton_xzty.setChecked(false);
					//改变标题
					title.setText(R.string.mian_lcty);
					//设置主图标显示的适配器
					gridView.setAdapter(adapter_lcty);
				}

				break;
			//玩在太原
			case R.id.main_footbar_wzty:
				if (buttonView.isChecked())
				{
					radioButton_lcty.setChecked(false);
					radioButton_xzty.setChecked(false);
					title.setText(R.string.mian_wzty);
					gridView.setAdapter(adapter_wzty);
				}

				break;
			//学在太原
			case R.id.main_footbar_xzty:
				if (buttonView.isChecked())
				{
					radioButton_lcty.setChecked(false);
					radioButton_wzty.setChecked(false);
					title.setText(R.string.mian_xzty);
					gridView.setAdapter(adapter_xzty);
				}

				break;
			//摇我喜欢	
			case R.id.main_footbar_shake_i_like:
				//改变按钮状态
				if (buttonView.isChecked())
				{
					radioButton_shak_i_like.setChecked(false);//防止回退时是按下状态

					Intent intent = new Intent(MainActivity.this,
							ShakeILikeActivity.class);
					startActivity(intent);
					overridePendingTransition(R.anim.fade_in, R.anim.hold);//淡入
				}

				break;
			default:
				break;
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.menu_my_collection:
			Intent intent = new Intent(this, MyFavoriteActivity.class);
			this.startActivity(intent);
			break;

		case R.id.menu_about:
			Intent aboutIntent = new Intent(this, AboutActivity.class);
			startActivity(aboutIntent);
			break;

		case R.id.menu_exit:
			System.exit(0);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	// 回退键
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK))
		{
			new AlertDialog.Builder(MainActivity.this)
					.setTitle("真的要离开？")
					.setNegativeButton("取消", null)
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener()
							{
								public void onClick(DialogInterface dialog,
										int which)
								{
									System.exit(0);
								}
							}).show();
		}
		return super.onKeyDown(keyCode, event);
	}

}
