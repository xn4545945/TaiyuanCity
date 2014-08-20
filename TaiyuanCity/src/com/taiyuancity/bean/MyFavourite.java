package com.taiyuancity.bean;

/**
 * 我的收藏ListView中填充实体类. 
 * 
 * @author neng
 * @date 2013-2-20
 */
public class MyFavourite
{
	private int title;
	private int content;
	private int imageId;

	public MyFavourite(int title, int content, int imageId)
	{
		super();
		this.title = title;
		this.content = content;
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

	public int getContent()
	{
		return content;
	}

	public void setContent(int content)
	{
		this.content = content;
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
