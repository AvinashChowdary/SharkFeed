package com.example.avinashravilla.sharkfeed.model.imagedetails;

public class Url {
    private String _content;

    private String type;

    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ClassPojo [_content = " + _content + ", type = " + type + "]";
    }
}