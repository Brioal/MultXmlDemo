package com.brioal.multxmldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.brioal.multxmldemo.adapter.MainListAdapter;
import com.brioal.multxmldemo.entity.ThingsEntity;
import com.brioal.multxmldemo.interfaces.OnChangeListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MainListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recyclerView);
        initRecyclerView();
    }


    private void initRecyclerView() {
        final List<ThingsEntity> list = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < 4; i++) {
            list.add(new ThingsEntity("First", "第" + (i + index) + "条"));
        }
        index = list.size();
        for (int i = 0; i < 4; i++) {
            list.add(new ThingsEntity("Second", "第" + (i + index) + "条"));
        }
        index = list.size();
        for (int i = 0; i < 4; i++) {
            list.add(new ThingsEntity("Third", "第" + (i + index) + "条"));
        }

        mAdapter = new MainListAdapter(this, list, new OnChangeListener() {
            @Override
            public void add(ThingsEntity entity, int position) {
                list.add(position, entity);
                mAdapter.change();
            }

            @Override
            public void delete(ThingsEntity entity, int position) {
                list.remove(position);
                mAdapter.change();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

}
