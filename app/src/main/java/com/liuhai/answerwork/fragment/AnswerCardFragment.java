package com.liuhai.answerwork.fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.liuhai.answerwork.AnSwerPage;
import com.liuhai.answerwork.R;
import com.liuhai.answerwork.adapter.NumDateAdapter;
import com.liuhai.answerwork.dateutils.Httputils;
import com.liuhai.answerwork.utils.Content;
import com.liuhai.answerwork.utils.GsonUtils;
import com.liuhai.answerwork.utils.StringUtils;
import com.liuhai.bean.BasicAnserDate;
import com.liuhai.bean.NumberDate;
import com.liuhai.bean.SelectDetailDate;
import com.liuhai.bean.State;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 答题卡的主要界面
 * @author liuhai
 */
public class AnswerCardFragment extends Fragment implements AnSwerPage.CommitAnSwer {


    private View view;

    private TextView txt_title,txt_result,txt_desc;
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
                    txt_desc.setText(date.getAnswerdetail()==null?"":date.getAnswerdetail());
                    List<String> titils=StringUtils.getSelectdate(date.getContent());
                    if(titils!=null&&titils.size()>0) {
                        txt_title.setText(titils.get(0));
                        //题目题目
                        for (int i=1; i<titils.size();i++){
                            try {
                                String[] nums=titils.get(i).split("、");
                                NumberDate numberDate=new NumberDate(nums[0],nums[1]);
                                list.add(numberDate);
                            }catch (Exception e){
                                e.printStackTrace();
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
    public void commit(int i, State state) {
        //显示遮罩 从网络获取师傅正确的状态
        group.setVisibility(View.VISIBLE);
        ((AnSwerPage)getActivity()).getGuides().get(i).setState(state);
        Toast.makeText(getActivity().getApplicationContext(),"当前选择的是第"+i+"题目"+state.getAnswer()+"选项",1).show();






    }
}
