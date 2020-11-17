package th.ac.su.rockpaperscisors.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import th.ac.su.rockpaperscisors.R;
import th.ac.su.rockpaperscisors.model.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context mContext;
    private User[] mUsers;

    public String countWin(String[] strs){
        int dCount=0,winCount=0,loseCount=0;
        for(String s :strs) {
            if (s.equals("draw")) {
                dCount++;
            } else if (s.equals("win")) {
                winCount++;
            } else if (s.equals("lose")) {
                loseCount++;
            }
        }
        return "win : "+String.valueOf(winCount)+" lose : "+String.valueOf(loseCount)+" draw : "+String.valueOf(dCount);


    }
    public UserAdapter(Context context, User[] users) {
        this.mContext = context;
        this.mUsers = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.component_his, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = mUsers[position];

        holder.name.setText(user.name);
        holder.score.setText("Score : "+String.valueOf(user.score));
        String[] u = new String[5];
        u[0] = user.rOne;
        u[1] = user.rTwo;
        u[2] = user.rThree;
        u[3] = user.rFour;
        u[4] = user.rFive;

        holder.summary.setText(countWin(u));
       holder.userImg.setImageResource(R.drawable.user);
    }

    @Override
    public int getItemCount() {
        return mUsers.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView score;
        TextView summary;
        ImageView userImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_com_text);
            score = itemView.findViewById(R.id.score_com_text);
            summary = itemView.findViewById(R.id.summary_his_text);
            userImg = itemView.findViewById(R.id.img_his);
        }
    }
}

