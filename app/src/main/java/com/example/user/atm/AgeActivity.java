package com.example.user.atm;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class AgeActivity extends BaseActivity {
    int [] numbers = {18,19,20,21,22,23,24,25};
    private EditText edAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);
        edAge = findViewById(R.id.ed_age);
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AgeAdapter());
    }
    public void next(View view) {
        int age = Integer.parseInt(edAge.getText().toString());
        getSharedPreferences("user", MODE_PRIVATE)
                .edit()
                .putInt("AGE", age)
                .apply();
//        user.setAge(age);
        Intent gender = new Intent(this, GenderActivity.class);
        startActivity(gender);
    }
    public void back(View view) {
        finish();
    }
    class AgeAdapter extends RecyclerView.Adapter<AgeAdapter.AgeHolder>{
        @NonNull
        @Override
        public AgeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.age_row,parent,false);
            return new AgeHolder(view);
        }

//        匿名類別要存取區域變數需要改成final
        @Override
        public void onBindViewHolder(@NonNull AgeHolder holder, final int position) {
            holder.ageText.setText(numbers[position] + "");
            if(numbers[position] % 2 == 1) {
                holder.ageText.setTextColor(Color.BLUE);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("AgeActivety","onClick :" + numbers[position]);
                    edAge.setText(numbers[position] + "");
                }
            });
        }

        @Override
        public int getItemCount() {
            return numbers.length;
        }

        class AgeHolder extends RecyclerView.ViewHolder{
            TextView ageText;
            public AgeHolder(View itemView) {
                super(itemView);
                ageText = itemView.findViewById(R.id.age);
            }
        }
    }
}
