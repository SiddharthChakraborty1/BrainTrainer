package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GridLayout gridLayout;
    TextView timer,points,equation;
    int pointCounter,questionCounter;
    int globalAns;

    Button b1,b2,b3,b4,bStart,bStartAgain,bSubmit;
    Character[] characters = {'+','-','/','*'};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setVisibility(View.INVISIBLE);
        pointCounter = 0;
        questionCounter = 0;
        points = findViewById(R.id.score);
        points.setText("0/0");
        points.setVisibility(View.INVISIBLE);
        equation = findViewById(R.id.equation);
        equation.setVisibility(View.INVISIBLE);
        globalAns = 0;
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        bStart = findViewById(R.id.btnStart);
        timer = findViewById(R.id.timerTextView);
        timer.setVisibility(View.INVISIBLE);
        bStartAgain = findViewById(R.id.btnStartAgain);

        bStartAgain.setVisibility(View.INVISIBLE);



    }

    public void startGame(View view)
    {
        equation.setVisibility(View.VISIBLE);
        points.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        bStart.setVisibility(View.INVISIBLE);

        startTimer();

        generateButton(generateEquation());


    }

    public void startTimer()
    {      points.setText(" ");
            points.setVisibility(View.VISIBLE);

        new CountDownTimer(120000,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
             Long minutes = millisUntilFinished/1000/60;
             Long seconds = millisUntilFinished/1000%60;
             String timeRemaining = ""+ minutes + " : "+ seconds;
             String scores = ""+ pointCounter + "/ " + questionCounter;
             points.setText(scores);

             timer.setText(timeRemaining);
             timer.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFinish() {

                bStartAgain.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"game over",Toast.LENGTH_SHORT).show();

            }
        }.start();
    }

    public int generateEquation()
    {
        Random random = new Random();
        int first = random.nextInt(100);
        int second = random.nextInt(100);
        char operator = characters[random.nextInt(5)];
        int ans = 0;
        switch (operator)
        {
            case '*':
                ans = first*second;
                break;

            case '+':
                ans = first+second;
                break;

            case '-':
                ans = first-second;
                break;

            case '/':
                if(first>=second && second != 0)
                ans = first/second;
                else
                    ans = 0;
                break;

        }
        String question = ""+ first +" "+ operator +" "+ second + " = ?";
        equation.setText(question);
        globalAns = ans;
        return ans;
    }

    public void submit(View view)
    {   int ans = globalAns;
        questionCounter++;
        Button button = (Button) view;
        if(Integer.parseInt(button.getText().toString().trim()) == globalAns)
        {
            pointCounter++;
        }

        ans = generateEquation();
        generateButton(ans);

    }

    public void generateButton(int ans)
    {
        Random rand = new Random();
        int btnNo = rand.nextInt(4);
        switch (btnNo)
        {
            case 0:
                b1.setText(Integer.toString(ans));
                b2.setText(Integer.toString(rand.nextInt(100)));
                b3.setText(Integer.toString(rand.nextInt(100)));
                b4.setText(Integer.toString(rand.nextInt(100)));
                break;

            case 1:
                b2.setText(Integer.toString(ans));
                b1.setText(Integer.toString(rand.nextInt(100)));
                b3.setText(Integer.toString(rand.nextInt(100)));
                b4.setText(Integer.toString(rand.nextInt(100)));
                break;

            case 2:
                b3.setText(Integer.toString(ans));
                b2.setText(Integer.toString(rand.nextInt(100)));
                b1.setText(Integer.toString(rand.nextInt(100)));
                b4.setText(Integer.toString(rand.nextInt(100)));
                break;

            case 3:
                b4.setText(Integer.toString(ans));
                b2.setText(Integer.toString(rand.nextInt(100)));
                b3.setText(Integer.toString(rand.nextInt(100)));
                b1.setText(Integer.toString(rand.nextInt(100)));
                break;
        }
    }


}