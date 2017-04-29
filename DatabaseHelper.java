package com.example.mihindu.mysqlproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mihindu on 04/20/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASENAME ="Student2.db";
    public static final String TABLENAME ="student_table3";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "LASTNAME";
    public static final String COL_4 = "MARKS";


    public DatabaseHelper(Context context) {
        super(context,DATABASENAME, null ,1  );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLENAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT , LASTNAME TEXT , MARKS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME  );
        onCreate(db);
    }

    public boolean insertData (String fName, String lName , String marks) {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2 , fName);
        contentValues.put(COL_3 , lName);
        contentValues.put(COL_4 , marks);
        long result =db.insert(TABLENAME , null,contentValues );
        if (result == -1) {
            return false;
        } else {
            return true;
        }


    }

    public Cursor getAllData() {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + TABLENAME , null);
        return res;
    }

    public boolean updateData(String id, String fName , String lName , String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1 , id);
        contentValues.put(COL_2 , fName);
        contentValues.put(COL_3 , lName);
        contentValues.put(COL_4 , marks);
        db.update(TABLENAME,contentValues, "ID = ?" , new String [] {id});
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLENAME,"ID= ?" ,  new String [] {id});
    }
}
