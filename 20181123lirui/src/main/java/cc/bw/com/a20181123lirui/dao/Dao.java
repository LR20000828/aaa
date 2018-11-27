package cc.bw.com.a20181123lirui.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cc.bw.com.a20181123lirui.sqlite.Sqlite;

public class Dao {

    private final SQLiteDatabase db;

    public Dao(Context context){
        Sqlite sqlite = new Sqlite(context);
        db = sqlite.getWritableDatabase();
    }

    public long insert(String table, String nullColumnHack, ContentValues values){
        return db.insert(table,nullColumnHack,values);
    }
    public Cursor select(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return db.query(table,columns,selection,selectionArgs,groupBy,having,orderBy);
    }


}
