package com.example.avinashravilla.sharkfeed.model.imagedetails;

public class Dates {
    private String lastupdate;

    private String takenunknown;

    private String taken;

    private String takengranularity;

    private String posted;

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getTakenunknown() {
        return takenunknown;
    }

    public void setTakenunknown(String takenunknown) {
        this.takenunknown = takenunknown;
    }

    public String getTaken() {
        return taken;
    }

    public void setTaken(String taken) {
        this.taken = taken;
    }

    public String getTakengranularity() {
        return takengranularity;
    }

    public void setTakengranularity(String takengranularity) {
        this.takengranularity = takengranularity;
    }

    public String getPosted() {
        return posted;
    }

    public void setPosted(String posted) {
        this.posted = posted;
    }

    @Override
    public String toString() {
        return "ClassPojo [lastupdate = " + lastupdate + ", takenunknown = " + takenunknown + ", taken = " + taken + ", takengranularity = " + takengranularity + ", posted = " + posted + "]";
    }
}
