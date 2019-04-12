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

public class AddTimelineActivity extends AppCompatActivity {
    EditText tJudul,tDeskripsi,tDate;
    Button btnAdd;
    DBHelper db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timeline);

        db = new DBHelper(this);

        tJudul = (EditText) findViewById(R.id.tAddJudul);
        tDeskripsi = (EditText) findViewById(R.id.tAddDesk);
        tDate= (EditText) findViewById(R.id.tAddDate);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase dbnya = db.getWritableDatabase();

//                cursor = dbnya.rawQuery("select * from note",null);
//                int nomor = cursor.getCount();
                dbnya.execSQL("insert into note(judul, deskripsi, date) values('" + tJudul.getText().toString() + "','" + tDeskripsi.getText().toString() + "','" + tDate.getText().toString() + "')");

                Toast.makeText(getApplicationContext(), "Add Timeline Successfully", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(AddTimelineActivity.this, TimelineActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
