package com.zulfikar.utstimelinepribadi;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zulfikar.utstimelinepribadi.Database.DBHelper;

public class DetailActivity extends AppCompatActivity {
    String id;
    DBHelper db;
    String judul,deskripsi,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        judul = intent.getStringExtra("judul");
        deskripsi = intent.getStringExtra("deskripsi");
        date = intent.getStringExtra("date");

        TextView tfJudul = (TextView) findViewById(R.id.tvJudul);
        TextView tfDesk= (TextView) findViewById(R.id.tvDesk);
        TextView tfDate = (TextView) findViewById(R.id.tvDate);
        Button btnUp = (Button) findViewById(R.id.btnUp);
        Button btnHapus = (Button) findViewById(R.id.btnHapus);

        tfJudul.setText("Judul : " + judul);
        tfDesk.setText("Deskripsi :" +  deskripsi);
        tfDate.setText("Deadine : " + date);

        db = new DBHelper(this);

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = DetailActivity.this;
                Intent i = new Intent(context, UpdateTimelineActivity.class);
                i.putExtra("judul", judul);
                i.putExtra("deskripsi", deskripsi);
                i.putExtra("date", date);
                context.startActivity(i);
                finish();
//                Toast.makeText(detailNote.this, "Id nya : " + id, Toast.LENGTH_SHORT).show();
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase dbsql = db.getReadableDatabase();
                dbsql.delete("note","judul = '" + judul + "'",null);
                Toast.makeText(DetailActivity.this,"Berhasil dihapus!", Toast.LENGTH_LONG).show();
                finish();
            }
        });


    }
}
