package com.example.yenematch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {

    private List<MatchModel> matchList;
    private OnMatchClickListener listener;

    // Interface to handle clicks in the Activity
    public interface OnMatchClickListener {
        void onMatchClick(MatchModel match);
    }

    public MatchAdapter(List<MatchModel> matchList, OnMatchClickListener listener) {
        this.matchList = matchList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_match_adapter, parent, false);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        MatchModel match = matchList.get(position);
        holder.tvName.setText(match.getFullName());
        holder.tvLastMessage.setText(match.getLastMessage());

        // Handle clicking on a match to open the chat
        holder.itemView.setOnClickListener(v -> listener.onMatchClick(match));
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public static class MatchViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvLastMessage;

        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvMatchName);
            tvLastMessage = itemView.findViewById(R.id.tvLastMessage);
        }
    }
}