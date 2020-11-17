package th.ac.su.rockpaperscisors.adapter2;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import th.ac.su.rockpaperscisors.GameActivity;
import th.ac.su.rockpaperscisors.History_Recycler;
import th.ac.su.rockpaperscisors.Learn_Recycler;
import th.ac.su.rockpaperscisors.R;
import th.ac.su.rockpaperscisors.model.User2;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private final Context mContext;
    private User2[] mUsers;


    public UserAdapter(Context context, User2[] users) {
        this.mContext = context;
        User2[] u = {new User2("Rock","",R.drawable.rock),
                new User2("Paper","",R.drawable.paper),
                new User2("Scissors","",R.drawable.scissors)};
        this.mUsers = u;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.component_learn, parent, false);
        return new MyViewHolder(mContext,v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        User2 user = mUsers[position];

        holder.type.setText(user.type);

        //holder.word.setText(user.word);
        holder.typeImg.setImageResource(user.imgId);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "";
                String word = "";
                if(holder.type.getText().equals("Rock")){
                    str = "Rock";
                    word = "Win : scisors. Lose : Paper";
                }else if(holder.type.getText().equals("Paper")){
                    str = "Paper";
                    word = "Win : Rock. Lose : Scissors";
                }else if(holder.type.getText().equals("Scissors")){
                    str = "Scissors";
                    word = "Win : Paper. Lose : Rock";
                }
                AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
                dialog.setTitle(str);
                dialog.setMessage(word);
                dialog.setPositiveButton("OK", null);
                dialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mUsers.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView type;
        TextView word;
        ImageView typeImg;

        public MyViewHolder(final Context context,@NonNull final View itemView) {
            super(itemView);

            type = itemView.findViewById(R.id.item_text);
            //word = itemView.findViewById(R.id.word_text);
            typeImg = itemView.findViewById(R.id.img_learn);

        }
    }
}

