TaiyuanCity
===========
介绍见：
http://blog.csdn.net/xn4545945/article/details/38788677

An Android App，Base on Android 2.3.3。

本人很早前写的一个小应用分享一下。主要是界面框架可以借鉴一下，app内容不是重点。

其中也借鉴了别人的一些东西，现在找不到链接了。

===============================================================

本App可借鉴的地方：（做的烂的地方直接PASS吧）

1.包括底部的自定义类似iOS中的TabBar；

2.摇一摇功能。

3.页面收藏及Sqlite的使用。

4.新特性界面制作。

5.启动画面。

6.ListView的自定义。

7.公共代码的抽取，包括XML布局文件中的头部与尾部等。

===============================================================



下面将简单的解析下项目：

**1、src目录**
src目录用于存放项目的包及java源码文件。

下面是src目录的子目录：
> src

> ├ com.taiyuancity.adapter

> ├ com.taiyuancity.app

> ├ com.taiyuancity.bean

> ├ com.taiyuancity.dao

> ├ com.taiyuancity.ui

> ├ com.taiyuancity.util

> ├ com.taiyuancity.widget


功能说明:
- com.taiyuancity.adapter — 列表、网格等适配器包（主页网格、收藏列表适配器）
- com.taiyuancity.app — 存储包应用程序启动、配置等
- com.taiyuancity.bean — 实体包（主页网格实体模型，收藏列表实体模型）
- com.taiyuancity.dao — 数据库（对应收藏与取消收藏用到）
- com.taiyuancity.ui — 界面包与activity（各种Activity）
- com.taiyuancity.util — 工具包 （摇一摇用到的随机函数，监听加速器检查摇动）
- com.taiyuancity.widget — 自定义控件包（自定义首次启动时的展示画面的scroll控件）


===============================================================


代码写的不足的地方敬请指正，Thks。
