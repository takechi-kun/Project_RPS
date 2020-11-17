package th.ac.su.rockpaperscisors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import th.ac.su.rockpaperscisors.R;

public class UserNameActivity extends AppCompatActivity {

    String username = "";
    TextView userText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name);

        userText = findViewById(R.id.user_text);


        Button userBtn = findViewById(R.id.user_btn);
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = userText.getText().toString();

                Intent intent = new Intent(UserNameActivity.this,GameActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}