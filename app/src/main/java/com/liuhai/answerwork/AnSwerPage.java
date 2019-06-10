package com.liuhai.answerwork;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.liuhai.answerwork.adapter.ClumAdapter;
import com.liuhai.answerwork.dateutils.Httputils;
import com.liuhai.answerwork.fragment.AnswerCardFragment;
import com.liuhai.answerwork.utils.Content;
import com.liuhai.answerwork.utils.GsonUtils;
import com.liuhai.answerwork.utils.StringUtils;
import com.liuhai.answerwork.view.ShadowView;
import com.liuhai.bean.BasicAnserDate;
import com.liuhai.bean.ChoseState;
import com.liuhai.bean.QuestDate;
import com.liuhai.bean.QuestDetailDate;
import com.liuhai.bean.QuestDetailDateTwo;
import com.liuhai.bean.QuestStateDate;
import com.liuhai.bean.State;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;

/**
 * 答题首页
 */
public class AnSwerPage extends AppCompatActivity implements View.OnClickListener {
    ImageView img_back,img_share;
    TextView txt_title,txt_num;
    Button btn_answercard,btn_before,btn_after,btn_sure;
    ViewPager viewPager;
    List<Fragment> fragmentList=new ArrayList<>();
    //保存最初始状态用于重做功能
    List<Fragment> clonefragment=new ArrayList<>();
    ShadowView shadowView;
    //选项List 固定高度？
    RecyclerView clume_recyclview;


    //最后提交的时候，每夜的答案从这里拿

    // guides.get(i).getState.getvalue() 拿到答案的选项
    List<ChoseState> guides=new ArrayList<>();
    private int examid;
    private int blockid;

    public List<ChoseState> getGuides() {
        return guides;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_an_swer_page);
         shadowView=new ShadowView(this);
        int shadowId=ViewCompat.generateViewId();
        shadowView.setId(shadowId);
        addshowAwordLayout();
        findView();
        intView();
    }

    private void findView() {
         img_back=findViewById(R.id.img_back);
         img_share=findViewById(R.id.img_share);
         txt_title=findViewById(R.id.txt_title);
         txt_num=findViewById(R.id.txt_num);
         viewPager=findViewById(R.id.viewpager);
         btn_answercard=findViewById(R.id.btn_answercard);
         btn_before=findViewById(R.id.btn_before);
         btn_after=findViewById(R.id.btn_after);
         btn_sure=findViewById(R.id.btn_sure);
         clume_recyclview=findViewById(R.id.clume_recyclview);


    }

    private void intView() {
        img_back.setOnClickListener(this);
        img_share.setOnClickListener(this);
        btn_answercard.setOnClickListener(this);
        btn_after.setOnClickListener(this);
        btn_before.setOnClickListener(this);
        btn_after.setOnClickListener(this);
        btn_sure.setOnClickListener(this);
        loadDate();
    }


private void showDate(){
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    clume_recyclview.setLayoutManager(layoutManager);
    layoutManager.setOrientation(OrientationHelper. VERTICAL);
    clume_recyclview.setAdapter(new ClumAdapter(guides,this,viewPager,clume_recyclview));
    Collections.copy(fragmentList,clonefragment);
    //让viewpager不可滑动
    viewPager.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    });

    viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }


        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);
        }
    });

    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
            txt_num.setText("("+(i+1)+"/"+fragmentList.size()+")");

            //有的选项卡没有选答案
            if(getGuides().get(i)!=null&&getGuides().get(i).getState()==State.NOANSWER){
                btn_sure.setText("确认");
            }

             //如果是最后一页 而且没有选择答案则为确认，最后一页选过了为提交，未提交返回上一页，如果没有选择则为确认
            if(i==fragmentList.size()-1&&(getGuides().get(i)!=null&&getGuides().get(i).getState()==State.NOANSWER)){
                btn_sure.setText("确认");
            }else if(i==fragmentList.size()-1){
                btn_sure.setText("提交");
            }


        }

        @Override
        public void onPageSelected(int i) {

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    });

    viewPager.setOffscreenPageLimit(fragmentList.size());
}
    /**
     * 获取数据生成fragment
     */
    private void loadDate(){


        Httputils.HttpGet(Content.QuestionsCollect("54"), new StringCallback() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<String> response) {boolean flag=false;
                if(AnSwerPage.this.isFinishing()){
                    return;
                }

                try {
                    JSONObject jsonObject=new JSONObject(response.body());
                    jsonObject.getJSONObject("obj").getJSONArray("examInfoList");
                } catch (JSONException e) {
                    e.printStackTrace();
                    //如果报错，表示 是对象
                    flag=true;
                }

                List<QuestDate> datelist=null;

                if(flag) {
                    BasicAnserDate<QuestDetailDate> result = null;
                    try {
                        result = GsonUtils.getResult(response.body(), QuestDetailDate.class);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (result == null) {
                        return;
                    }
                    if (null == result.getObj()) {
                        return;
                    }
                    //每夜数据传递给各个fragment
                    txt_title.setText(result.getObj().getTitle());
                    datelist = result.getObj().getExamInfoList().getObj();

                }else{

                    BasicAnserDate<QuestDetailDateTwo> result = null;
                    try {
                        result = GsonUtils.getResult(response.body(), QuestDetailDateTwo.class);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (result == null) {
                        return;
                    }
                    if (null == result.getObj()) {
                        return;
                    }
                    txt_title.setText(result.getObj().getTitle());
                    //每夜数据传递给各个fragment
                    datelist = result.getObj().getExamInfoList();

                }

                if(datelist!=null) {
                    for (int i=0;i<datelist.size();i++
                    ) {
                        QuestDate qdate=datelist.get(i);
                        if(qdate==null){
                            continue;
                        }
                        blockid = qdate.getBlockId();
                        examid = qdate.getExamId();
                        AnswerCardFragment fragment= AnswerCardFragment.getInstance(qdate.getQuestionId(),qdate.getBlockId(),qdate.getExamId());
                        fragmentList.add(fragment);
                        StringBuilder builder=new StringBuilder();
                        builder.append("第").append(i+1+"").append("题");
                        guides.add(new ChoseState(builder.toString(),State.NOANSWER));
                    }
                }

                showDate();




            }


        });
//        if(fragmentList.size()>0){
//            return;
//        }
//
//        for(int i=0;i<10;i++){
//            fragmentList.add(new AnswerCardFragment());
//            guides.add(new String("第"+(i+1)+"提"));
//        }


    }

    public ViewPager getViewPager() {
        return viewPager;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.img_back:

                finish();

                break;
            case R.id.img_share:
                break;

            case R.id.btn_answercard:
                float translationY=clume_recyclview.getTranslationY();
                if(!clume_recyclview.isShown()){
                    ObjectAnimator animator=ObjectAnimator.ofFloat(clume_recyclview,"translationY",translationY,0);
                    animator.setDuration(500);
                    animator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                              clume_recyclview.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {


                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                    animator.start();

                }else{
                    ObjectAnimator animator=ObjectAnimator.ofFloat(clume_recyclview,"translationY",translationY,clume_recyclview.getHeight());
                    animator.setDuration(500);
                    animator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            clume_recyclview.setVisibility(View.GONE);

                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                    animator.start();
                }

                break;

            case R.id.btn_before:
                viewPager.setCurrentItem((viewPager.getCurrentItem()-1)<0?0:viewPager.getCurrentItem()-1);

                break;

            case R.id.btn_after:
                viewPager.setCurrentItem((viewPager.getCurrentItem()+1)>fragmentList.size()-1?viewPager.getCurrentItem():viewPager.getCurrentItem()+1);
                break;

            case R.id.btn_sure:
                AnswerCardFragment cardFragment= (AnswerCardFragment) fragmentList.get(viewPager.getCurrentItem());
                cardFragment.commit(viewPager.getCurrentItem(),getGuides().get(viewPager.getCurrentItem()).getState(),true);
              //  Toast.makeText(getApplicationContext(),"点击去定提交了"+"当前页面的答案是"+guides.get(viewPager.getCurrentItem()).getState().getAnswer(),1).show();
                //显示得分，具体逻辑自己改
                if(viewPager.getCurrentItem()==fragmentList.size()-1) {
                    if (btn_sure.getText().equals("提交")) {


                        //提交答案
                        String answers = StringUtils.getAnsweers(getGuides());
                        String url = Content.QuestionCommit(blockid, examid, answers);
                        Httputils.HttpGet(url, new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {

                                if (AnSwerPage.this.isFinishing()) {
                                    return;
                                }
                                btn_sure.setClickable(false);
                                btn_sure.setText("已提交");
                                btn_sure.setTextColor(Color.parseColor("#f6f6f6"));
                                if (response.body() != null && !response.body().equals("")) {
                                    try {
                                        JSONObject result = new JSONObject(response.body());
                                        if (result.has("obj")) {
                                            int score = result.getInt("obj");
                                            showshodow();
                                            //禁用提交
                                            btn_sure.setClickable(false);
                                            btn_sure.setText("已提交");
                                            btn_sure.setTextColor(Color.parseColor("#f6f6f6"));
                                            Toast.makeText(getApplicationContext(), "当前得分：" + score, 0).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }else{
                        btn_sure.setText("提交");
                    }
                }
                break;

        }


    }

    /**
     * 全局遮挡
     */
    private void addshowAwordLayout(){
        ViewGroup viewGroup= (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
        hideshadow();
        viewGroup.addView(shadowView);


    }

    private void showshodow(){
        shadowView.setVisibility(View.VISIBLE);
    }

    private void hideshadow(){

        shadowView.setVisibility(View.GONE);
    }

    /**
     * 选择后提交选择的题目和答案
     */
    public  interface CommitAnSwer{


        /**
         *
         * @param i 第几道提
         * @param state 答案 A,B,C,D。。。
         * @param shouldConnectNet 是否联网判断是否正确
         */
        public void commit(int i,State state,boolean shouldConnectNet);


        /**
         * 点击遮罩的重做按钮
         */
        public void reDo();


    }
}
