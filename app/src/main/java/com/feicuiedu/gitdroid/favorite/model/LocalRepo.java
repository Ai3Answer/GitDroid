package com.feicuiedu.gitdroid.favorite.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by gqq on 2016/12/12.
 */
// 表明他是一张数据库表
@DatabaseTable(tableName = "local_repo")
public class LocalRepo {

    /**
     * id : 22374063
     * name : android-best-practices
     * full_name : futurice/android-best-practices
     * avatar_url : https://avatars.githubusercontent.com/u/852157?v=3
     * description : Do's and Don'ts for Android development, by Futurice developers
     * stargazers_count : 10469
     * forks_count : 1974
     */

    // 主键，一般是递增，不能重复
    @DatabaseField(id = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField(columnName = "full_name")
    @SerializedName("full_name")
    private String fullName;

    @DatabaseField(columnName = "avatar_url")
    @SerializedName("avatar_url")
    private String avatar;

    @DatabaseField
    private String description;

    @DatabaseField(columnName = "stargazers_count")
    @SerializedName("stargazers_count")
    private int starCount;

    @DatabaseField(columnName = "forks_count")
    @SerializedName("forks_count")
    private int forksCount;

    // foreign 外键 ，主要的作用就是关联数据库另外一张表
//    @DatabaseField(columnName = "",foreign = true,canBeNull = true)
//    private Group mGroup;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }
}
