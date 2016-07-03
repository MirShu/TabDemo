package com.jat.tabdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {

    private ViewPager vp_fragment;
    private RadioGroup rg_tab;
    private HorizontalScrollView hsv_tab;
    private PageFragmentAdapter adapter = null;
    private List<Fragment> fragmentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    //初始化界面
    public void initView(){
        rg_tab = (RadioGroup) findViewById(R.id.rg_tab);
        vp_fragment = (ViewPager) findViewById(R.id.vp_fragment);
        hsv_tab = (HorizontalScrollView) findViewById(R.id.hsv_tab);
        //点击监听
        rg_tab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                vp_fragment.setCurrentItem(checkedId);
            }
        });
        vp_fragment.setOnPageChangeListener(this);
        initTab();//动态产生radiobutton
        initViewPager();//加载viewpager中的fragment
        rg_tab.check(0);//设置当前fragment为当前的第一个
    }

    /**
     * 初始化tab
     */
    private void initTab(){
        //获取tab标题等内容
        List<Channel> channelList = ChannelDb.getSelectedChannel();
        for (int i = 0 ; i<channelList.size() ;i++){
            //加载RadioButton
            RadioButton rb = (RadioButton) LayoutInflater.from(this).inflate(R.layout.tab_rb,null);
            rb.setId(i);
            rb.setText(channelList.get(i).getName());
            //设置button参数
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,RadioGroup.LayoutParams.WRAP_CONTENT);
            //将radiobutton添加到radioGround中
            rg_tab.addView(rb,params);
        }
    }
    /**
     *添加fragment并且设置viewpager适配器
     */
    private void initViewPager(){
        List<Channel> channelList = ChannelDb.getSelectedChannel();
        for (int i = 0 ;i <channelList.size() ; i++){
            NewsFragment fragment = new NewsFragment();
            //向fragment传值
            Bundle bundle = new Bundle();
            bundle.putString("weburl",channelList.get(i).getWeburl());
            bundle.putString("name",channelList.get(i).getName());
            fragment.setArguments(bundle);//向fragment传入数据
            fragmentList.add(fragment);
        }
        adapter= new PageFragmentAdapter(super.getSupportFragmentManager(),fragmentList);
        vp_fragment.setAdapter(adapter);
    }

    /**
     * 滑动viewpager时调整scrollview的位置以便显示按钮
     * @param index
     */
    private void setTab(int index){
        RadioButton radioButton = (RadioButton) rg_tab.getChildAt(index);
        radioButton.setChecked(true);
        int left = radioButton.getLeft();
        int width = radioButton.getMeasuredWidth();
        DisplayMetrics metrics = new DisplayMetrics();
        super.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        int len = left + width/2 -screenWidth/2;
        hsv_tab.smoothScrollTo(len,0);//滑动scrollview
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 选择页面
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        setTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
