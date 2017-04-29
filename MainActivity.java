package com.example.mihindu.mysqlproject;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity  {

    private EditText txtId;
    private EditText txtFName;
    private EditText txtLName;
    private EditText txtMarks;
    private Button btnAddData;
    private Button btnViewAll;
    private Button btnUpdateData;
    private Button btnDeleteData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Toast.makeText(MainActivity.this, "Frst  " , Toast.LENGTH_LONG);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       final  DatabaseHelper db = new DatabaseHelper(this);
        txtId = (EditText) findViewById(R.id.txtID);
        txtFName = (EditText) findViewById(R.id.txtFName);
        txtLName = (EditText) findViewById(R.id.txtLName);
        txtMarks = (EditText) findViewById(R.id.txtMarks);
        btnAddData = (Button) findViewById(R.id.btnAdd);
        btnViewAll =  (Button) findViewById(R.id.btnViewAll);
        btnUpdateData = (Button) findViewById(R.id.btnUpdateAll);
        btnDeleteData = (Button) findViewById(R.id.btnDelete);

        btnAddData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isInserted = db.insertData (txtFName.getText().toString(), txtLName.getText().toString(), txtMarks.getText().toString());
                if (isInserted ) {
                    Toast.makeText(MainActivity.this, "Data inserted..! " , Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Data NOT inserted..! " , Toast.LENGTH_LONG);
                }
            }
        });

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res =db.getAllData();
                if (res.getCount()== 0){
                    showMessage("Error" , "Nothing to show");

                    return;
                }
                else
                {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("id : " + res.getString(0) + "\n");
                        buffer.append("First Name : " + res.getString(1) + "\n");
                        buffer.append("Last Name: " + res.getString(2) + "\n");
                        buffer.append("Marks : " + res.getString(3) + "\n\n");
                    }
                    showMessage("Data" , buffer.toString());
                }




            }
        });

        btnUpdateData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean isUpdated = db.updateData (txtId.getText().toString(),txtFName.getText().toString(), txtLName.getText().toString(), txtMarks.getText().toString());
                if (isUpdated ) {
                    Toast.makeText(MainActivity.this, "Data inserted..! " , Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Data NOT inserted..! " , Toast.LENGTH_LONG);
                }
            }
        });

        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows = db.deleteData(txtId.getText().toString());
                if (deleteRows >0 ) {
                    Toast.makeText(MainActivity.this, "Data Deleted" , Toast.LENGTH_SHORT);
                }else {
                    Toast.makeText(MainActivity.this, "No data to delete" , Toast.LENGTH_SHORT);
                }

            }
        });

    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
