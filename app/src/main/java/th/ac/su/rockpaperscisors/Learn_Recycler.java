package th.ac.su.rockpaperscisors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import th.ac.su.rockpaperscisors.adapter.UserAdapter;

public class Learn_Recycler extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn__recycler);

        mRecyclerView = findViewById(R.id.recycler_view2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(Learn_Recycler.this));
        th.ac.su.rockpaperscisors.adapter2.UserAdapter adapter = new th.ac.su.rockpaperscisors.adapter2.UserAdapter(Learn_Recycler.this, null);
        mRecyclerView.setAdapter(adapter);

        Button backLearnBtn = findViewById(R.id.back_learn_btn);
        backLearnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Learn_Recycler.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}