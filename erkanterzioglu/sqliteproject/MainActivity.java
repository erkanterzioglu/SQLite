package com.erkanterzioglu.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            SQLiteDatabase database = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY,name VARCHAR,age INT)");
            database.execSQL("INSERT INTO musicians (name,age) VALUES ('James',50)");
            database.execSQL("INSERT INTO musicians (name,age) VALUES ('Lars',90)");
            database.execSQL("INSERT INTO musicians (name,age) VALUES ('Kirk',31)");
            // database.execSQL("DELETE FROM musicians WHERE name LIKE 'K%'",null);
            database.execSQL("UPDATE musicians SET name='Kirk Hammett' WHERE name='Kirk'");

            Cursor cursor = database.rawQuery("SELECT *FROM musicians WHERE name='Kirk Hammett'",null);
            int idIx=cursor.getColumnIndex("id");
            int nameIx=cursor.getColumnIndex("name");
            int ageIx=cursor.getColumnIndex("age");
            while (cursor.moveToNext()){
                System.out.println("Name"+ cursor.getString(nameIx));
                System.out.println("Age" + cursor.getInt(ageIx));
                System.out.println("ID"+cursor.getInt(idIx));



            }

            cursor.close();


        }
        catch(Exception e ){
            e.printStackTrace();
        }



    }
}