package com.example.avinashravilla.sharkfeed.model.imagedetails;

public class Photo {
    private Tags tags;

    private String safety_level;

    private Visibility visibility;

    private Urls urls;

    private Editability editability;

    private People people;

    private String dateuploaded;

    private String isfavorite;

    private Publiceditability publiceditability;

    private String id;

    private String farm;

    private Title title;

    private Dates dates;

    private String views;

    private String rotation;

    private Description description;

    private Owner owner;

    private Usage usage;

    private String secret;

    private String server;

    private Notes notes;

    private String license;

    private String media;

    private Comments comments;

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    public String getSafety_level() {
        return safety_level;
    }

    public void setSafety_level(String safety_level) {
        this.safety_level = safety_level;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public Editability getEditability() {
        return editability;
    }

    public void setEditability(Editability editability) {
        this.editability = editability;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public String getDateuploaded() {
        return dateuploaded;
    }

    public void setDateuploaded(String dateuploaded) {
        this.dateuploaded = dateuploaded;
    }

    public String getIsfavorite() {
        return isfavorite;
    }

    public void setIsfavorite(String isfavorite) {
        this.isfavorite = isfavorite;
    }

    public Publiceditability getPubliceditability() {
        return publiceditability;
    }

    public void setPubliceditability(Publiceditability publiceditability) {
        this.publiceditability = publiceditability;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getRotation() {
        return rotation;
    }

    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "ClassPojo [tags = " + tags + ", safety_level = " + safety_level + ", visibility = " + visibility + ", urls = " + urls + ", editability = " + editability + ", people = " + people + ", dateuploaded = " + dateuploaded + ", isfavorite = " + isfavorite + ", publiceditability = " + publiceditability + ", id = " + id + ", farm = " + farm + ", title = " + title + ", dates = " + dates + ", views = " + views + ", rotation = " + rotation + ", description = " + description + ", owner = " + owner + ", usage = " + usage + ", secret = " + secret + ", server = " + server + ", notes = " + notes + ", license = " + license + ", media = " + media + ", comments = " + comments + "]";
    }
}