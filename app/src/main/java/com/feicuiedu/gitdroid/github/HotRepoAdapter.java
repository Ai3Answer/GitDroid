package com.feicuiedu.gitdroid.github;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.feicuiedu.gitdroid.github.repolist.RepoListFragment;
import com.feicuiedu.gitdroid.github.repolist.model.Language;

import java.util.List;

/**
 * Created by gqq on 2016/12/2.
 */

public class HotRepoAdapter extends FragmentPagerAdapter{

    private List<Language> data;

    public HotRepoAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new RepoListFragment();
    }

    @Override
    public int getCount() {
        return 10;
    }

    // 拿到ViewPager的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return "java"+position;
    }
}
