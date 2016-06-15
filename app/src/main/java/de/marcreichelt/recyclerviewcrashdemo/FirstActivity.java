package de.marcreichelt.recyclerviewcrashdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FirstActivity extends AppCompatActivity {

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        final View animate = findViewById(R.id.animate);
        animate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstActivity activity = FirstActivity.this;
                Intent intent = new Intent(activity, SecondActivity.class);
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, animate, "animate").toBundle();
                ActivityCompat.startActivity(activity, intent, bundle);
            }
        });
    }

}
