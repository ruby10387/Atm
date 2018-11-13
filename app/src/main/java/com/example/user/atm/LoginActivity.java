package com.example.user.atm;

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
