package com.simanjit.dxminds.model;

import java.io.Serializable;
import java.util.List;

public class NewsResponse implements Serializable {
    private String totalResults;

    private List<Articles> articles;

    private String status;

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClassPojo [totalResults = " + totalResults + ", articles = " + articles + ", status = " + status + "]";
    }
}
