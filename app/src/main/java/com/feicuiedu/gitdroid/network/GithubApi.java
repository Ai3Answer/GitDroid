package com.feicuiedu.gitdroid.network;

import com.feicuiedu.gitdroid.github.hotuser.HotUserResult;
import com.feicuiedu.gitdroid.github.repoinfo.RepoContentResult;
import com.feicuiedu.gitdroid.github.repolist.model.Repo;
import com.feicuiedu.gitdroid.github.repolist.model.RepoResult;
import com.feicuiedu.gitdroid.login.model.AccessToken;
import com.feicuiedu.gitdroid.login.model.User;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by gqq on 2016/12/8.
 */

public interface GithubApi {
    // 构建接口请求

    // 进行开发应用注册得到的应用信息
    String CLIENT_ID = "ba09905a8f518e73941f";
    String CLIENT_SECRET = "dea61db22c9aa008ac1733730bd2a763d6648047";
    String AUTH_SCOPE= "user,public_repo,repo";

    String CALL_BACK = "feicui";

    String AUTH_URL = "https://github.com/login/oauth/authorize?client_id="+CLIENT_ID+"&scope="+AUTH_SCOPE;

    /**
     * 授权登录的Token获取
     * 三个参数是需要上传的数据
     * @param clientId
     * @param clientSecret
     * @param code
     * @return
     */
    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("https://github.com/login/oauth/access_token")
    Call<AccessToken> getOAuthToken(
            @Field("client_id")String clientId,
            @Field("client_secret")String clientSecret,
            @Field("code")String code);

    /**
     * 获取用户信息
     * @return
     */
    @GET("/user")
    Call<User> getUser();

    /**
     * 获取仓库列表
     * @param q 语言
     * @param page 页数
     * @return
     */
    @GET("/search/repositories")
    Call<RepoResult> searchRepos(
            @Query("q")String q,
            @Query("page")int page);

    /**
     * 热门用户
     * @param query
     * @param pageId
     * @return
     */
    // https://api.github.com/search/users?q=followers:>1000
    @GET("/search/users")
    Call<HotUserResult> serachUser(@Query("q")String query, @Query("page")int pageId);

    /**
     * 获取readme
     * @param owner 仓库拥有者
     * @param repo 仓库名称
     * @return
     */
    @GET("/repos/{owner}/{repo}/readme")
    Call<RepoContentResult> getReadme(@Path("owner") String owner,
                                      @Path("repo") String repo);

    /**
     * 获取MarkDown文件内容，内容以HTML形式展示出来
     * @param body
     * @return
     */
    @Headers({"Content-Type:text/plain"})
    @POST("/markdown/raw")
    Call<ResponseBody> markDown(@Body RequestBody body);

}
