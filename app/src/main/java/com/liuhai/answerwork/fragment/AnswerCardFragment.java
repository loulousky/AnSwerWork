package com.liuhai.answerwork.fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daquexian.flexiblerichtextview.FlexibleRichTextView;
import com.liuhai.answerwork.AnSwerPage;
import com.liuhai.answerwork.R;
import com.liuhai.answerwork.adapter.NumDateAdapter;
import com.liuhai.answerwork.dateutils.Httputils;
import com.liuhai.answerwork.utils.Content;
import com.liuhai.answerwork.utils.GsonUtils;
import com.liuhai.answerwork.utils.StringUtils;
import com.liuhai.bean.BasicAnserDate;
import com.liuhai.bean.InvaildAnswer;
import com.liuhai.bean.NumberDate;
import com.liuhai.bean.SelectDetailDate;
import com.liuhai.bean.State;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 答题卡的主要界面
 * @author liuhai
 */
public class AnswerCardFragment extends Fragment implements AnSwerPage.CommitAnSwer {


    private View view;

    private FlexibleRichTextView txt_title,txt_desc;
    private TextView txt_result;
    private ImageView img_result;
    private RecyclerView recyclerView;
    private ViewGroup group;
    private NumDateAdapter recycleAdapter;
    private List<NumberDate> list=new ArrayList<>();
    private boolean loadover=false;
    private ProgressBar bar;

    public AnswerCardFragment() {
    }


    /**
     *
     * @param blockid 题目组id
     * @param examid  题目id
     * @return
     */
    public static AnswerCardFragment getInstance(int questionID,int blockid,int examid){
         AnswerCardFragment answerCardFragment=new AnswerCardFragment();
         Bundle bundle=new Bundle();
         bundle.putInt("blockid",blockid);
         bundle.putInt("examid",examid);
        bundle.putInt("questionID",questionID);
         answerCardFragment.setArguments(bundle);
         return answerCardFragment;




    }

    private int blockId,examId,questionId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view==null){
            view = inflater.inflate(R.layout.fragment_answer_card, container, false);
            if (getArguments() != null) {
                blockId = getArguments().getInt("blockid");
                examId = getArguments().getInt("examid");
                questionId = getArguments().getInt("questionID");
            }
            intView();
            loadDate();
        }
        return view;
    }

    private void intView() {
        txt_title=view.findViewById(R.id.txt_title);
        recyclerView=view.findViewById(R.id.recyclview);
        group=view.findViewById(R.id.group_result);
       img_result= view.findViewById(R.id.img_result);
        txt_result=view.findViewById(R.id.txt_result);
        txt_desc=view.findViewById(R.id.txt_desc);
        bar = view.findViewById(R.id.progress_circular);
    }

    private void findView() {
        recycleAdapter=new NumDateAdapter(list,getActivity(),this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper. VERTICAL);
        recyclerView.setAdapter( recycleAdapter);
    }


    private void loadDate(){
        Httputils.HttpGet(Content.QuestionDeatil(questionId, blockId, examId), new StringCallback() {


            @Override
            public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                BasicAnserDate<SelectDetailDate> result=GsonUtils.getResult(response.body(), SelectDetailDate.class);
                if(null==result){
                    return;
                }

                SelectDetailDate date=result.getObj();
                //提取题目的数据

                if(date!=null&&date.getContent()!=null){
                    if(date.getContent().contains("|")&&!date.getContent().contains("**")) {
                        txt_desc.setText(date.getAnswerdetail() == null ? "" : date.getAnswerdetail());
                        List<String> titils = StringUtils.getSelectdate(date.getContent());
                        if (titils != null && titils.size() > 0) {
                            txt_title.setText(titils.get(0));
                            //题目题目
                            for (int i = 1; i < titils.size(); i++) {
                                try {
                                    String[] nums = titils.get(i).split("、");
                                    NumberDate numberDate = new NumberDate(nums[0], nums[1]);
                                    list.add(numberDate);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }
                        }

                    }else if(date.getContent().contains("**")){
                        txt_desc.setText(date.getAnswerdetail() == null ? "" : date.getAnswerdetail());
                        List<String> titils = StringUtils.getSelectdateStar(date.getContent());
                        if (titils != null && titils.size() > 0) {
                            txt_title.setText(titils.get(0));

                         List<String> titlsdolr=   StringUtils.getSelectdatedolr(titils.get(1));

                            for (int i = 0; i <titlsdolr.size() ; i++) {


                                titlsdolr.set(i, titlsdolr.get(i).replace("、","、$$")+"$$");



                            }
                            //题目题目
                            for (int i = 0; i < titlsdolr.size(); i++) {
                                try {
                                    String[] nums = titlsdolr.get(i).split("、");
                                    NumberDate numberDate = new NumberDate(nums[0], nums[1]);
                                    list.add(numberDate);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }
                        }

                    }
                    findView();
                    loadover=true;
                }
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<String> response) {
                super.onError(response);
                loadover=false;
            }

            @Override
            public void onFinish() {
                super.onFinish();
                bar.setVisibility(View.GONE);
            }
        });



    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser&&loadover){

        }
    }



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        loadover=false;

    }

    @Override
    public void commit(final int i, final State state,boolean s) {
        //显示遮罩 从网络获取师傅正确的状态

        ((AnSwerPage)getActivity()).getGuides().get(i).setState(state);
        //从网络获取数据判断答案师傅正确
        if(s&&state!=State.NOANSWER) {
            Httputils.HttpGet(Content.QuestionInvaild(questionId, blockId, examId), new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    if (getActivity().isFinishing()) {

                        return;
                    }

                    if (response == null || response.body() == null) {
                        return;
                    }
                    BasicAnserDate<InvaildAnswer> result = GsonUtils.getResult(response.body(), InvaildAnswer.class);
                    if (null == result) {
                        return;
                    }

                    InvaildAnswer answers = result.getObj();
                    if (answers != null) {
                        txt_desc.setText(answers.getAnswerdetail() == null ? "" : answers.getAnswerdetail());
                        //答案正确
                        if (answers.getAnswer() != null && answers.getAnswer().toUpperCase().equals(state.getAnswer())) {
                            txt_result.setText("正确");
                            txt_result.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                            img_result.setImageResource(R.mipmap.answer_icon_yes_normal);
                            group.setVisibility(View.VISIBLE);
                            ((AnSwerPage) getActivity()).getGuides().get(i).setCurrentCorrect(true);

                        } else {
                            txt_result.setText("错误");
                            txt_result.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                            img_result.setImageResource(R.mipmap.answer_icon_no_normal);
                            group.setVisibility(View.VISIBLE);
                            ((AnSwerPage) getActivity()).getGuides().get(i).setCurrentCorrect(false);

                        }

                        recycleAdapter.setDisabletouch(true);


                    }
                }

                @Override
                public void onError(Response<String> response) {
                    super.onError(response);
                    Toast.makeText(getContext().getApplicationContext(), "数据获取失败，请重新提交", 0).show();

                }
            });

            Toast.makeText(getActivity().getApplicationContext(),"当前选择的是第"+i+"题目"+state.getAnswer()+"选项",0).show();
        }else{
            if(s) {
                Toast.makeText(getActivity().getApplicationContext(), "没有选择答案可以跳过此题进行下一题,如果不填可以直接提交", 0).show();
            }
        }
    }

    @Override
    public void reDo() {
        recycleAdapter.setDisabletouch(false);
        //btn可用，颜色回复，字体回复
        //checkstate 状态全部恢复默认状态
    }
}
