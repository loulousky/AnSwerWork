package com.liuhai.answerwork.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.liuhai.answerwork.R;

/**
 * 完成后的阴影遮挡
 * @author liuhai
 */
public class ShadowView extends RelativeLayout {
    public ShadowView(Context context) {
        super(context);
        initView();
    }

    public ShadowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }




    private void initView(){

        final View shadow=View.inflate(getContext(), R.layout.shadow_view,null);

        shadow.findViewById(R.id.txt_score);

        shadow.findViewById(R.id.txt_again).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibility(GONE);
                //To-DO ...
            }
        });

        shadow.findViewById(R.id.img_close).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setVisibility(GONE);
            }
        });


        shadow.findViewById(R.id.qq).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        shadow.findViewById(R.id.wehat).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        shadow.findViewById(R.id.weibo).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        shadow.findViewById(R.id.qqspace).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        shadow.findViewById(R.id.nothing).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        shadow.findViewById(R.id.pyq).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

      addView(shadow);
    }







}
