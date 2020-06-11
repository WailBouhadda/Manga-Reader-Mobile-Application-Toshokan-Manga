package com.example.toshokan_manga;

import java.util.List;

public class MangaInfo {
    private String title;
    private String artist;
    private String image;
    private Integer last_chapter_date;
    private String released;
    private String created;
    private String description;
    private String hits;
    private String chapters_len;
    private List<String> categories;

    public MangaInfo(String title, String artist, String image, Integer last_chapter_date, String released, String created, String description, String hits, String chapters_len, List<String> categories) {
        this.title = title;
        this.artist = artist;
        this.image = image;
        this.last_chapter_date = last_chapter_date;
        this.released = released;
        this.created = created;
        this.description = description;
        this.hits = hits;
        this.chapters_len = chapters_len;
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getImage() {
        return image;
    }

    public Integer getLast_chapter_date() {
        return last_chapter_date;
    }

    public String getReleased() {
        return released;
    }

    public String getCreated() {
        return created;
    }

    public String getDescription() {
        return description;
    }

    public String getHits() {
        return hits;
    }

    public String getChapters_len() {
        return chapters_len;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLast_chapter_date(Integer last_chapter_date) {
        this.last_chapter_date = last_chapter_date;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public void setChapters_len(String chapters_len) {
        this.chapters_len = chapters_len;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
