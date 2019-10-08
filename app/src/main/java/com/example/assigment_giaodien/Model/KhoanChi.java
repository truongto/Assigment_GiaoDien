package com.example.assigment_giaodien.Model;

public class KhoanChi {
    String id;
    String name;
    long money;
    String day;

    public static final String TABLE_KHOANCHI= "Khoanchi";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MONEY = "money";
    public static final String COLUMN_DAY = "day";

    public static final String CREATE_TABLE_KHOANCHI = " CREATE TABLE " + TABLE_KHOANCHI + " ( " + COLUMN_ID +
            " INTEGER PRIMARY KEY, " + COLUMN_NAME + " VARCHAR, " + COLUMN_MONEY + " LONG, " + COLUMN_DAY + " VARCHAR )";

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

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
