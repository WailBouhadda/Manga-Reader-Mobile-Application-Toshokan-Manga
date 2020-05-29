package com.example.toshokan_manga.Model;

public class Manga {
    int background;
    public String mangaName;
    public  String  mangaka;

    public Manga() {
    }

    public Manga(int background, String mangaName, String mangaka) {
        this.background = background;
        this.mangaName = mangaName;
        this.mangaka = mangaka;
    }

    public int getBackground() {
        return background;
    }

    public String getMangaName() {
        return mangaName;
    }

    public String getMangaka() {
        return mangaka;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public void setMangaName(String mangaName) {
        this.mangaName = mangaName;
    }

    public void setMangaka(String mangaka) {
        this.mangaka = mangaka;
    }
}
