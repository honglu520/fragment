package com.example.fragment.SqList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fragment.R;

public class SQListActivity extends AppCompatActivity {
    private String TAG ="SQListActivity";
    private Button button,button1,button2,button3,button4;
    private MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlist);
        //构建一个MydatabaseHelper对象，指明数据库为BookStore，版本号为1
        myDatabaseHelper=new MyDatabaseHelper(this,"BookStore.db",null,3);
        onclick();
    }

    private void onclick() {
        button=findViewById(R.id.BOOK);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建数据库
                myDatabaseHelper.getWritableDatabase();
            }
        });
        button1=findViewById(R.id.insert);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //添加数据
                SQLiteDatabase db=myDatabaseHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                //插入第一条数据
                values.put("name","android");
                values.put("author","hl");
                values.put("price","999.99");
                db.insert("Book",null,values);
                values.clear();
                //添加第二条数据
                values.put("name","kotlin");
                values.put("author","honglu");
                values.put("price","100");
                db.insert("Book",null,values);
                Toast.makeText(SQListActivity.this,"数据插入成功",Toast.LENGTH_SHORT).show();

            }
        });
        button2=findViewById(R.id.delete);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //删除数据
            SQLiteDatabase db=myDatabaseHelper.getWritableDatabase();
            db.delete("Book","pages > ?",new String[] { "500"});
            Toast.makeText(SQListActivity.this,"数据删除成功",Toast.LENGTH_SHORT).show();
            }
        });
        button3=findViewById(R.id.update);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //修改数据
                SQLiteDatabase db =myDatabaseHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("price","20");
                db.update("Book",values,"author =?",new String[] {"hl"});
                values.clear();
                values.put("pages","999");
                db.update("Book",values,"name=?",new String[]{"android"});
                Toast.makeText(SQListActivity.this,"数据更新成功",Toast.LENGTH_SHORT).show();
            }
        });
        button4=findViewById(R.id.select);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //查询数据
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d(TAG,"book name is     "+name);
                        Log.d(TAG,"book anthor is   "+author);
                        Log.d(TAG,"book price is"    +price);
                        Log.d(TAG,"book pages is"    +pages);


                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}
