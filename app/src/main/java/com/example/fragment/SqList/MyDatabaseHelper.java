package com.example.fragment.SqList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context mcontext;
    public static  final String CREATE_HONGLU="create table HongLu("
            +"id integer primary key autoincrement,"
            +"name text,"
            +"height real,"
            +"weight real,"
            +"age integer)";

    public static  final String CREATE_BOOK="create table Book("
            +"id integer primary key autoincrement,"
            +"author text,"
            +"price real,"
            +"pages integer,"
            +"name text)";
    public static  final String CREATE_CATEGORY="create table Category("
            +"id integer primary key autoincrement,"
            +"category_name text,"
            +"category_code integer)";
    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext=context;
        Log.d("MyDatabaseHelper",CREATE_BOOK);
    }
    //创建数据库执行
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BOOK);
        Toast.makeText(mcontext,"数据库创建成功",Toast.LENGTH_SHORT).show();
    }
    //跟新数据库执行
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(CREATE_CATEGORY);
        sqLiteDatabase.execSQL(CREATE_HONGLU);
        Toast.makeText(mcontext,"数据库更新成功",Toast.LENGTH_SHORT).show();
    }
}
