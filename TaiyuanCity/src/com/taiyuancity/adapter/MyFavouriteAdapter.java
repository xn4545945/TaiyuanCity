package com.taiyuancity.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.taiyuancity.R;
import com.taiyuancity.bean.MyFavourite;
import com.taiyuancity.bean.MyFavouriteHolder;

/**
 * 我的收藏listview的适配器.
 * 
 * @author neng
 * @date 2013-2-20
 */
public class MyFavouriteAdapter extends BaseAdapter
{
	private LayoutInflater inflater; 	// 用来布局的。
	private List<MyFavourite> myFavouritesLists; //实体集
	private Context context; 

	public MyFavouriteAdapter(Context context, int[] titles, int[] contents,
			int[] images)
	{
		this.context = context;
		inflater = LayoutInflater.from(context);
		myFavouritesLists = new ArrayList<MyFavourite>();

		for (int i = 0; i < titles.length; i++)
		{
			myFavouritesLists.add(new MyFavourite(titles[i], contents[i],
					images[i]));
		}
	}

	public MyFavouriteAdapter(Context context,
			List<MyFavourite> myFavouritesLists)
	{
		this.context = context;
		this.myFavouritesLists = myFavouritesLists;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount()
	{
		// 返回List的大小
		if (null != myFavouritesLists)
		{
			return myFavouritesLists.size();
		} else
		{
			return 0;
		}
	}

	@Override
	public Object getItem(int position)
	{
		return myFavouritesLists.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		MyFavouriteHolder myFavouriteHolder;
		// 获取layout布局文件
		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.bean_my_favourite, null);
			myFavouriteHolder = new MyFavouriteHolder();

			myFavouriteHolder.title = (TextView) convertView
					.findViewById(R.id.myFavourite_title);
			myFavouriteHolder.content = (TextView) convertView
					.findViewById(R.id.myFavourite_content);
			myFavouriteHolder.img = (ImageView) convertView
					.findViewById(R.id.myFavourite_image);
			convertView.setTag(myFavouriteHolder);
		} else
		{
			myFavouriteHolder = (MyFavouriteHolder) convertView.getTag();
		}
		// 给控件赋值
		myFavouriteHolder.title.setText(context.getString(myFavouritesLists
				.get(position).getTitle()));
		myFavouriteHolder.content.setText(context.getString(myFavouritesLists
				.get(position).getContent()));
		myFavouriteHolder.img.setImageResource(myFavouritesLists.get(position)
				.getImageId());

		return convertView;
	}

}