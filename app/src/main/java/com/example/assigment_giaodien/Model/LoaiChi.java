package com.example.assigment_giaodien.Model;

public class LoaiChi {
    private String id;
    private String name;

    public static final String TABLE_LOAICHI = "Loaichi";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";

    public static final String CREATE_TABLE_LOAICHI = " CREATE TABLE " + TABLE_LOAICHI + " ( " + COLUMN_ID +
            " INTEGER PRIMARY KEY, " + COLUMN_NAME + " VARCHAR) ";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
