package com.zulfikar.utstimelinepribadi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zulfikar.utstimelinepribadi.Database.DBHelper;
import com.zulfikar.utstimelinepribadi.Database.Sessions;
import com.zulfikar.utstimelinepribadi.Model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<User> pengguna;
    public EditText tfNama,tfPassword;
    public Button btnMasuk;
    public Sessions sessions;
    DBHelper db;

    public static Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessions = new Sessions(this);
        if (sessions.getHasLogin()){
            this.intent = new Intent(MainActivity.this, TimelineActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
            startActivity(intent);
//            startActivity(new Intent(MainActivity.this, TimelineActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
        }

        pengguna = new ArrayList<>();
        pengguna.add(new User("zul", "1234"));

        tfNama = (EditText) findViewById(R.id.userName);
        tfPassword = (EditText) findViewById(R.id.passWord);
        btnMasuk = (Button) findViewById(R.id.btnLogin);

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User tmpPengguna = null;
                for(User u:pengguna){
                    if(u.getNama().equals(tfNama.getText().toString()) && u.getPassword().equals(tfPassword.getText().toString())){
                        tmpPengguna = u;
                    }
                }

                if(tmpPengguna != null){
                    SharedPreferences spPengguna = MainActivity.this.getSharedPreferences("Userlogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = spPengguna.edit();
                    edit.putString("sedangLogin",tmpPengguna.getNama());
                    edit.apply();

                    sessions.saveBoolean(sessions.SP_Has_Login,true);
                    Intent intent = new Intent(MainActivity.this, TimelineActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "Username atau Password Salah", Toast.LENGTH_LONG).show();
                }
            }
        });

        db = new DBHelper(this);

    }
}
