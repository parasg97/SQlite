package com.persi.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TextInputLayout;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DataBase_Name= "student.db";
    public static final String DataBase_Table="student_table";
    public static final String COl_1="id";
    public static final String COl_2="name";
    public static final String COl_3="subject";
    public static final String COl_4="marks";




    public DataBaseHelper(Context context) {
        super(context, DataBase_Name, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+DataBase_Table+"(ID Integer Primary Key Autoincrement,Name Text,Subject Text,Marks Integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists "+DataBase_Table);
    }

    public boolean insertData(String name,String subject,int marks){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COl_2,name);
        contentValues.put(COl_3,subject);
        contentValues.put(COl_4,marks);
        long result=db.insert(DataBase_Table,null,contentValues);
        if(result!=-1)
            return true;
        else
            return false;
    }

    public Cursor getALLData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+DataBase_Table,null);
        return res;
    }

    public void updateData(String id,String name,String subject,int marks){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COl_2,name);
        contentValues.put(COl_3,subject);
        contentValues.put(COl_4,marks);
        db.update(DataBase_Table,contentValues,"id=?",new String[]{id});

        return;

    }

    public void deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(DataBase_Table,"id=?",new String[]{id});
    }
}
