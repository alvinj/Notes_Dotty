package com.alvinalexander.notes;

import java.util.Calendar;
import java.util.Date;
import com.alvinalexander.notes.utils.DateUtils;

/**
 * JavaFX ObservableList requires this to be a *property*,
 * i.e., to follow the JavaBean standard
 */
public class Note {

    private String note;
    private String noteShortened;
    private String url;
    private String tags;
    private Date dateCreated;
    private Date dateUpdated;

    /**
     * Use this constructor when first creating a Note. The two dates
     * default to “now.”
     */
    public Note(String note, String url, String tags) {
        this.note = note;
        this.noteShortened = note;
        this.url = url;
        this.tags = tags;
        Date d = Calendar.getInstance().getTime();
        this.dateCreated = d;
        this.dateUpdated = d;
    }

    /**
     * Use this constructor when updating a Note.
     */
    public Note(String note, String url, String tags, String dateCreatedString, String dateUpdatedString) {
        this.note = note;
        this.noteShortened = note;
        this.url = url;
        this.tags = tags;
        this.dateCreated = DateUtils.convertStringToDate(dateCreatedString);
        this.dateUpdated = DateUtils.convertStringToDate(dateUpdatedString);
    }

    public Note(String note, String url, String tags, Date dateCreated, Date dateUpdated) {
        this.note = note;
        this.noteShortened = note;
        this.url = url;
        this.tags = tags;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    /**
     * Use this constructor when intentionally populating`noteShortened`.
     */
    public Note(String note, String noteShortened, String url, String tags, Date dateCreated, Date dateUpdated) {
        this.note = note;
        this.noteShortened = noteShortened;
        this.url = url;
        this.tags = tags;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public String toString() {
        return note + "|" + url + "|" + tags + "|" + DateUtils.convertDateToString(dateCreated) + "|" + DateUtils.convertDateToString(dateUpdated);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getNoteShortened() {
        return noteShortened;
    }

    public void setNoteShortened(String noteShortened) {
        this.noteShortened = noteShortened;
    }

}
