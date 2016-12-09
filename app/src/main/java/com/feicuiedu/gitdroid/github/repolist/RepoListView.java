package com.feicuiedu.gitdroid.github.repolist;

import com.feicuiedu.gitdroid.github.repolist.model.Repo;

import java.util.List;

/**
 * Created by gqq on 2016/12/9.
 */

public interface RepoListView {

    void addRefreshData(List<Repo> repos);
    void addLoadMore(List<Repo> repos);

}
