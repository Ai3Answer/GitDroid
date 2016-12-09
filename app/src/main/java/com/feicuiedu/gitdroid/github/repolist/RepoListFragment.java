package com.feicuiedu.gitdroid.github.repolist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feicuiedu.gitdroid.R;
import com.feicuiedu.gitdroid.github.repolist.model.Language;

/**
 * Created by gqq on 2016/12/2.
 */

public class RepoListFragment extends Fragment{

    private static final String KEY_LANGUAGE = "key_language";

    /**
     * 仓库列表页面：不同语言的仓库列表数据
     * 在这个页面里面展示的是不同语言的仓库列表：根据不同的语言去请求不同的数据
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repo_list,container,false);
    }

    /**
     * 不建议在构造方法里面传递数据，所以可以提供一个创建方法，进行传递
     */
    public static RepoListFragment getInstance(Language language){
        // 创建一个RepoListFragment
        RepoListFragment repoListFragment = new RepoListFragment();
        // 传递的数据
        Bundle bundle = new Bundle();
        // 传递的对象，注意：Language一定要实现序列化
        bundle.putSerializable(KEY_LANGUAGE,language);
        repoListFragment.setArguments(bundle);
        return repoListFragment;
    }

    // 拿到传递过来的数据
    private Language getLanguage(){
        return (Language) getArguments().getSerializable(KEY_LANGUAGE);
    }
}
