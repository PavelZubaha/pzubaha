package com.pzubaha.jsop.models;

import java.sql.Timestamp;

public class Vacancy {
    private int id;
    private String header;
    private String desc;
    private Timestamp updateTime;
    private String url;

    public Vacancy(String header, String desc, Timestamp updateTime, String url) {
        this.header = header;
        this.desc = desc;
        this.updateTime = updateTime;
        this.url = url;
    }

    @Override
    public String toString() {
        return String.format("Vacancy %n{header=%s%ndesc=%s%nupdateTime=%s%nurl=%s}", header, desc, updateTime, url);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
