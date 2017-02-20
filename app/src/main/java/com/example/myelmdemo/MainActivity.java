package com.example.myelmdemo;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myelmdemo.utils.CommonListAdapter;
import com.example.myelmdemo.utils.CommonRecyclerAdapter;
import com.example.myelmdemo.utils.ListViewHolder;
import com.example.myelmdemo.utils.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.example.myelmdemo.R.id.toolbar;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private CollapsingToolbarLayout mCollapsingToolBar;
    private ListView mListView;
    private List<NoodleEntity> mDataList = new ArrayList<>();
    private MyListAdapter mListAdapter;
    private View headerView;
    private List<String> mRecyclerDataList = new ArrayList<>();
    private MyRecyclerAapter mRecyclerAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        init();
        initData();
    }

    //初始化列表数据
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
        mListView.setAdapter(mListAdapter);


        mRecyclerDataList.add("全部");
        mRecyclerDataList.add("蜂鸟专送");
        mRecyclerDataList.add("品牌馆");
        mRecyclerDataList.add("离我最近");
        mRecyclerDataList.add("￥20以下");
        mRecyclerDataList.add("￥20-￥30");
        mRecyclerDataList.add("￥30-￥40");
        mRecyclerDataList.add("￥40以上");
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        mListView.addHeaderView(mRecyclerView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(MainActivity.this,DetailsActivity.class));
            }
        });

    }

    private void init() {

        mCollapsingToolBar.setTitle("");
        mToolBar.setTitle("");

        //左侧小箭头的返回事件
        setSupportActionBar(mToolBar);
        //决定左上角图标的右侧是否有向左的小箭头,true 有
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mListAdapter = new MyListAdapter(this,mDataList,R.layout.item_list);
        mRecyclerAdapter = new MyRecyclerAapter(this,mRecyclerDataList,R.layout.item_recycler);




    }

    private void initView() {
        mToolBar = (Toolbar)findViewById(toolbar);
        mCollapsingToolBar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mListView = (ListView)findViewById(R.id.lv_list);
        headerView = View.inflate(this, R.layout.header_view,null);
        mRecyclerView = (RecyclerView) headerView.findViewById(R.id.rv_header);
    }

    class MyListAdapter extends CommonListAdapter<NoodleEntity>{

        public MyListAdapter(Context context, List<NoodleEntity> datas, int layoutId) {
            super(context, datas, layoutId);
        }

        @Override
        public void convert(ListViewHolder holder, NoodleEntity noodleEntity, int postion) {
            holder.setImageResId(R.id.iv_item_image,noodleEntity.getImageRes());
            holder.setText(R.id.tv_item_title,noodleEntity.getTitle());
            holder.setText(R.id.tv_item_month_sale,"月售"+noodleEntity.getMonthSale()+"份");
            holder.setText(R.id.tv_item_good_evaluate,"好评率"+noodleEntity.getGoodEvaluate()+"%");
            holder.setText(R.id.tv_item_address,noodleEntity.getAddress());
            holder.setText(R.id.tv_item_price,"￥"+noodleEntity.getPrice());
            holder.setText(R.id.tv_item_cheap,noodleEntity.getActivity());
        }
    }

    class MyRecyclerAapter extends CommonRecyclerAdapter<String>{

        public MyRecyclerAapter(Context ctx, List<String> list, int LayoutId) {
            super(ctx, list, LayoutId);
        }

        @Override
        public void bindData(RecyclerViewHolder holder, int position, String item) {
            holder.setText(R.id.tv_item_recycler,item);
        }
    }
}
