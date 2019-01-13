package com.example.avinashravilla.sharkfeed.model.imagedetails;

public class Usage {
    private String candownload;

    private String canblog;

    private String canprint;

    private String canshare;

    public String getCandownload() {
        return candownload;
    }

    public void setCandownload(String candownload) {
        this.candownload = candownload;
    }

    public String getCanblog() {
        return canblog;
    }

    public void setCanblog(String canblog) {
        this.canblog = canblog;
    }

    public String getCanprint() {
        return canprint;
    }

    public void setCanprint(String canprint) {
        this.canprint = canprint;
    }

    public String getCanshare() {
        return canshare;
    }

    public void setCanshare(String canshare) {
        this.canshare = canshare;
    }

    @Override
    public String toString() {
        return "ClassPojo [candownload = " + candownload + ", canblog = " + canblog + ", canprint = " + canprint + ", canshare = " + canshare + "]";
    }
}