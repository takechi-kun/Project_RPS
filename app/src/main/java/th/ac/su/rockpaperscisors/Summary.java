package th.ac.su.rockpaperscisors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import th.ac.su.rockpaperscisors.db.AppDatabase;
import th.ac.su.rockpaperscisors.model.User;
import th.ac.su.rockpaperscisors.util.AppExecutors;

public class Summary extends AppCompatActivity {
    TextView[] textRound = new TextView[5];
    TextView userText;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");

    final String[] nameResult = new String[5];
    String[] rX = {"r1","r2","r3","r4","r5"};
    char[] variable = new char[5];

    textRound[0] = findViewById(R.id.r1_result);
        textRound[1] = findViewById(R.id.r2_result);
        textRound[2] = findViewById(R.id.r3_result);
        textRound[3] = findViewById(R.id.r4_result);
        textRound[4] = findViewById(R.id.r5_result);

        userText = findViewById(R.id.user_text);
        userText.setText("Name : " + username);

        Intent i = getIntent();
        final int score = i.getIntExtra("score",0);
        TextView scoreTextView = findViewById(R.id.score_text_view);
        scoreTextView.setText("Your score is.."+String.valueOf(score));




        for(int j = 0;j<5;j++){
            variable[j] = i.getCharExtra(rX[j],'d');
        }
        for(int j = 0;j<5;j++){
            char c = variable[j];
            int color =Color.BLACK;
            String result=" ";
            if(c=='d'){
                result =  "draw";
                color = Color.BLUE;
            }else if(c=='w'){
                result = "win";
                color = Color.GREEN;
            }else if(c == 'l'){
                result = "lose";
                color = Color.RED;
            }
            nameResult[j] = result;
            textRound[j].setText(result);
            textRound[j].setTextColor(color);
        }





        Button backButton = findViewById(R.id.back_his_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user = new User(0,username,nameResult[0],
                        nameResult[1],nameResult[2],nameResult[3],nameResult[4],score);

                AppExecutors executors = new AppExecutors();
                executors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() { // worker thread
                        AppDatabase db = AppDatabase.getInstance(Summary.this);
                        db.userDao().addUser(user);
                        Intent intent = new Intent(Summary.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });
    }
}