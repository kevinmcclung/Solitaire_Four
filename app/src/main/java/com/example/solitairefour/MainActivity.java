package com.example.solitairefour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String[] gameState = new String[16];

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
                imageButton.setImageResource(R.drawable.plus);
            } else {
                imageButton.setImageResource(R.drawable.minus);
            }
            view.setClickable(false);
            Log.i("id", view.getTag().toString());
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
        return false;
    }

    public void endGame() {}

}