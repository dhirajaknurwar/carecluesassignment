package com.master.care.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.master.care.R;
import com.master.care.model.DoctorsDataModel;
import com.master.care.ui.base.BaseActivity;
import com.master.care.ui.chat.ChatActivity;
import com.master.care.utils.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements DataContract.View, HomeDataAdapter.Callbacks {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.view_no_content)
    View mNoContent;

    @BindView(R.id.newsRecyclerView)
    RecyclerView mNewsRecyclerView;

    @Inject
    DataContract.Presenter mPresenter;

    private List<DoctorsDataModel.DataModel> dataModelArrayList = new ArrayList<>();
    private HomeDataAdapter mHomeDataAdapter;

    private int pageNo = 1;
    private boolean loadingData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHomeDataAdapter = new HomeDataAdapter(dataModelArrayList);
        mHomeDataAdapter.setCallbacks(this);
        mNewsRecyclerView.setAdapter(mHomeDataAdapter);

        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        mNewsRecyclerView.addItemDecoration(new DividerDecoration(getApplicationContext()));
        mSwipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, R.color.colorPrimaryDark)
        );

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                dataModelArrayList.clear();
                mPresenter.loadData(true, pageNo);
            }
        });

        mNewsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!recyclerView.canScrollVertically(1)) {
                    onScrolledToEnd();
                } else if (dy < 0) {
                    onScrolledUp();
                } else if (dy > 0) {
                    onScrolledDown();
                }

            }


            public void onScrolledUp() {

            }

            public void onScrolledDown() {

            }

            public void onScrolledToEnd() {

                if (!loadingData) {
                    pageNo = pageNo + 1;
                    loadingData = true;
                    //showData(false);
                    mPresenter.loadData(true, pageNo);
                }
            }
        });


       // showData(false);
        mPresenter.loadData(true, pageNo);


    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }

    private void showData(boolean b) {

        int recyclerViewVisibility = b ? View.VISIBLE : View.GONE;
        mNewsRecyclerView.setVisibility(recyclerViewVisibility);
    }

    @Override
    public void onDataClick(@NonNull DoctorsDataModel.DataModel dataModel) {
        ChatActivity.launchActivity(MainActivity.this, String.valueOf(dataModel.getFirstName()+" "+dataModel.getLastName()));
    }

    @Override
    public void showLoading(boolean active) {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(active);
            }
        });
    }

    @Override
    public void showData(@NonNull DoctorsDataModel doctorsDataModel) {
        loadingData = false;
        dataModelArrayList.addAll(doctorsDataModel.getData());
        mHomeDataAdapter.notifyDataSetChanged();
        showData(true);
    }

    @Override
    public void showNoData() {
        showData(false);
    }
}
