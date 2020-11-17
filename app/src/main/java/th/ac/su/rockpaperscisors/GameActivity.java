package th.ac.su.rockpaperscisors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity  implements View.OnClickListener {
    private int round = 1, score = 0;
    private Button[] mButtons = new Button[3];
    private TextView roundText, challengeText;
    private int answer[] = new int[3];
    private String answerStr[] = {"rock", "paper", "scisors"};
    private String challengeStr[] = {"You turn", "Cool?", "You turn", "Guess What?", "Hey!"};
    private int indexAnswer, indexChallenge;
    private char[] result = new char[6];
    private Random random;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");

        roundText = findViewById(R.id.round_text);
        challengeText = findViewById(R.id.challenge_text);

        mButtons[0] = findViewById(R.id.rock_btn);
        mButtons[1] = findViewById(R.id.paper_btn);
        mButtons[2] = findViewById(R.id.scissors_btn);

        mButtons[0].setOnClickListener(this);
        mButtons[1].setOnClickListener(this);
        mButtons[2].setOnClickListener(this);

        answer[0] = R.id.rock_btn;
        answer[1] = R.id.paper_btn;
        answer[2] = R.id.scissors_btn;

        random = new Random();

        game();
    }

    public void game() {
        if (round <= 5)
            roundText.setText("Round " + String.valueOf(round));
        indexAnswer = random.nextInt(answer.length);
        indexChallenge = random.nextInt(challengeStr.length);
        challengeText.setText(challengeStr[indexChallenge]);
    }

    @Override
    public void onClick(View v) {
        Button b = findViewById(v.getId());
        int comId = answer[indexAnswer];
        int id = v.getId();
        String display = "";

        if (id == comId) {
            display = "draw";
            score++;
            result[round] = 'd';
        } else {
            if (id == answer[0]) {
                //rock
                if (comId == answer[1]) {
                    //paper
                    display = "lose";
                } else if (comId == answer[2]) {
                    //scisors
                    display = "win";
                }
            } else if (id == answer[1]) {
                //paper
                if (comId == answer[0]) {
                    //rock
                    display = "win";
                } else if (comId == answer[2]) {
                    //scisors
                    display = "lose";
                }
            } else if (id == answer[2]) {
                //scisors
                if (comId == answer[0]) {
                    //rock
                    display = "lose";
                } else if (comId == answer[1]) {
                    //paper
                    display = "win";
                }
            }
        }
        if (display.equals("win")) {
            result[round] = 'w';
            score += 2;
        }
        if(display.equals("lose")) {
            result[round] = 'l';
        }


        int idUser = 0;
        int count = 0;
        for(Integer i : answer){
            if(i==id) {
                idUser = count;
                break;
            }
            count++;
        }
        AlertDialog.Builder dialog = new AlertDialog.Builder(GameActivity.this);
        dialog.setTitle("Your " + display);
        dialog.setMessage("You try :" + answerStr[idUser]  +
                "\nCompetitor try : " + answerStr[indexAnswer] + "\n your score = " + score);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (round > 5) {
                    Intent intent = new Intent(GameActivity.this, Summary.class);
                    intent.putExtra("score", score);
                    intent.putExtra("r1", result[1]);
                    intent.putExtra("r2", result[2]);
                    intent.putExtra("r3", result[3]);
                    intent.putExtra("r4", result[4]);
                    intent.putExtra("r5", result[5]);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
            }
        });
        dialog.show();


        if (round <= 5) {
            round++;
            game();
        }

    }
}