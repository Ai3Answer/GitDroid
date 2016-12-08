package com.feicuiedu.gitdroid.login;

import android.util.Log;

import com.feicuiedu.gitdroid.commons.LogUtils;
import com.feicuiedu.gitdroid.login.model.AccessToken;
import com.feicuiedu.gitdroid.login.model.User;
import com.feicuiedu.gitdroid.login.model.UserRepo;
import com.feicuiedu.gitdroid.network.GithubApi;
import com.feicuiedu.gitdroid.network.GithubClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gqq on 2016/12/8.
 */

// 登录的业务类
public class LoginPresenter {

    private Call<AccessToken> mTokenCall;
    private Call<User> mUserCall;

    public void login(String code) {
        /**
         * 1. 获取Token
         */
        if (mTokenCall != null) {
            mTokenCall.cancel();
        }
        mTokenCall = GithubClient.getInstance().getOAuthToken(
                GithubApi.CLIENT_ID,
                GithubApi.CLIENT_SECRET,
                code);
        mTokenCall.enqueue(tokenCallback);
    }

    private Callback<AccessToken> tokenCallback = new Callback<AccessToken>() {

        @Override
        public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {

            if (response.isSuccessful()){

                // 取出响应信息，得到Token值
                AccessToken accessToken = response.body();
                String token = accessToken.getAccessToken();

                // 存储Token值
                UserRepo.setAccessToken(token);

                // 根据token获取用户信息
                mUserCall = GithubClient.getInstance().getUser();
                mUserCall.enqueue(mUserCallback);

            }
        }

        @Override
        public void onFailure(Call<AccessToken> call, Throwable t) {
            LogUtils.e("onFailure"+t.getMessage());

        }
    };

    private Callback<User> mUserCallback = new Callback<User>() {
        @Override
        public void onResponse(Call<User> call, Response<User> response) {
            if (response.isSuccessful()){
                // 信息存储一下
                User user = response.body();
                UserRepo.setUser(user);
                LogUtils.e("请求的响应信息"+UserRepo.getUser().toString());
            }
        }

        @Override
        public void onFailure(Call<User> call, Throwable t) {

        }
    };
}
