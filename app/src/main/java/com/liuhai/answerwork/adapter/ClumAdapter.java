package com.liuhai.answerwork.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewUtils;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.liuhai.answerwork.R;
import com.liuhai.bean.ChoseState;
import com.liuhai.bean.NumberDate;

import java.util.List;

/**
 * 题目导航
 * @author liuhai
 */
public class ClumAdapter extends RecyclerView.Adapter<ClumAdapter.ClumeViewHolder> {
    List<ChoseState> list;
    Context context;
    ViewPager viewPager;
    RecyclerView recyclerView;

    public ClumAdapter(List<ChoseState> list, Context context, ViewPager viewPager,RecyclerView recyclerView) {
        this.list = list;
        this.context=context;
        this.viewPager=viewPager;
        this.recyclerView=recyclerView;
    }

    @NonNull
    @Override
    public ClumeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context,R.layout.activity_clume_item,null);
        ClumeViewHolder viewHolder=new ClumeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ClumeViewHolder numberViewHolder, final int i) {
        final ChoseState guide=list.get(i);
        numberViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //跳到指定选项，隐藏自身
                float translationY=recyclerView.getTranslationY();
                ObjectAnimator animator=ObjectAnimator.ofFloat(recyclerView,"translationY",translationY,recyclerView.getHeight());
                animator.setDuration(500);
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        viewPager.setCurrentItem(i);
                        recyclerView.setVisibility(View.GONE);

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
        });

        numberViewHolder.guide.setText(guide.getLittle_title());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ClumeViewHolder extends RecyclerView.ViewHolder{

        TextView guide;

        public ClumeViewHolder(@NonNull View itemView) {
            super(itemView);
           guide= itemView.findViewById(R.id.txt_guide);
        }
    }


}
