package com.liuhai.answerwork.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daquexian.flexiblerichtextview.FlexibleRichTextView;
import com.liuhai.answerwork.AnSwerPage;
import com.liuhai.answerwork.R;
import com.liuhai.bean.NumberDate;
import com.liuhai.bean.State;

import java.util.List;

/**
 * 答题的adapter
 * @author liuhai
 */
public class NumDateAdapter extends RecyclerView.Adapter<NumDateAdapter.NumberViewHolder> {
    List<NumberDate> list;
    Activity context;
    AnSwerPage.CommitAnSwer commitAnSwer;
    boolean disabletouch;

    public NumDateAdapter(List<NumberDate> list, Activity context,AnSwerPage.CommitAnSwer commitAnSwer) {
        this.list = list;
        this.context=context;
        this.commitAnSwer=commitAnSwer;
    }

    public boolean isDisabletouch() {
        return disabletouch;
    }

    public void setDisabletouch(boolean disabletouch) {
        this.disabletouch = disabletouch;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context,R.layout.fragment_num_item,null);
        NumberViewHolder viewHolder=new NumberViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final NumberViewHolder numberViewHolder, final int i) {
        final NumberDate numberDate=list.get(i);
        numberViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!disabletouch) {
                    //清除别的选项，设置自身
                    for (NumberDate date : list
                    ) {
                        date.setChose(false);
                    }
                    numberDate.setChose(true);

                    String answer = State.getValue(list.get(i).getHead()).getAnswer();
                    commitAnSwer.commit(((AnSwerPage) context).getViewPager().getCurrentItem(), State.getValue(list.get(i).getHead()),false);

                    notifyDataSetChanged();
                }


            }
        });

        if(numberDate.isChose()){
            numberViewHolder.img_flag.setImageResource(R.mipmap.answer_icon_ring_selected);

        }else{

            numberViewHolder.img_flag.setImageResource(R.mipmap.answer_icon_ring_default);
        }
        numberViewHolder.head.setText(numberDate.getHead()+".");
        numberViewHolder.content.setText(numberDate.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NumberViewHolder extends RecyclerView.ViewHolder{

        TextView head;
        FlexibleRichTextView content;
        ImageView img_flag;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            img_flag=itemView.findViewById(R.id.img_flag);
           head= itemView.findViewById(R.id.txt_head);
           content= itemView.findViewById(R.id.txt_content);
        }
    }


}
