package com.pzubaha.jsop.models;

import java.sql.Timestamp;

public class Vacancy {
    private int id;
    private String header;
    private String desc;
    private Timestamp updateTime;

    public Vacancy(int id, String header, String desc, Timestamp updateTime) {
        this.id = id;
        this.header = header;
        this.desc = desc;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return String.format("Vacancy %n{header=%s%ndesc=%s%nupdateTime=%s%nurl=%s}", header, desc, updateTime, "http://www.sql.ru/forum/" + id);
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
}
