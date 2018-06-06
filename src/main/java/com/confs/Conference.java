package com.confs;

public class Conference {
    private int id;
    private String name;
    private String desc;
    private String date;    //YYYY-MM-DD
    private String time;    //default: HH:MM:SS, HH:MM -> HOUR_MINUTE
    private int owner;

    public Conference() {
    }

    public Conference(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public Conference(String name, String desc, String date, String time, int owner) {
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.time = time;
        this.owner = owner;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getOwner() {
        return owner;
    }
}
