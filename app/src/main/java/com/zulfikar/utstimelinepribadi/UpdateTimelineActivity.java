package com.zulfikar.utstimelinepribadi;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zulfikar.utstimelinepribadi.Database.DBHelper;

public class UpdateTimelineActivity extends AppCompatActivity {

    EditText tfJudul,tfDeskripsi,tfDate;
    Button btnSimpan;
    DBHelper db;
    Cursor cursor;
    String judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_timeline);

        Intent intent = getIntent();
        judul = intent.getStringExtra("judul");
        String deskripsi = intent.getStringExtra("deskripsi");
        String date = intent.getStringExtra("date");

        tfJudul = (EditText) findViewById(R.id.tUpdateJudul);
        tfDeskripsi = (EditText) findViewById(R.id.tUpdateDesk);
        tfDate= (EditText) findViewById(R.id.tUpdateDate);
        btnSimpan = (Button) findViewById(R.id.btnUpdate);

        tfJudul.setText(judul);
        tfDeskripsi.setText(deskripsi);
        tfDate.setText(date);
        db = new DBHelper(this);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase dbnya = db.getWritableDatabase();
                dbnya.execSQL("update note set judul='"+ tfJudul.getText() +"', deskripsi='"+ tfDeskripsi.getText() +"', date='"+ tfDate.getText() +"' where judul='"+ judul +"'");
                finish();
                Toast.makeText(UpdateTimelineActivity.this,"Update successfully",Toast.LENGTH_LONG).show();
            }
        });
    }
}
