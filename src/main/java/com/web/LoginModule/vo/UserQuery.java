package com.web.LoginModule.vo;

import java.io.Serializable;

public class UserQuery implements Serializable {

    private int page;
    private int rows;
    private String queryName;
    private String queryType;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    @Override
    public String toString() {
        return "UserQuery{" +
                "page=" + page +
                ", rows=" + rows +
                ", queryName='" + queryName + '\'' +
                ", queryType='" + queryType + '\'' +
                '}';
    }
}
