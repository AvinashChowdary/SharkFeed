package com.example.avinashravilla.sharkfeed.model.imagedetails;

public class Publiceditability {
    private String canaddmeta;

    private String cancomment;

    public String getCanaddmeta() {
        return canaddmeta;
    }

    public void setCanaddmeta(String canaddmeta) {
        this.canaddmeta = canaddmeta;
    }

    public String getCancomment() {
        return cancomment;
    }

    public void setCancomment(String cancomment) {
        this.cancomment = cancomment;
    }

    @Override
    public String toString() {
        return "ClassPojo [canaddmeta = " + canaddmeta + ", cancomment = " + cancomment + "]";
    }
}