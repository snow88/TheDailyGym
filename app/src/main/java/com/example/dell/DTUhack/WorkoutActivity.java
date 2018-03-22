package com.example.dell.DTUhack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class WorkoutActivity extends AppCompatActivity {
    TextView workouttitle, workoutdesc;
    ImageView workoutiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        workouttitle = findViewById(R.id.workouttitle);
        workoutdesc = findViewById(R.id.workoutdesc);
        workoutiv = findViewById(R.id.workoutiv);

        workouttitle.setText(getIntent().getStringExtra("TITLE"));
        workoutdesc.setText(getIntent().getStringExtra("DESC"));
        Picasso.with(this)
                .load(getIntent().getIntExtra("IMG", R.drawable.bg4_b))
                .resize(400, 300)
                .centerCrop()
                .into(workoutiv);

    }
}
