package th.ac.su.rockpaperscisors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import th.ac.su.rockpaperscisors.adapter.UserAdapter;
import th.ac.su.rockpaperscisors.db.AppDatabase;
import th.ac.su.rockpaperscisors.model.User;
import th.ac.su.rockpaperscisors.util.AppExecutors;

public class History_Recycler extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onResume() {
        super.onResume();

        final AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(History_Recycler.this);
                final User[] users = db.userDao().getAllUsers();

                executors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {

                        th.ac.su.rockpaperscisors.adapter.UserAdapter adapter = new th.ac.su.rockpaperscisors.adapter.UserAdapter(History_Recycler.this, users);
                        mRecyclerView.setAdapter(adapter);
                    }
                });




            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history__recycler);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(History_Recycler.this));

        Button backHisBtn = findViewById(R.id.back_his_btn);
        backHisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(History_Recycler.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}