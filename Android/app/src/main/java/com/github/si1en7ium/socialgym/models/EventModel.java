package com.github.si1en7ium.socialgym.models;

import org.joda.time.DateTime;
import org.joda.time.Interval;

public final class EventModel {
    private String title;

    private String description;

    private UserModel creator;

    private SportKindModel sportKind;

    private DateTime dateTime;

    private Interval interval;

    public EventModel(String title, String description, UserModel creator, SportKindModel sportKind, DateTime dateTime, Interval interval) {
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.sportKind = sportKind;
        this.dateTime = dateTime;
        this.interval = interval;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserModel getCreator() {
        return creator;
    }

    public void setCreator(UserModel creator) {
        this.creator = creator;
    }

    public SportKindModel getSportKind() {
        return sportKind;
    }

    public void setSportKind(SportKindModel sportKind) {
        this.sportKind = sportKind;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Interval getInterval() {
        return interval;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }
}
