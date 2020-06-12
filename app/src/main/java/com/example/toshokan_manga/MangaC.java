package com.example.toshokan_manga;

public class MangaC {

    private String im;
    private String t;
    private String a;
    private Long last_chapter_date;
    private Long k;

    public MangaC(String im, String t, String a, Long last_chapter_date, Long k) {
        this.im = im;
        this.t = t;
        this.a = a;
        this.last_chapter_date = last_chapter_date;
        this.k =k;
    }

    public Long getLast_chapter_date() {
        return last_chapter_date;
    }

    public void setLast_chapter_date(Long last_chapter_date) {
        this.last_chapter_date = last_chapter_date;
    }

    public MangaC() {

    }

    public Long getK() {
        return k;
    }

    public void setK(Long k) {
        this.k = k;
    }

    public String getIm() {
        return im;
    }

    public String getT() {
        return t;
    }

    public String getA() { return a;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public void setT(String t) {
        this.t = t;
    }

    public void setA(String a) {
        this.a = a;
    }
}
