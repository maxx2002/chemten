package com.example.chemten.view.LeaderboardView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chemten.R;
import com.example.chemten.model.Users;

import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.CardViewViewHolder>
{
    private Context context;
    private List<Users.Leaderboard> listUser;
    private List<Users.Leaderboard> getListUser()
    {
        return listUser;
    }
    public void setListUser(List<Users.Leaderboard> listUser) {
        this.listUser = listUser;
    }
    public LeaderboardAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_card, parent, false);
        return new LeaderboardAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final Users.Leaderboard results = getListUser().get(position);
        holder.no.setText(String.valueOf(results.getId()));
        holder.nama.setText(String.valueOf(results.getUser_id()));
        holder.skor.setText(String.valueOf(results.getRank_score()));
    }

    @Override
    public int getItemCount() {
        return getListUser().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView no, nama, skor;
        CardView cardView;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            no = itemView.findViewById(R.id.no);
            nama = itemView.findViewById(R.id.nama);
            skor = itemView.findViewById(R.id.skor);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}