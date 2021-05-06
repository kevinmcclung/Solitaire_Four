package com.sonnysappcafe.solitairefour;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int countdownToTie = 16;
    int[] gameState = new int[16];
    //0 unoccupied, 1 +, 2 -

    //I cannot figure a way to reset all ImageButton views at once, so I am taking a more brute force approach for now.
    ImageButton a1;
    ImageButton a2;
    ImageButton a3;
    ImageButton a4;
    ImageButton b1;
    ImageButton b2;
    ImageButton b3;
    ImageButton b4;
    ImageButton c1;
    ImageButton c2;
    ImageButton c3;
    ImageButton c4;
    ImageButton d1;
    ImageButton d2;
    ImageButton d3;
    ImageButton d4;
    ImageButton[] imageButtons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        a4 = findViewById(R.id.a4);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        c4 = findViewById(R.id.c4);
        d1 = findViewById(R.id.d1);
        d2 = findViewById(R.id.d2);
        d3 = findViewById(R.id.d3);
        d4 = findViewById(R.id.d4);
        imageButtons = new ImageButton[] {a1, a2, a3, a4, b1, b2, b3, b4, c1, c2, c3, c4, d1, d2, d3, d4};
    }

    public void onClick(View view) {
        Random random = new Random();
        double num = random.nextDouble();
        view.setClickable(false);
        //To avoid throwing an Exception, do not use this method for any non-ImageButton views.
        try {
            ImageButton imageButton = (ImageButton) view;
            if(num < 0.5) {
                try {
                    gameState[Integer.parseInt(view.getTag().toString())] = 1;
                    imageButton.setImageResource(R.drawable.plus);
                    countdownToTie--;
                } catch(NumberFormatException e) {
                    Log.e("Error", e.getMessage());
                }
                if(isGameOver()) {
                    Toast.makeText(this, getString(R.string.win), Toast.LENGTH_LONG).show();
                    endGame();
                } else if(countdownToTie == 0) {
                    Toast.makeText(this, getString(R.string.tie), Toast.LENGTH_LONG).show();
                    endGame();
                }
            } else {
                try {
                    gameState[Integer.parseInt(view.getTag().toString())] = 2;
                    imageButton.setImageResource(R.drawable.minus);
                    countdownToTie--;
                } catch(NumberFormatException e) {
                    Log.e(getString(R.string.error), e.getMessage());
                }
                if(isGameOver()) {
                    Toast.makeText(this, getString(R.string.lose), Toast.LENGTH_LONG).show();
                    endGame();
                } else if(countdownToTie == 0) {
                    Toast.makeText(this, getString(R.string.tie), Toast.LENGTH_LONG).show();
                    endGame();
                }
            }
        } catch(Exception e) {
            Log.e(getString(R.string.error), getString(R.string.not_image_button));
        }
    }

    public boolean isGameOver() {
        /*game-ending combinations are 0/1/2/3, 4/5/6/7, 8/9/10/11, 12/13/14/15,
         *0/4/8/12, 1/5/9/13, 2/6/10/14, 3/7/11/15,
         * 0/5/10/15, 3/6/9/12
         */
        return (gameState[0] == gameState[1] && gameState[1] == gameState[2] && gameState[2] == gameState[3] && gameState[3] != 0)
                || (gameState[4] == gameState[5] && gameState[5] == gameState[6] && gameState[6] == gameState[7] && gameState[7] != 0)
                || (gameState[8] == gameState[9] && gameState[9] == gameState[10] && gameState[10] == gameState[11] && gameState[11] != 0)
                || (gameState[12] == gameState[13] && gameState[13] == gameState[14] && gameState[14] == gameState[15] && gameState[15] != 0)
                || (gameState[0] == gameState[4] && gameState[4] == gameState[8] && gameState[8] == gameState[12] && gameState[12] != 0)
                || (gameState[1] == gameState[5] && gameState[5] == gameState[9] && gameState[9] == gameState[13] && gameState[13] != 0)
                || (gameState[2] == gameState[6] && gameState[6] == gameState[10] && gameState[10] == gameState[14] && gameState[14] != 0)
                || (gameState[3] == gameState[7] && gameState[7] == gameState[11] && gameState[11] == gameState[15] && gameState[15] != 0)
                || (gameState[0] == gameState[5] && gameState[5] == gameState[10] && gameState[10] == gameState[15] && gameState[15] != 0)
                || (gameState[3] == gameState[6] && gameState[6] == gameState[9] && gameState[9] == gameState[12] && gameState[12] != 0);
    }

    public void endGame() {
        TextView textView = findViewById(R.id.textView);
        textView.setText(getString(R.string.replay));
        for(ImageButton imageButton: imageButtons) {
            imageButton.setClickable(false);
        }
        textView.setClickable(true);
    }

    public void restartGame(View view) {
        TextView textView = (TextView) view;

        gameState = new int[16];
        countdownToTie = 16;

        textView.setClickable(false);
        textView.setText(R.string.instructions);
        for(ImageButton imageButton: imageButtons) {
            imageButton.setImageResource(R.drawable.question);
            imageButton.setClickable(true);
        }
    }

}