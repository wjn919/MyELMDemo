# MyELMDemo
![image](https://github.com/wjn919/MyELMDemo/blob/master/app/screenshots/demo.gif)

### 介绍

这是一个仿照饿了么详情页的简单demo
   - CoordinateLayout+MyAppBarLayout+CollapsingToolbarLayout+recyclerView
   - 自定义CoordinateLayout，重写onTouchEvent事件，将move,up,事件在下拉的情况下传递给AppBarLayout
   - 在定义AppBarLayout，重写onTouchEvent事件，监听move和up事件完成下拉关闭activity
   - 上滑事件由CoordinateLayout嵌套AppBarLayout完成类似视差特效的效果

### APK下载

   - [apk 下载](https://github.com/wjn919/MyELMDemo/raw/master/app/screenshots/demo.apk)

### 关于我

   - 我的github：[wjn919](https://github.com/wjn919)
   - 我的CSDN博客：[wjn919](http://blog.csdn.net/wjn_yes)
