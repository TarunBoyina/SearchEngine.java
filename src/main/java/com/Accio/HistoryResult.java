package com.Accio;
public class HistoryResult {
    String keyword;
    String link;

    public HistoryResult(String keyword, String link) {
        this.keyword = keyword;
        this.link = link;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}