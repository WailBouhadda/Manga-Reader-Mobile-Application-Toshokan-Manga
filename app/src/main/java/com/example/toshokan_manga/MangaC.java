package com.example.toshokan_manga;

public class MangaC {

    private String im;
    private String t;
    private String a;

    public MangaC(String im, String t, String a) {
        this.im = im;
        this.t = t;
        this.a = a;
    }

    public MangaC() {

    }

    public String getIm() {
        return im;
    }

    public String getT() {
        return t;
    }

    public String getA() {
        return a;
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
