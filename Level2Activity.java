package com.example.thilina.k_p_balance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Level2Activity extends AppCompatActivity {

    private Button startGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);

        startGame = (Button) findViewById(R.id.play_btn);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(Level2Activity.this,MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}
