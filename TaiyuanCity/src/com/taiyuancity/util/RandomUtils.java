package com.taiyuancity.util;

import java.util.Random;

import com.taiyuancity.app.AppConstant;

/**
 * 随机出现, 通过layout的id来操作
 * 
 * @author neng
 * @date 2013-2-10
 */
public class RandomUtils
{
	/**
	 * 返回layout数组中int值, 出错时返回-1
	 * 
	 * @return
	 */
	public static int randomLayout()
	{
		Random randomArray = new Random();
		int whichArray = randomArray.nextInt(3);	//0-2
		//System.out.println("3个数组中的第" + whichArray + "个!");
		switch (whichArray)
		{
		case 0:
			return AppConstant.layout_lcty[randomArray
					.nextInt(AppConstant.layout_lcty.length)];
		case 1:
			return AppConstant.layout_wzty[randomArray
					.nextInt(AppConstant.layout_wzty.length)];
		case 2:
			return AppConstant.layout_xzty[randomArray
					.nextInt(AppConstant.layout_xzty.length)];
		default:
			break;
		}
		return -1;
	}
}
