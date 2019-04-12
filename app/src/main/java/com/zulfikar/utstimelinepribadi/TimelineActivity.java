package com.zulfikar.utstimelinepribadi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zulfikar.utstimelinepribadi.Adapter.NoteAdapter;
import com.zulfikar.utstimelinepribadi.Database.DBHelper;
import com.zulfikar.utstimelinepribadi.Database.Sessions;
import com.zulfikar.utstimelinepribadi.Model.Note;

import java.util.ArrayList;

public class TimelineActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NoteAdapter adapter;
    ArrayList<Note> note;
    DBHelper db;
    Cursor cursor;
    TextView tvNote;
    Button btnTambah,btnHapus,btnLogout;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        db = new DBHelper(this);

        SharedPreferences spPengguna = TimelineActivity.this.getSharedPreferences("Userlogin", Context.MODE_PRIVATE);

        TextView rvNote = (TextView) findViewById(R.id.rvNote);

        recyclerView = (RecyclerView) findViewById(R.id.rvNote);
        tvNote = (TextView) findViewById(R.id.tvNote);
        btnTambah = (Button) findViewById(R.id.btnTambah);
        btnHapus= (Button) findViewById(R.id.btnHapus);
        btnLogout = findViewById(R.id.btnLogout);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimelineActivity.this, AddTimelineActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase dbsql = db.getReadableDatabase();
                String sql = "delete from note";
                dbsql.execSQL(sql);
                addDataDummy();
            }
        });

        addDataDummy();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimelineActivity.this, MainActivity.class);
                SharedPreferences preferences = getSharedPreferences(Sessions.SP_Tiens_APP,MODE_PRIVATE);
                preferences.edit().putBoolean(Sessions.SP_Has_Login,false).apply();
//                MainActivity.intent.removeFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        addDataDummy();
    }

    private void addDataDummy() {
        note = new ArrayList<>();
        SQLiteDatabase dbsql = db.getReadableDatabase();
        cursor = dbsql.rawQuery("SELECT * FROM note",null);
        cursor.moveToFirst();

        if(cursor.getCount() > 0){
            do {

                note.add(new Note(cursor.getString(cursor.getColumnIndex("judul")),cursor.getString(cursor.getColumnIndex("deskripsi")),cursor.getString(cursor.getColumnIndex("date"))));

            } while (cursor.moveToNext());
        }

        adapter = new NoteAdapter(note);

        layoutManager = new LinearLayoutManager(TimelineActivity.this);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(layoutManager);

        if(note.size() > 0){
            this.tvNote.setText("Total Timeline : " + note.size());
        }else{
            adapter.notifyDataSetChanged();
            this.tvNote.setText("Nothing timeline. : ");
        }

    }
}
