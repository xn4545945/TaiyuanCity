package com.taiyuancity.bean;

/**
 * 主网格的单个图标实体类. 全用int是因为存储的是资源的id
 * 
 * @author neng
 * @date 
 */
public class MainIcon
{
	private int title;
	private int imageId;

	public MainIcon(int title, int imageId)
	{
		super();
		this.title = title;
		this.imageId = imageId;
	}

	public int getTitle()
	{
		return title;
	}

	public void setTitle(int title)
	{
		this.title = title;
	}

	public int getImageId()
	{
		return imageId;
	}

	public void setImageId(int imageId)
	{
		this.imageId = imageId;
	}
}
