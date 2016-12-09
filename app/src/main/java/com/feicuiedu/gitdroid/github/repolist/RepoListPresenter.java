package com.feicuiedu.gitdroid.github.repolist;

import com.feicuiedu.gitdroid.github.repolist.model.Language;
import com.feicuiedu.gitdroid.github.repolist.model.Repo;
import com.feicuiedu.gitdroid.github.repolist.model.RepoResult;
import com.feicuiedu.gitdroid.network.GithubClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.id;

/**
 * Created by gqq on 2016/12/9.
 */

// 仓库列表的业务类
public class RepoListPresenter {

    private RepoListView mRepoListView;

    private Language mLanguage;
    private int mNextPage = 1;


    public RepoListPresenter(Language language,RepoListView repoListView) {
        mLanguage = language;
        mRepoListView = repoListView;
    }

    // 刷新的方法
    public void refreshdata(){
        mNextPage = 1;
        Call<RepoResult> repoResultCall = GithubClient.getInstance().searchRepos("language:" + mLanguage.getPath(), mNextPage);
        repoResultCall.enqueue(refreshCallback);
    }

    // 加载更多的方法
    public void loadMore(){
        Call<RepoResult> repoResultCall = GithubClient.getInstance().searchRepos("language:" + mLanguage.getPath(), mNextPage);
        repoResultCall.enqueue(loadMoreCallback);
    }

    private Callback<RepoResult> loadMoreCallback = new Callback<RepoResult>() {

        @Override
        public void onResponse(Call<RepoResult> call, Response<RepoResult> response) {
            if (response.isSuccessful()){
                RepoResult repoResult = response.body();
                if (repoResult==null){
                    // 显示加载错误
                    return;
                }
                if (repoResult.getTotalCount()<=0){
                    // 显示没有更多数据了
                    return;
                }
                List<Repo> repoList = repoResult.getItems();
                if (repoList!=null){
                    // 将加载出来的数据设置给ListView
                    mRepoListView.addLoadMore(repoList);
                    mNextPage++;
                }
            }
        }

        @Override
        public void onFailure(Call<RepoResult> call, Throwable t) {
            // 加载的视图停止
            // 弹个吐司
        }
    };

     private Callback<RepoResult> refreshCallback = new Callback<RepoResult>() {

         @Override
         public void onResponse(Call<RepoResult> call, Response<RepoResult> response) {
             if (response.isSuccessful()){
                 RepoResult body = response.body();
                 if (body==null){
                     // 显示一个空视图
                     return;
                 }
                 if (body.getTotalCount()<=0){
                     // 显示一个空视图
                     // 重新刷新数据。。。。
                     return;
                 }
                 // 获取到了数据
                 List<Repo> repoList = body.getItems();
                 if (repoList!=null){
                     // 设置数据了，设置给ListView
                     mRepoListView.addRefreshData(repoList);
                     mNextPage = 2;
                     return;
                 }
             }
             // 显示一个错误的视图
         }

         @Override
         public void onFailure(Call<RepoResult> call, Throwable t) {
            // 停止刷新
             // 弹吐司
         }
     };
}
