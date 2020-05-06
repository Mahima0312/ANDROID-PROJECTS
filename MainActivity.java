package com.example.tictacto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningpos= {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}

    };

    //0 is yellow & 1 is red
    int activeplayer=0;
    boolean gameactive=true;
    public void dropIn(View view)
    {
        ImageView counter= (ImageView)view;
        int tappedCounter= Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 &&gameactive) {
            gameState[tappedCounter] = activeplayer;


            counter.setTranslationY(-2000);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);

                activeplayer = 1;

            } else {
                counter.setImageResource(R.drawable.red);


                activeplayer = 0;


            }
            counter.animate().translationYBy(2000).rotationBy(3600).setDuration(300);
            for (int[] winningposition : winningpos) {
                if (gameState[winningposition[0]] == gameState[winningposition[1]] && gameState[winningposition[1]] == gameState[winningposition[2]] && gameState[winningposition[0]] != 2) {
                    String message = "";
                    gameactive=false;
                    if (activeplayer == 1)
                        message = "Yellow";
                    else
                        message = "Red";

                    Toast.makeText(this, message + " has Won! ", Toast.LENGTH_LONG).show();

                    Button playagain= (Button)findViewById(R.id.button);
                    TextView winner= (TextView)findViewById(R.id.textView);
                    winner.setText(message+" has won!");
                    playagain.setVisibility(View.VISIBLE);
                    winner.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    public void playAgain(View view)
    {
        Button playagain= (Button)findViewById(R.id.button);
        TextView winner= (TextView)findViewById(R.id.textView);
        playagain.setVisibility(View.INVISIBLE);
        winner.setVisibility(View.INVISIBLE);
        GridLayout gridlayout= (GridLayout)findViewById(R.id.grid);
        for(int i=0; i<gridlayout.getChildCount();i++)
        {
            ImageView counter= (ImageView)gridlayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }



        activeplayer=0;
        gameactive=true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
