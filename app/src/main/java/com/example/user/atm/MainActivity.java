package com.example.user.atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity {
    private static final int RC_LOGIN = 100;
    boolean login = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TextView nickText = findViewById(R.id.ed_nickname);
//        nickText.setText(user.getNickname());
        if (!login) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent,RC_LOGIN);
        }
        List<String> fruits = Arrays.asList("香蕉","鳳梨","芭樂");
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,fruits);
        ListView listview = findViewById(R.id.list);
        listview.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_LOGIN) {
            if(resultCode != RESULT_OK) {
                finish();
            }else{
                login = true;
                String nickname = getSharedPreferences("user", MODE_PRIVATE)
                        .getString("NICKNAME", null);
                int age = getSharedPreferences("user", MODE_PRIVATE)
                        .getInt("AGE", 0);
                int gender = getSharedPreferences("user", MODE_PRIVATE)
                        .getInt("GENDER", 0);
                if(nickname == null || age == 0 || gender == 0) {
                    Intent nick = new Intent(this, NicknameActivity.class);
                    startActivity(nick);
                }
//                if (!user.isValid()){
//                    Intent nick = new Intent(this, NicknameActivity.class);
//                    startActivity(nick);
//                }
            }
        }
    }
}
