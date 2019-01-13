package com.example.avinashravilla.sharkfeed.model.imagedetails;

public class Tags {
    private Tag[] tag;

    public Tag[] getTag() {
        return tag;
    }

    public void setTag(Tag[] tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "ClassPojo [tag = " + tag + "]";
    }
}