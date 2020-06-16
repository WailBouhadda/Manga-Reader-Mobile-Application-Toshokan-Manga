package com.example.toshokan_manga;

public class chapters {

    String num;
    String url;

    public chapters(String chapternumber, String chapterurl) {
        this.num = chapternumber;
        this.url = chapterurl;
    }

    public chapters() {

    }

    public String getNum() {
        return num;
    }

    public String getUrl() {
        return url;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
