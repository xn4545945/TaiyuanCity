package com.taiyuancity.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.taiyuancity.R;
import com.taiyuancity.adapter.MyFavouriteAdapter;
import com.taiyuancity.bean.MyFavourite;
import com.taiyuancity.dao.MyFavouriteDAO;
import com.taiyuancity.util.ConstantUtils;

/**
 * "我的收藏"主控类
 * 
 * @author neng
 * @date 2013-2-22
 */
public class MyFavoriteActivity extends Activity
{
	private ImageButton back;
	private ListView listView;
	private MyFavouriteAdapter myFavouriteAdapter;
	private MyFavouriteDAO myFavouriteDAO; //查询数据库
	private int[] layoutIds;			//查询到的所有layoutId

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_favourite);

		myFavouriteDAO = new MyFavouriteDAO(this);

		initListView();

		//回退按钮设置
		back = (ImageButton) findViewById(R.id.my_favourite_head_back);
		back.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				MyFavoriteActivity.this.finish();
			}
		});
	}

	/**
	 * listview设置
	 */
	private void initListView()
	{
		listView = (ListView) findViewById(R.id.listview);
		myFavouriteAdapter = new MyFavouriteAdapter(this, getMyFavouriteLists());
		listView.setAdapter(myFavouriteAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				Intent intent = new Intent(MyFavoriteActivity.this,
						ChildActivity.class);
				//传递Intent参数, layout的id
				intent.putExtra("id", layoutIds[position]);
				startActivity(intent);
			}
		});

		//为listview注册ContextMenu
		registerForContextMenu(listView);
	}

	/**
	 * 获取MyFavourite的列表, 用来设置adapter
	 * 
	 * @return
	 */
	public List<MyFavourite> getMyFavouriteLists()
	{
		List<MyFavourite> myFavouritesLists = new ArrayList<MyFavourite>();
		//得到数据库中所有layoutId
		layoutIds = myFavouriteDAO.findAll();
		//找出layoutId对应的资源, 封装成MyFvourite给myFavouritesLists
		for (int i = 0; i < layoutIds.length; i++)
		{
			Log.i("MyFavoriteActivity", "layoutId-->" + i + " :" + layoutIds[i]);
			//去空
			if (0 != layoutIds[i])
			{
				myFavouritesLists.add(ConstantUtils
						.getMyFavourite(layoutIds[i]));
				//Log.i("MyFavoriteActivity","MyFavourite-->"+ i+ " :"+ getString((ConstantUtils.getMyFavourite(layoutIds[i])).getContent()));
			} else
				break;
		}
		return myFavouritesLists;
	}

	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo mi)
	{
		menu.setHeaderTitle(R.string.menu_my_favourite_context_title);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_my_favourite_context_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item)
	{
		//得到选中的项
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		//根据位置获取填充, 填充类型是MyFavourite
		MyFavourite myFavourite = (MyFavourite) listView
				.getItemAtPosition((int) menuInfo.id);
		switch (item.getItemId())
		{
		//		case Menu.FIRST:
		case R.id.menu_my_favourite_context_no:
			int layoutIdDel = ConstantUtils.getLayoutId(myFavourite);//得到layoutID
			myFavouriteDAO.delete(layoutIdDel);
			Toast.makeText(MyFavoriteActivity.this,
					getString(R.string.db_my_favourite_delete),
					Toast.LENGTH_SHORT).show();
			//重新设置适配器以更新界面
			myFavouriteAdapter = new MyFavouriteAdapter(this,
					getMyFavouriteLists());
			listView.setAdapter(myFavouriteAdapter);
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
}