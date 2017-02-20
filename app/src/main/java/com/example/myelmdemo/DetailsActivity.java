package com.example.myelmdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import android.widget.ImageView;

import android.widget.RelativeLayout;

import com.example.myelmdemo.utils.CommonRecyclerAdapter;
import com.example.myelmdemo.utils.RecyclerViewHolder;
import com.example.myelmdemo.view.MyAppBarLayout;
import com.example.myelmdemo.view.WrapRecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.y;


/**
 * Created by wjn on 2017/2/14.
 */

public class DetailsActivity extends AppCompatActivity {

    private CollapsingToolbarLayoutState state;
    private List<NoodleEntity> mDataList = new ArrayList<>();
    private MyRecyclerAdapter mAdapter;
    private View mFooterMoreV;
    private View mHeaderV;
    private RelativeLayout rvbottom;
    private CoordinatorLayout coordinatorlayout;
    private float transactionY;

    private float currentY;
    private AppBarLayout.OnOffsetChangedListener listerner;

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    Toolbar toolbar;
    ImageView ivImage;
    CollapsingToolbarLayout collapsingtoolbar;
    WrapRecyclerView rlrecycler;
    private MyAppBarLayout app_bar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
        initData();
        init();

    }

    private void initData() {

        NoodleEntity entity1 = new NoodleEntity();
        entity1.setImageRes(getResources().getDrawable(R.mipmap.one));
        entity1.setTitle("麻酱牛筋面");
        entity1.setMonthSale("126");
        entity1.setGoodEvaluate("100");
        entity1.setAddress("凉皮先生羊肉汤（银河soho）");
        entity1.setPrice("13");
        entity1.setActivity("满25减8");

        NoodleEntity entity2 = new NoodleEntity();
        entity2.setImageRes(getResources().getDrawable(R.mipmap.two));
        entity2.setTitle("酸辣牛筋面");
        entity2.setMonthSale("121");
        entity2.setGoodEvaluate("100");
        entity2.setAddress("凉皮先生羊肉汤（银河soho）");
        entity2.setPrice("13");
        entity2.setActivity("满25减8");

        NoodleEntity entity3 = new NoodleEntity();
        entity3.setImageRes(getResources().getDrawable(R.mipmap.three));
        entity3.setTitle("重庆小面微辣-小碗");
        entity3.setMonthSale("202");
        entity3.setGoodEvaluate("90");
        entity3.setAddress("重庆小面馆（银河soho）");
        entity3.setPrice("17");
        entity3.setActivity("满20减6");

        NoodleEntity entity4 = new NoodleEntity();
        entity4.setImageRes(getResources().getDrawable(R.mipmap.four));
        entity4.setTitle("扁豆焖面");
        entity4.setMonthSale("160");
        entity4.setGoodEvaluate("94");
        entity4.setAddress("康师傅私房牛肉面（银河soho）");
        entity4.setPrice("28");
        entity4.setActivity("满25减8");

        NoodleEntity entity5 = new NoodleEntity();
        entity5.setImageRes(getResources().getDrawable(R.mipmap.five));
        entity5.setTitle("经典红烧牛肉面");
        entity5.setMonthSale("218");
        entity5.setGoodEvaluate("94");
        entity5.setAddress("康师傅私房牛肉面（银河soho）");
        entity5.setPrice("28");
        entity5.setActivity("满25减8");


        NoodleEntity entity6 = new NoodleEntity();
        entity6.setImageRes(getResources().getDrawable(R.mipmap.six));
        entity6.setTitle("一品焖面");
        entity6.setMonthSale("117");
        entity6.setGoodEvaluate("100");
        entity6.setAddress("合利屋（日坛店）");
        entity6.setPrice("22");
        entity6.setActivity("满25减6");

        NoodleEntity entity7 = new NoodleEntity();
        entity7.setImageRes(getResources().getDrawable(R.mipmap.seven));
        entity7.setTitle("炒乌冬面");
        entity7.setMonthSale("110");
        entity7.setGoodEvaluate("100");
        entity7.setAddress("凉皮先生羊肉汤（银河soho）");
        entity7.setPrice("22");
        entity7.setActivity("满25减8");

        NoodleEntity entity8 = new NoodleEntity();
        entity8.setImageRes(getResources().getDrawable(R.mipmap.eight));
        entity8.setTitle("岐山臊子面（大）");
        entity8.setMonthSale("84");
        entity8.setGoodEvaluate("100");
        entity8.setAddress("凉皮先生羊肉汤（银河soho）");
        entity8.setPrice("22");
        entity8.setActivity("满30减4");

        mDataList.add(entity1);
        mDataList.add(entity2);
        mDataList.add(entity3);
        mDataList.add(entity4);
        mDataList.add(entity5);
        mDataList.add(entity6);
        mDataList.add(entity7);
        mDataList.add(entity8);
    }

    private float progress = 1;

    private void init() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //决定左上角图标的右侧是否有向左的小箭头,true 有
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //左侧小箭头的返回事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        listerner = new AppBarLayout.OnOffsetChangedListener() {


            @Override
            public void onOffsetChanged(final AppBarLayout appBarLayout, int verticalOffset) {

                progress = ((float) Math.abs(verticalOffset)) / ((float) appBarLayout.getTotalScrollRange());
                rlrecycler.setAlpha(progress);
                rvbottom.setAlpha(progress);
                if (verticalOffset == 0) {//展开
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记展开
                       /* Log.e("展开verticalOffset", verticalOffset+"");
                        Log.e("展开getTotalScrollRange:",appBarLayout.getTotalScrollRange()+"");   state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                       */ collapsingtoolbar.setTitle("");//设置title为EXPANDED
                        rlrecycler.setAlpha(0);
                        rvbottom.setAlpha(0);
                        app_bar.setTranslationY(200);
                        rlrecycler.setTranslationY(200);
                        app_bar.setScaleX(0.8f);
                        app_bar.setScaleY(0.8f);
                        app_bar.setExpanded(true);
                        app_bar.isExpanded(true);



                    }

                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {


                  /*  Log.e("缩小verticalOffset", verticalOffset+"");
                    Log.e("缩小getTotalScrollRange:",appBarLayout.getTotalScrollRange()+"");
*/
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {//缩小后
                        collapsingtoolbar.setTitle("一碗小面");//设置title不显示
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                        rlrecycler.setAlpha(1);
                        rvbottom.setAlpha(1);
                        app_bar.setTranslationY(0);
                        rlrecycler.setTranslationY(0);
                        app_bar.setScaleX(1f);
                        app_bar.setScaleY(1f);
                        app_bar.isExpanded(false);

                    }

                } else {
                 /*   Log.e("verticalOffset", verticalOffset+"");
                    Log.e("getTotalScrollRange:",appBarLayout.getTotalScrollRange()+"");
                 */   if ((progress + 0.8f) > 1) {
                        app_bar.setScaleX(1);
                        app_bar.setScaleY(1);
                    } else {
                        app_bar.setScaleX(progress + 0.8f);
                        app_bar.setScaleY(progress + 0.8f);
                    }
                    transactionY = 200*(1-progress);
                    app_bar.setTranslationY(transactionY);
                    rlrecycler.setTranslationY(transactionY);

                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {//正在缩小
                        collapsingtoolbar.setTitle("");//设置title为INTERNEDIATE
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                        app_bar.isExpanded(false);

                    }
                }
            }


        };
        app_bar.addOnOffsetChangedListener(listerner);

        mAdapter = new MyRecyclerAdapter(this, mDataList, R.layout.item_details_recycler);
        rlrecycler.setItemAnimator(new DefaultItemAnimator());
        rlrecycler.setLayoutManager(new GridLayoutManager(this, 2));
        rlrecycler.addFootView(initFooterMoreView());
        rlrecycler.addHeaderView(initHeaderView());
        rlrecycler.setAdapter(mAdapter);

        app_bar.setActivity(this);


    }



    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        collapsingtoolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        coordinatorlayout = (CoordinatorLayout) findViewById(R.id.cl_coordinatorlayout);
        app_bar = (MyAppBarLayout) findViewById(R.id.app_bar);
        rlrecycler = (WrapRecyclerView) findViewById(R.id.rl_recycler);
        rvbottom = (RelativeLayout) findViewById(R.id.rv_bottom);


    }

    /**
     * 更多样式
     *
     * @return
     */
    private View initFooterMoreView() {

        if (mFooterMoreV == null)
            mFooterMoreV = LayoutInflater.from(this).inflate(R.layout.inc_v_more, rlrecycler, false);//这样写就可以解决不居中的问题。
        // 上拉更多
        return mFooterMoreV;
    }

    /**
     * 头布局
     *
     * @return
     */
    private View initHeaderView() {

        if (mHeaderV == null)
            mHeaderV = LayoutInflater.from(this).inflate(R.layout.item_header_details, rlrecycler, false);//这样写就可以解决不居中的问题。
        // 上拉更多
        return mHeaderV;
    }

    class MyRecyclerAdapter extends CommonRecyclerAdapter<NoodleEntity> {

        public MyRecyclerAdapter(Context ctx, List<NoodleEntity> list, int LayoutId) {
            super(ctx, list, LayoutId);
        }

        @Override
        public void bindData(RecyclerViewHolder holder, int position, NoodleEntity item) {
            holder.setText(R.id.item_rl_name,item.getTitle()).setText(R.id.item_rl_sale,"月售"+item.getMonthSale()+"份");
            holder.setText(R.id.item_rl_price,"￥"+item.getPrice());
            holder.setImageResId(R.id.item_rl_image,item.getImageRes());

        }
    }
}
