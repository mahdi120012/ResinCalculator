package com.e.resincalculator;

public class RecyclerModel {
    private String id;
    private String onvan;
    private String matn;
    private String picture;
    private String link;
    public RecyclerModel(String id, String onvan, String matn , String picture, String link) {
        this.id = id;
        this.onvan = onvan;
        this.matn = matn;
        this.picture = picture;
        this.link = link;
    }


    public String getId() {
        return id;
    }

    public String getOnvan() {
        return onvan;
    }

    public String getMatn() {
        return matn;
    }

    public String getPicture() {
        return picture;
    }

    public String getLink() {
        return link;
    }
}