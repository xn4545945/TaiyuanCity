package com.taiyuancity.util;

import com.taiyuancity.app.AppConstant;
import com.taiyuancity.bean.MyFavourite;

/**
 * 负责appConstant类中常量的位置寻找, 为"我的收藏"数据库及listview服务的.
 * 
 * @author neng
 * @date 2013-2-20
 */
public class ConstantUtils
{
	/**
	 * 遍历3个layout数组, 找出其中与layoutId相等的, 返回其位置, 并集装成MyFavourite.
	 * 
	 * @param layoutId
	 * @return myFavourite | default
	 */
	public static MyFavourite getMyFavourite(int layoutId)
	{
		for (int i = 0; i < AppConstant.layout_lcty.length; i++)
		{
			if (layoutId == AppConstant.layout_lcty[i])
			{
				//找到即返回退出
				return new MyFavourite(AppConstant.title_lcty[i],
						AppConstant.content_lcty[i], AppConstant.image_lcty[i]);
			}
		}

		for (int i = 0; i < AppConstant.layout_wzty.length; i++)
		{
			if (layoutId == AppConstant.layout_wzty[i])
			{
				return new MyFavourite(AppConstant.title_wzty[i],
						AppConstant.content_wzty[i], AppConstant.image_wzty[i]);
			}
		}

		for (int i = 0; i < AppConstant.layout_xzty.length; i++)
		{
			if (layoutId == AppConstant.layout_xzty[i])
			{
				return new MyFavourite(AppConstant.title_xzty[i],
						AppConstant.content_xzty[i], AppConstant.image_xzty[i]);
			}
		}

		return new MyFavourite(AppConstant.title_default,
				AppConstant.content_default, AppConstant.image_default);
	}

	/**
	 * 根据myFavourite得到对应的layout的id
	 * 
	 * @param myFavourite
	 * @return
	 */
	public static int getLayoutId(MyFavourite myFavourite)
	{
		for (int i = 0; i < AppConstant.title_lcty.length; i++)
		{
			if (AppConstant.title_lcty[i] == myFavourite.getTitle())
			{
				return AppConstant.layout_lcty[i];
			}
		}

		for (int i = 0; i < AppConstant.title_wzty.length; i++)
		{
			if (AppConstant.title_wzty[i] == myFavourite.getTitle())
			{
				return AppConstant.layout_wzty[i];
			}
		}

		for (int i = 0; i < AppConstant.title_xzty.length; i++)
		{
			if (AppConstant.title_xzty[i] == myFavourite.getTitle())
			{
				return AppConstant.layout_xzty[i];
			}
		}

		return AppConstant.layout_default;
	}

}
