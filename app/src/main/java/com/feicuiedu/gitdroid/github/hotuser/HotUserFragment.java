package com.feicuiedu.gitdroid.github.hotuser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.login.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by gqq on 2016/12/13.
 */
public class HotUserFragment extends Fragment implements HotUserPresenter.HotUserView{

    private HotUserPresenter presenter;

    @BindView(R.id.ptrClassicFrameLayout)
    PtrClassicFrameLayout ptrClassicFrameLayout;

    @BindView(R.id.lvRepos)
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_user,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter = new HotUserPresenter(this);
        // 初始PulltoRefresh控件
        initPullToRefresh();
    }

    private void initPullToRefresh() {
        // 下拉刷新控件的监听(下拉时和上拉时会来回调的)
        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                presenter.loadMore();
            }
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refresh();
            }
        });
    }

    @Override
    public void refreshData(List<User> list) {

    }

    @Override
    public void showRefreshView() {

    }

    @Override
    public void stopRefresh() {

    }

    @Override
    public void addLoadData(List<User> list) {

    }

    @Override
    public void showLoadView() {

    }

    @Override
    public void hideLoadView() {

    }

    @Override
    public void showMessage(String msg) {

    }
}
