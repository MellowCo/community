package com.li.community.entity;

/**
 * @Description:
 * @Author: li
 * @Create: 2020-01-12 17:17
 */
public class GithubUser {
    private String login;
    private long id;


    @Override
    public String toString() {
        return "GithubUser{" +
                "login='" + login + '\'' +
                ", id=" + id +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
