package com.example.avinashravilla.sharkfeed.model.imagedetails;

public class Owner {
    private String username;

    private String location;

    private String iconserver;

    private String realname;

    private String path_alias;

    private String nsid;

    private String iconfarm;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIconserver() {
        return iconserver;
    }

    public void setIconserver(String iconserver) {
        this.iconserver = iconserver;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPath_alias() {
        return path_alias;
    }

    public void setPath_alias(String path_alias) {
        this.path_alias = path_alias;
    }

    public String getNsid() {
        return nsid;
    }

    public void setNsid(String nsid) {
        this.nsid = nsid;
    }

    public String getIconfarm() {
        return iconfarm;
    }

    public void setIconfarm(String iconfarm) {
        this.iconfarm = iconfarm;
    }

    @Override
    public String toString() {
        return "ClassPojo [username = " + username + ", location = " + location + ", iconserver = " + iconserver + ", realname = " + realname + ", path_alias = " + path_alias + ", nsid = " + nsid + ", iconfarm = " + iconfarm + "]";
    }
}