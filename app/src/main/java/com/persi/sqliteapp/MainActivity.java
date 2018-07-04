package com.persi.sqliteapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private  DataBaseHelper db;
    private TextInputEditText mStudentName;
    private TextInputEditText mMarks;
    private TextInputEditText mId;
    private TextInputEditText mSubject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStudentName=(TextInputEditText) findViewById(R.id.Student_Name);
        mMarks=(TextInputEditText)findViewById(R.id.Marks);
        mId=(TextInputEditText)findViewById(R.id.Id);
        mSubject=(TextInputEditText)findViewById(R.id.Subject);
        db=new DataBaseHelper(this);

    }

    public void addToTable(View v) {

        boolean isTrue=db.insertData(mStudentName.getText().toString(),mSubject.getText().toString(),Integer.parseInt(mMarks.getText().toString()));
        if(isTrue)
            Toast.makeText(this,"Entry Added",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Entry Not Added",Toast.LENGTH_SHORT).show();
    }

    public void viewALLData(View v){
        Cursor res=db.getALLData();
        if(res.getCount()==0){
            Log.d("Sql","lol");
            return;
        }
        else{
            StringBuffer sb=new StringBuffer();
            while (res.moveToNext()){
                sb.append("Id:"+res.getString(0)+"\n");
                sb.append("Name:"+res.getString(1)+"\n");
                sb.append("Suject:"+res.getString(2)+"\n");
                sb.append("Marks:"+res.getString(3)+"\n\n");
            }
            showDialouge("Data",sb.toString());
        }

    }

    private void showDialouge(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }

    public void updateTableData(View v){
        db.updateData(mId.getText().toString(),mStudentName.getText().toString(),mSubject.getText().toString(),Integer.parseInt(mMarks.getText().toString()));
        Toast.makeText(this,"Data Updated",Toast.LENGTH_SHORT).show();
    }

    public void deleteTableData(View v){
        db.deleteData(mId.getText().toString());
        Toast.makeText(this,"Data Deleted",Toast.LENGTH_SHORT).show();

    }


}
