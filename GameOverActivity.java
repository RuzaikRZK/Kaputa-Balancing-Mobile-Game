package com.example.thilina.k_p_balance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    private Button startGameAgain;
    //private TextView disScore;
   // private String score2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        //score2 = getIntent().getExtras().get("Score1").toString();


        startGameAgain = (Button) findViewById(R.id.play_again_btn);
        //disScore =(TextView) findViewById(R.id.displayScore);
        //disScore.setText("Score : "+score2);
        startGameAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(GameOverActivity.this,MainActivity.class);
                startActivity(mainIntent);
            }
        });


    }
}
