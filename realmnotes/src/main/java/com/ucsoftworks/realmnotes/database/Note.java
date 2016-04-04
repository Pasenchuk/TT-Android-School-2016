package com.ucsoftworks.realmnotes.database;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by pasencukviktor on 05/04/16
 */
@RealmClass //optional, just for compiler time checks
public class Note extends RealmObject {
    //must be private
    private String note;
    private Date date;

    //standard getters/setters

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
