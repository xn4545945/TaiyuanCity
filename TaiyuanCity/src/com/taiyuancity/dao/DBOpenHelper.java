package com.taiyuancity.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 这个文件是用SQLiteOpenHelper创建SQLite数据库，以及管理其版本.
 */
public class DBOpenHelper extends SQLiteOpenHelper
{
	private static final int VERSION = 1;// 版本是从 1 开始的.修改版本后执行upgrade方法。
	private static final String DBNAME = "favourite.db";// 数据库名
	public static final String MyFavouriteTable = "t_favourite"; //表名, 不可更改

	public DBOpenHelper(Context context)
	{
		super(context, DBNAME, null, VERSION);
	}

	// 安装的时候。当有表的时候是不执行的。只执行一次。
	public void onCreate(SQLiteDatabase db)
	{
		// 创建表
		db.execSQL("create table " + MyFavouriteTable
				+ "(layoutId integer primary key)");
	}

	// 数据库是有版本的，这个执行版本的更新，备份等
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{

	}

}
