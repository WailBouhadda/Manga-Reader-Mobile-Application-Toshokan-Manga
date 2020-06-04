package com.example.toshokan_manga.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Manga {

    @SerializedName("im")
    public String background;
    @SerializedName("t")
    public String mangaName;

    public  String  mangaka;
    public int ID;
    public String alias;
    public  String status;


    public Manga() {
    }

    public Manga(String background, String mangaName, String mangaka) {
        this.background = background;
        this.mangaName = mangaName;
        this.mangaka = mangaka;
    }

    public String getBackground() {
        return background;
    }

    public String getMangaName() {
        return mangaName;
    }

    public String getMangaka() {
        return mangaka;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setMangaName(String mangaName) {
        this.mangaName = mangaName;
    }

    public void setMangaka(String mangaka) {
        this.mangaka = mangaka;
    }
}
