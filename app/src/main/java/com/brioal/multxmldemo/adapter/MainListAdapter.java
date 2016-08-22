package com.brioal.multxmldemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.brioal.multxmldemo.R;
import com.brioal.multxmldemo.entity.ThingsEntity;
import com.brioal.multxmldemo.interfaces.OnChangeListener;

import java.util.List;

/**
 * Main RecyclerView Adapter
 * Created by Brioal on 2016/8/22.
 */

public class MainListAdapter extends RecyclerView.Adapter {
    private final int TYPE_TIME = 0;
    private final int TYPE_THING = 1;
    private int mFirstNum = 0;
    private int mSecondNum = 0;
    private int mThirdNum = 0;

    private Context mContext;
    private List<ThingsEntity> mList;
    private OnChangeListener mListener;

    public MainListAdapter(Context context, List<ThingsEntity> list, OnChangeListener listener) {
        mContext = context;
        mList = list;
        mFirstNum = 0;
        mSecondNum = 0;
        mThirdNum = 0;
        for (int i = 0; i < mList.size(); i++) {
            ThingsEntity entity = mList.get(i);
            switch (entity.getLabel()) {
                case "First":
                    mFirstNum++;
                    break;
                case "Second":
                    mSecondNum++;
                    break;
                case "Third":
                    mThirdNum++;
                    break;
            }
        }
        this.mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TIME) {
            return new TimeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_time, parent, false));
        } else if (viewType == TYPE_THING) {

            return new ThingViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_thing, parent, false));
        }
        return null;
    }

    public void change() {
        mFirstNum = 0;
        mSecondNum = 0;
        mThirdNum = 0;
        for (int i = 0; i < mList.size(); i++) {
            ThingsEntity entity = mList.get(i);
            switch (entity.getLabel()) {
                case "First":
                    mFirstNum++;
                    break;
                case "Second":
                    mSecondNum++;
                    break;
                case "Third":
                    mThirdNum++;
                    break;
            }
        }
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof TimeViewHolder) {
            final ThingsEntity entity = mList.get(position + 1);
            ((TimeViewHolder) holder).mTime.setText(entity.getLabel());
            final int finalPosition2 = position;
            ((TimeViewHolder) holder).mAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        ThingsEntity addEntity = new ThingsEntity(entity.getLabel(), "New Things");
                        mListener.add(addEntity, getRightPosition(finalPosition2+1));
                    }
                }
            });
            final int finalPosition3 = position;
            ((TimeViewHolder) holder).mDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.delete(entity, getRightPosition(finalPosition3+1));
                    }
                }
            });
        } else if (holder instanceof ThingViewHolder) {
            position = getRightPosition(position);
            ThingsEntity entity = mList.get(position);
            ((ThingViewHolder) holder).mThing.setText(entity.getTitle());
        }
    }

    public int getRightPosition(int position) {
        if (position < mFirstNum + 1) {
            position = position - 1;
        } else if (position <= mFirstNum + mSecondNum + 2) {
            position = position - 2;
        } else {
            position = position - 3;
        }
        return position;
    }


    @Override
    public int getItemCount() {
        return mList.size() + 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position != 0 && position != (mFirstNum + 1) && position != (mFirstNum + mSecondNum + 2)) {
            return TYPE_THING;
        }
        return TYPE_TIME;
    }

    //Time Label ViewHolder
    class TimeViewHolder extends RecyclerView.ViewHolder {
        TextView mTime;
        Button mAdd;
        Button mDel;

        public TimeViewHolder(View itemView) {
            super(itemView);
            mTime = (TextView) itemView.findViewById(R.id.item_tv_time);
            mAdd = (Button) itemView.findViewById(R.id.item_btn_add);
            mDel = (Button) itemView.findViewById(R.id.item_btn_del);
        }
    }

    //Thing Label ViewHolder
    class ThingViewHolder extends RecyclerView.ViewHolder {
        TextView mThing;

        public ThingViewHolder(View itemView) {
            super(itemView);
            mThing = (TextView) itemView.findViewById(R.id.item_tv_thing);
        }
    }
}
