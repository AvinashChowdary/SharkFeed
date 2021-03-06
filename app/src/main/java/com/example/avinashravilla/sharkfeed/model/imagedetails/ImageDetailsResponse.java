package com.example.avinashravilla.sharkfeed.model.imagedetails;

public class ImageDetailsResponse {
    private Photo photo;

    private String stat;

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    @Override
    public String toString() {
        return "ClassPojo [photo = " + photo + ", stat = " + stat + "]";
    }
}