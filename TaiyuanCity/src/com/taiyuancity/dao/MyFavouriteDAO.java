package com.taiyuancity.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 数据库只存储一个layoutId.
 */
public class MyFavouriteDAO
{
	// 1、通过构造方法来实例化一个DBOpenHelper，这个DBOpenHelper类可以进行数据库的操作。
	private DBOpenHelper helper;
	private SQLiteDatabase db;

	public MyFavouriteDAO(Context context)
	{
		helper = new DBOpenHelper(context);
	}

	/**
	 * 数据库中添加信息
	 * 
	 * @param layoutId
	 */
	public void add(int layoutId)
	{
		db = helper.getWritableDatabase();
		// 是exec。两个参数，第二个是用来填充第一个中的占位符（？）的
		db.execSQL("insert into " + DBOpenHelper.MyFavouriteTable
				+ "(layoutId) values (?)", new Object[] { layoutId });
	}

	/**
	 * 查询信息
	 * 
	 * @param layoutId
	 * @return layoutId|-1,
	 */
	public int find(int layoutId)
	{
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select layoutId from "
				+ DBOpenHelper.MyFavouriteTable + " where layoutId = ?",
				new String[] { String.valueOf(layoutId) });
		// 判断第一个对象是否为空，如果不为空，则返回
		while (cursor.moveToNext())
		{
			// cursor.getColumnIndex("title")是在不知道索引的情况下通过名字来获取索引。
			return cursor.getInt(cursor.getColumnIndex("layoutId"));
		}
		return -1;
	}

	/**
	 * 查询所有layoutId信息
	 * 
	 * @return int[]
	 */
	public int[] findAll()
	{
		int[] layoutIds = new int[40];
		int i = 0;
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select layoutId from "
				+ DBOpenHelper.MyFavouriteTable, null);
		// 判断第一个对象是否为空，如果不为空，则返回
		while (cursor.moveToNext())
		{
			layoutIds[i] = cursor.getInt(cursor.getColumnIndex("layoutId"));
			i++;
		}
		return layoutIds;
	}

	/**
	 * 删除信息
	 * 
	 * @param ...layoutId,表示参数的个数是不固定的
	 */
	public void delete(Integer... layoutId)
	{
		if (layoutId.length > 0)
		{
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < layoutId.length; i++)
			{
				sb.append('?').append(',');
			}
			sb.deleteCharAt(sb.length() - 1);// 删除最后一个逗号。
			// delete from t_favourite where layoutId in (?,?,?,?);删除的是一段
			db = helper.getWritableDatabase();
			db.execSQL("delete from " + DBOpenHelper.MyFavouriteTable
					+ " where layoutId in (" + sb + ")", (Object[]) layoutId);
		}
	}

}
