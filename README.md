[video]
[image]

## 这是完全用kotlin写的Demo

## 主要代码存在 homepage Module

## build.gradle 引用的库


	implementation 'com.android.support:design:28.0.0-alpha3'

    implementation 'cn.bingoogolapple:bga-banner:2.2.4@aar'

    implementation 'com.github.hackware1993:MagicIndicator:1.5.0'

    implementation "org.jetbrains.anko:anko:0.10.5"

    implementation 'com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.30'

    implementation 'com.github.bumptech.glide:glide:4.5.0'

    implementation 'com.google.code.gson:gson:2.8.5'


**轮播图**  
**[BGABanner-Android](https://github.com/bingoogolapple/BGABanner-Android)**  

**tab**  
**[MagicIndicator](https://github.com/hackware1993/MagicIndicator)**  

**anko**  
**[anko](https://github.com/Kotlin/anko)**  
现在只用它toast()方法了 更多用法还要探索  

**[BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)**  

**[glide](https://github.com/bumptech/glide)**

**[gson](https://github.com/google/gson)**


HomeActivity 是不可滑动的  
HomeViewPagerActivity 可以滑动切换  

## 修改位置
数据（轮播图图片、公告文字、tab文字）  
HomeViewPagerActivity ->（tabList\imgList\mesList)  

TODO
轮播图样式
公告样式
tab颜色、样式
recyclerview布局




## 关键代码
	
	Theme修改成NoActionBar
	styles.xml

	<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
    </style>

    recyclerview分割线

    var layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = OrientationHelper.VERTICAL
        recycler_view.layoutManager = layoutManager
        recycler_view.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))


## 实体说明

	-activity
		HomeActivity // 没有viewpager 只有fragment
		HomeViewPagerActivity // 有viewpager
	-adapter
		ViewPagerAdapter // HomeViewPagerActivity 的 viewpager 适配器
	-fragment
		HomeFragment // HomeActivity
		TabFragment	 // ViewPagerAdapter 切换的 Fragment

	layout
	activity_home.xml // HomeActivity 布局
	activity_home_viewpager.xml // HomeViewPagerACtivity
	activity_main.xml // 
	item_banner.xml // 轮播图和公告
	item_textview.xml // ViewFlipper item
	item_view_flipper.xml // 
	layout_item.xml // recycler item
	recycler_view.xml // 一个recycler——view

## 使用的View

**轮播图**  
使用BGABanner三方库  
集成代码
	
	布局：

	<cn.bingoogolapple.bgabanner.BGABanner
        android:id="@+id/banner"
        android:layout_width="match_parent"
        android:layout_height="180dp"
        app:banner_pageChangeDuration="1000"
        app:banner_pointAutoPlayAble="true"
        app:banner_pointContainerBackground="@android:color/transparent"
        app:banner_pointDrawable="@drawable/bga_banner_selector_point_hollow"
        app:banner_pointTopBottomMargin="15dp"
        app:banner_transitionEffect="alpha" />


        代码：

        fun initBanner() {
        banner.setAdapter(BGABanner.Adapter<ImageView,String> { banner, itemView, model, position ->
            Glide.with(itemView.context)
                    .load(model)
                    .apply(RequestOptions().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).dontAnimate().centerCrop())
                    .into(itemView)
        })
        banner.setData(imgList, Arrays.asList("","",""))
        // 点击事件
        banner.setDelegate { banner, itemView, model, position -> toast(model.toString()+position) }
    }


**公告**  
这是用ViewFlipper实现的公告
	
	布局：
	<ViewFlipper
            android:id="@+id/view_flipper"
            android:layout_weight="1"
            android:layout_width="match_parent"
            android:layout_height="25dp"
            android:autoStart="true"
            android:layout_gravity="center_vertical"
            android:paddingTop="2dp"
            android:flipInterval="2000"
            android:inAnimation="@anim/anim_come_in"
            android:outAnimation="@anim/anim_get_out">
        </ViewFlipper>


    代码：

    fun initViewFlipper() {
        for (i in mesList.indices) {
            var view = LayoutInflater.from(this).inflate(R.layout.item_textview,null)
            view.findViewById<TextView>(R.id.text_view).text = mesList[i]
            view.setOnClickListener {
                toast(mesList[i]+i)
            }
            view_flipper.addView(view)
        }
        view_flipper.isAutoStart = true
    }


**tab**  
使用MagicIndicator实现

	布局：

	<net.lucode.hackware.magicindicator.MagicIndicator
            android:id="@+id/magic_indicator"
            android:layout_width="match_parent"
            android:layout_height="40dp" />

    代码：
    fun initMagicIndicator() {
        var commonNavigator = CommonNavigator(this)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return  if (tabList == null)0 else tabList.size
            }

            override fun getTitleView(p0: Context?, p1: Int): IPagerTitleView {
                var colorTransitionPagerTitleView = ColorTransitionPagerTitleView(p0)
                colorTransitionPagerTitleView.normalColor= ContextCompat.getColor(p0!!,R.color.abc_btn_colored_borderless_text_material)
                colorTransitionPagerTitleView.selectedColor = ContextCompat.getColor(p0!!,R.color.abc_btn_colored_borderless_text_material)
                colorTransitionPagerTitleView.text = tabList[p1].toUpperCase()

                colorTransitionPagerTitleView.setOnClickListener (View.OnClickListener {
                    //                    view_pager.currentItem = p1
                    fragmentContainerHelper.handlePageSelected(p1);
                    switchPages(p1)

                })

                return colorTransitionPagerTitleView
            }
            override fun getIndicator(p0: Context?): IPagerIndicator {
                var indicator: LinePagerIndicator = LinePagerIndicator(p0)
                indicator.mode = LinePagerIndicator.MODE_EXACTLY
                indicator.setColors(ContextCompat.getColor(p0!!,R.color.abc_btn_colored_borderless_text_material))
                return indicator

            }
        }
        magic_indicator.navigator = commonNavigator
        fragmentContainerHelper.attachMagicIndicator(magic_indicator)
    }

**viewpager**   

	布局：
	<android.support.v4.view.ViewPager
            android:id="@+id/view_pager"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />


    代码：
    fun initViewpager() {
        var adapter = ViewPagerAdapter(supportFragmentManager,tabList, "dfdff")
        view_pager.adapter = adapter
    }

    ViewPagerAdapter
    class ViewPagerAdapter(fm: FragmentManager?, var tabList:ArrayList<String>, var listStr:Any) : FragmentPagerAdapter(fm){

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var fragment :TabFragment = super.instantiateItem(container, position) as TabFragment
        fragment.updateArguments(position, listStr.toString())
        return fragment
    }

    override fun getCount(): Int {
        return  tabList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabList[position]
    }


    override fun getItem(position: Int): Fragment {
        return  TabFragment.create(position, Gson().toJson(listStr))
    }


    override fun notifyDataSetChanged() {
        super.notifyDataSetChanged()
    }
}


## 未完成

- []  添加下拉刷新
- [] fragment懒加载
- [] 数据动态获取

## 参考链接

[使用CoordinatorLayout打造各种炫酷的效果](https://www.jianshu.com/p/f09723b7e887)  

[kotlin循环控制](http://www.runoob.com/kotlin/kotlin-loop-control.html)  

[Android循环滚动控件——ViewFlipper的使用](https://blog.csdn.net/u011150924/article/details/60867499)  

[Kotlin定义静态变量、静态方法](https://blog.csdn.net/xuyonghong1122/article/details/80268981)  

[Gradle中文用户组](https://www.jianshu.com/c/9e24d4c23a84)  

[Groovy语言规范》-语法](http://ifeve.com/groovy-syntax/)





















