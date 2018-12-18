package com.example.user.atm;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private String userid;
    private String passwd;
    private EditText edUserid;
    private EditText edPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUserid = findViewById(R.id.ed_userid);
        edPasswd = findViewById(R.id.ed_password);
        String userId = getSharedPreferences("ATM",MODE_PRIVATE).getString("USERID","");
        edUserid.setText(userId);
        MyDBHelper helper = new MyDBHelper(this,"expense.db",null,1);
        ContentValues values = new ContentValues();
        values.put("cdate","2018-12-18");
        values.put("info","breakfast");
        values.put("amount",60);
        helper.getWritableDatabase().insert("exp",null,values);
    }

    public void login(View view) {
        userid = edUserid.getText().toString();
        passwd = edPasswd.getText().toString();
        if ("jack".equals(userid) && "1234".equals(passwd)) {
            getSharedPreferences("ATM",MODE_PRIVATE)
                    .edit()
                    .putString("USERID", userid)
                    .apply();
            setResult(RESULT_OK);
            finish();
        }
    }
}
