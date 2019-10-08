package com.example.assigment_giaodien.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import com.example.assigment_giaodien.Model.KhoanChi;
import com.example.assigment_giaodien.Model.KhoanThu;
import com.example.assigment_giaodien.Model.LoaiChi;
import com.example.assigment_giaodien.Model.LoaiThu;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSQL extends SQLiteOpenHelper {
    public DatabaseSQL(Context context) {
        super(context, "Loaithu.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LoaiThu.CREATE_TABLE_LOAITHU);
        db.execSQL(KhoanThu.CREATE_TABLE_KHOANTHU);
        db.execSQL(KhoanChi.CREATE_TABLE_KHOANCHI);
        db.execSQL(LoaiChi.CREATE_TABLE_LOAICHI);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + LoaiThu.TABLE_LOAITHU);
        db.execSQL(" DROP TABLE IF EXISTS " + KhoanThu.TABLE_KHOANTHU);
        db.execSQL(" DROP TABLE IF EXISTS " + KhoanChi.TABLE_KHOANCHI);
        db.execSQL(" DROP TABLE IF EXISTS " + LoaiChi.TABLE_LOAICHI);
        onCreate(db);
    }

    public long inserLoaithu(LoaiThu loaiThu) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LoaiThu.COLUMN_ID, loaiThu.getId());
        contentValues.put(LoaiThu.COLUMN_NAME, loaiThu.getName());
        long result = sqLiteDatabase.insert(LoaiThu.TABLE_LOAITHU, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public List<LoaiThu> getLoaithu() {
        List<LoaiThu> loaiThuList = new ArrayList<>();
        String SELECT = " SELECT * FROM " + LoaiThu.TABLE_LOAITHU;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
//                int id = cursor.getInt(cursor.getColumnIndex(LoaiThu.COLUMN_ID));
                String id = cursor.getString(cursor.getColumnIndex(LoaiThu.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(LoaiThu.COLUMN_NAME));
                LoaiThu loaiThu = new LoaiThu();
                loaiThu.setId(id);
                loaiThu.setName(name);
                loaiThuList.add(loaiThu);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return loaiThuList;
    }

    public void deleteLoaithu(String idLoaithu) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LoaiThu.TABLE_LOAITHU, LoaiThu.COLUMN_ID + "=?", new String[]{String.valueOf(idLoaithu)});
        db.close();
    }

    public int updateLoaithu(LoaiThu loaiThu) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LoaiThu.COLUMN_NAME, loaiThu.getId());
        return db.update(LoaiThu.TABLE_LOAITHU, values, LoaiThu.COLUMN_ID + "=?", new String[]{String.valueOf(loaiThu)});
//        long result = db.update(LoaiThu.TABLE_LOAITHU, values, LoaiThu.COLUMN_ID + "=?", new String[]{loaiThu.getId()});
//        return result;
    }


    public long inserKhoanthu(KhoanThu khoanThu) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KhoanThu.COLUMN_ID, khoanThu.getId());
        contentValues.put(KhoanThu.COLUMN_NAME, khoanThu.getName());
        contentValues.put(KhoanThu.COLUMN_MONEY, khoanThu.getMoney());
        contentValues.put(KhoanThu.COLUMN_DAY, khoanThu.getDay());
        long result = sqLiteDatabase.insert(KhoanThu.TABLE_KHOANTHU, null, contentValues);
        sqLiteDatabase.close();
        return result;

    }

    public List<KhoanThu> getKhoanthu() {
        List<KhoanThu> khoanThuList = new ArrayList<>();
        String SELECT = " SELECT * FROM " + KhoanThu.TABLE_KHOANTHU;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String id = cursor.getString(cursor.getColumnIndex(KhoanThu.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(KhoanThu.COLUMN_NAME));
                long tien = cursor.getLong(cursor.getColumnIndex(KhoanThu.COLUMN_MONEY));
                String ngay = cursor.getString(cursor.getColumnIndex(KhoanThu.COLUMN_DAY));
                KhoanThu khoanThu = new KhoanThu();
                khoanThu.setId(id);
                khoanThu.setName(name);
                khoanThu.setMoney(tien);
                khoanThu.setDay(ngay);
                khoanThuList.add(khoanThu);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return khoanThuList;
    }

    public void deleteKhoanthu(String idKhoanthu) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(KhoanThu.TABLE_KHOANTHU, KhoanThu.COLUMN_ID + "=?", new String[]{String.valueOf(idKhoanthu)});
        db.close();
    }

    public int updateKhoanthu(KhoanThu khoanThu) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KhoanThu.COLUMN_NAME, khoanThu.getName());
        values.put(KhoanThu.COLUMN_MONEY, khoanThu.getMoney());
        values.put(KhoanThu.COLUMN_DAY, khoanThu.getDay());
        return db.update(KhoanThu.TABLE_KHOANTHU, values, KhoanThu.COLUMN_ID + " = ? ", new String[]{String.valueOf(khoanThu)});

    }


    public long inserKhoanchi(KhoanChi khoanChi) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KhoanChi.COLUMN_ID, khoanChi.getId());
        contentValues.put(KhoanChi.COLUMN_NAME, khoanChi.getName());
        contentValues.put(KhoanChi.COLUMN_MONEY, khoanChi.getMoney());
        contentValues.put(KhoanChi.COLUMN_DAY, khoanChi.getDay());
        long result = sqLiteDatabase.insert(KhoanChi.TABLE_KHOANCHI, null, contentValues);
        sqLiteDatabase.close();
        return result;

    }

    public List<KhoanChi> getKhoanchi() {
        List<KhoanChi> khoanChiList = new ArrayList<>();
        String SELECT = " SELECT * FROM " + KhoanChi.TABLE_KHOANCHI;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String id = cursor.getString(cursor.getColumnIndex(KhoanChi.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(KhoanChi.COLUMN_NAME));
                long tien = cursor.getLong(cursor.getColumnIndex(KhoanChi.COLUMN_MONEY));
                String ngay = cursor.getString(cursor.getColumnIndex(KhoanChi.COLUMN_DAY));
                KhoanChi khoanChi = new KhoanChi();
                khoanChi.setId(id);
                khoanChi.setName(name);
                khoanChi.setMoney(tien);
                khoanChi.setDay(ngay);
                khoanChiList.add(khoanChi);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return khoanChiList;
    }

    public void deleteKhoanchi(String idKhoanchi) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(KhoanChi.TABLE_KHOANCHI, KhoanChi.COLUMN_ID + "=?", new String[]{String.valueOf(idKhoanchi)});
        db.close();
    }

    public int updateKhoanchi(KhoanChi khoanChi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KhoanChi.COLUMN_NAME, khoanChi.getName());
        values.put(KhoanChi.COLUMN_MONEY, khoanChi.getMoney());
        values.put(KhoanChi.COLUMN_DAY, khoanChi.getDay());
        return db.update(KhoanChi.TABLE_KHOANCHI, values, khoanChi.COLUMN_ID + " = ? ", new String[]{String.valueOf(khoanChi)});

    }

    public long inserLoaichi(LoaiChi loaiChi) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LoaiChi.COLUMN_ID, loaiChi.getId());
        contentValues.put(LoaiChi.COLUMN_NAME, loaiChi.getName());
        long result = sqLiteDatabase.insert(LoaiChi.TABLE_LOAICHI, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public List<LoaiChi> getLoaichi() {
        List<LoaiChi> loaiChiList = new ArrayList<>();
        String SELECT = " SELECT * FROM " + LoaiChi.TABLE_LOAICHI;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
//                int id = cursor.getInt(cursor.getColumnIndex(LoaiThu.COLUMN_ID));
                String id = cursor.getString(cursor.getColumnIndex(LoaiChi.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(LoaiChi.COLUMN_NAME));
                LoaiChi loaiChi = new LoaiChi();
                loaiChi.setId(id);
                loaiChi.setName(name);
                loaiChiList.add(loaiChi);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return loaiChiList;
    }

    public void deleteLoaichi(String idLoaichi) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LoaiChi.TABLE_LOAICHI, LoaiChi.COLUMN_ID + "=?", new String[]{String.valueOf(idLoaichi)});
        db.close();
    }

    public int updateLoaichi(LoaiChi loaiChi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LoaiChi.COLUMN_NAME, loaiChi.getName());
        return db.update(LoaiChi.TABLE_LOAICHI, values, LoaiChi.COLUMN_ID + "=?", new String[]{String.valueOf(loaiChi)});
//        long result = db.update(LoaiThu.TABLE_LOAITHU, values, LoaiThu.COLUMN_ID + "=?", new String[]{loaiThu.getId()});
//        return result;
    }


    public List<KhoanThu> getthongkeKhoanThu() {
        List<KhoanThu> khoanThuList = new ArrayList<>();
        String SELECT = " SELECT * FROM " + KhoanThu.TABLE_KHOANTHU;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                long gia = cursor.getLong(cursor.getColumnIndex(KhoanThu.COLUMN_MONEY));
                KhoanThu khoanThu = new KhoanThu();
                khoanThu.setMoney(gia);
                khoanThuList.add(khoanThu);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return khoanThuList;
    }

    public List<KhoanChi> getthongkeKhoanchi() {
        List<KhoanChi> khoanChiList = new ArrayList<>();
        String SELECT = " SELECT * FROM " + KhoanChi.TABLE_KHOANCHI;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
               long gia = cursor.getLong(cursor.getColumnIndex(KhoanChi.COLUMN_MONEY));
                KhoanChi khoanChi = new KhoanChi();
                khoanChi.setMoney(gia);
                khoanChiList.add(khoanChi);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return khoanChiList;
    }



}
