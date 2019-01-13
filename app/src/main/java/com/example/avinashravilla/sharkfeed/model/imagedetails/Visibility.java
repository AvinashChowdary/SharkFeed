package com.example.avinashravilla.sharkfeed.model.imagedetails;

public class Visibility {
    private String isfamily;

    private String ispublic;

    private String isfriend;

    public String getIsfamily() {
        return isfamily;
    }

    public void setIsfamily(String isfamily) {
        this.isfamily = isfamily;
    }

    public String getIspublic() {
        return ispublic;
    }

    public void setIspublic(String ispublic) {
        this.ispublic = ispublic;
    }

    public String getIsfriend() {
        return isfriend;
    }

    public void setIsfriend(String isfriend) {
        this.isfriend = isfriend;
    }

    @Override
    public String toString() {
        return "ClassPojo [isfamily = " + isfamily + ", ispublic = " + ispublic + ", isfriend = " + isfriend + "]";
    }
}