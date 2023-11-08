package com.example.proyectoud1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
@Entity
public class Planetas implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String date;
    private String explanation;
    private String hdurl;
    private String title;
    private String copyright;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Planetas{" +
                "date=" + date +
                ", explanation='" + explanation + '\'' +
                ", hdurl='" + hdurl + '\'' +
                ", title='" + title + '\'' +
                ", title='" + copyright + '\'' +
                '}';
    }


}
