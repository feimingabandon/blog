package com.zou.pojo;

public class Tags {

    private int id;  // 标签id
    private String varchar;  // 标签名称

    public Tags() {
    }

    public Tags(int id, String varchar) {
        this.id = id;
        this.varchar = varchar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVarchar() {
        return varchar;
    }

    public void setVarchar(String varchar) {
        this.varchar = varchar;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "id=" + id +
                ", varchar='" + varchar + '\'' +
                '}';
    }
}
