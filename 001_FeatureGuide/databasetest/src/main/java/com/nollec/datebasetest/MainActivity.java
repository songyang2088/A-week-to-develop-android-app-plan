package com.nollec.datebasetest;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.sql.SQLData;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabaseHelper = new MyDatabaseHelper(this, "BookStore.db", null, 3);
        db= myDatabaseHelper.getReadableDatabase();


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.creat_book:
                db=myDatabaseHelper.getReadableDatabase();
            case R.id.add_data:
                ContentValues values=new ContentValues();
                values.put("author","Tom");
                values.put("price", 16.96);
                values.put("pages", 454);
                values.put("name", "The Da Vinci Code");

                db.insert("Book", null, values);//插入第一条数据
                values.clear();
                values.put("author", "巫妖王");
                values.put("price",100);
                values.put("pages",600);
                values.put("name", "魔兽世界");
                db.insert("Book", null, values);//插入第二条数据
                Toast.makeText(this, "添加数据", Toast.LENGTH_SHORT).show();
            case R.id.update_data:
                ContentValues values1=new ContentValues();
                values1.put("price", 50);

                db.update("Book", values1, "name=?", new String[]{"魔兽世界"});
                Toast.makeText(this, "更新魔兽世界的价格", Toast.LENGTH_SHORT).show();
            case R.id.delete_data:
                db.delete("Book","author=?",new String[]{"Tom"});
                Toast.makeText(this, "下架Tom的书", Toast.LENGTH_SHORT).show();




        }

    }

}
