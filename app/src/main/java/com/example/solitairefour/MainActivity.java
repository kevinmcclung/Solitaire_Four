package com.example.solitairefour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int countdownToTie = 16;
    int[] gameState = new int[16];
    //0 unoccupied, 1 +, 2 -

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Random random = new Random();
        double num = random.nextDouble();
        //To avoid throwing an Exception, do not use this method for any non-ImageButton views.
        try {
            ImageButton imageButton = (ImageButton) view;
            Drawable drawable;
            if(num < 0.5) {
                try {
                    gameState[Integer.parseInt(view.getTag().toString())] = 1;
                    imageButton.setImageResource(R.drawable.plus);
                    countdownToTie--;
                } catch(NumberFormatException e) {
                    Log.e("Error", e.getMessage());
                }
                if(isGameOver()) {
                    Toast.makeText(this, "YOU WIN", Toast.LENGTH_LONG).show();
                    endGame();
                } else if(countdownToTie == 0) {
                    Toast.makeText(this, "YOU TIE", Toast.LENGTH_LONG).show();
                    endGame();
                }
            } else {
                try {
                    gameState[Integer.parseInt(view.getTag().toString())] = 2;
                    imageButton.setImageResource(R.drawable.minus);
                    countdownToTie--;
                } catch(NumberFormatException e) {
                    Log.e("Error", e.getMessage());
                }
                if(isGameOver()) {
                    Toast.makeText(this, "YOU LOSE", Toast.LENGTH_LONG).show();
                    endGame();
                } else if(countdownToTie == 0) {
                    Toast.makeText(this, "YOU TIE", Toast.LENGTH_LONG).show();
                    endGame();
                }
            }
            view.setClickable(false);
            Log.i("id", view.getTag().toString());
            Log.i("spaces left", Integer.valueOf(countdownToTie).toString());
            if(isGameOver()) {
                endGame();
            }
        } catch(Exception e) {
            Log.e("Error", "View is not ImageButton");
        }
    }

    public boolean isGameOver() {
        /*game-ending combinations are 0/1/2/3, 4/5/6/7, 8/9/10/11, 12/13/14/15,
         *0/4/8/12, 1/5/9/13, 2/6/10/14, 3/7/11/15,
         * 0/5/10/15, 3/6/9/12
         */
        if((gameState[0] == gameState[1] && gameState[1] == gameState[2] && gameState[2] == gameState[3] && gameState[3] != 0)
                || (gameState[4] == gameState[5] && gameState[5] == gameState[6] && gameState[6] == gameState[7] &&  gameState[7] != 0)
                || (gameState[8] == gameState[9] && gameState[9] == gameState[10] && gameState[10] == gameState[11] &&  gameState[11] != 0)
                || (gameState[12] == gameState[13] && gameState[13] == gameState[14] && gameState[14] == gameState[15] &&  gameState[15] != 0)
                || (gameState[0] == gameState[4] && gameState[4] == gameState[8] && gameState[8] == gameState[12] &&  gameState[12] != 0)
                || (gameState[1] == gameState[5] && gameState[5] == gameState[9] && gameState[9] == gameState[13] &&  gameState[13] != 0)
                || (gameState[2] == gameState[6] && gameState[6] == gameState[10] && gameState[10] == gameState[14] &&  gameState[14] != 0)
                || (gameState[3] == gameState[7] && gameState[7] == gameState[11] && gameState[11] == gameState[15] &&  gameState[15] != 0)
                || (gameState[0] == gameState[5] && gameState[5] == gameState[10] && gameState[10] == gameState[15] &&  gameState[15] != 0)
                || (gameState[3] == gameState[6] && gameState[6] == gameState[9] && gameState[9] == gameState[12] &&  gameState[12] != 0)
        ) {

            return true;
        }
        return false;
    }

    public void endGame() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

}