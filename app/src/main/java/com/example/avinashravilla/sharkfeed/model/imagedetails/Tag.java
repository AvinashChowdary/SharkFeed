package com.example.avinashravilla.sharkfeed.model.imagedetails;

public class Tag {
    private String raw;

    private String id;

    private String author;

    private String machine_tag;

    private String _content;

    private String authorname;

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMachine_tag() {
        return machine_tag;
    }

    public void setMachine_tag(String machine_tag) {
        this.machine_tag = machine_tag;
    }

    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    @Override
    public String toString() {
        return "ClassPojo [raw = " + raw + ", id = " + id + ", author = " + author + ", machine_tag = " + machine_tag + ", _content = " + _content + ", authorname = " + authorname + "]";
    }
}
