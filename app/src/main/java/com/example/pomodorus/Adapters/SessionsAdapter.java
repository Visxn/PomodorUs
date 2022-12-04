package com.example.pomodorus.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pomodorus.R;

import java.util.ArrayList;
//REcyclerView for the sessions history
public class SessionsAdapter extends RecyclerView.Adapter<SessionsAdapter.MyViewHolder> {

   Context context;
   ArrayList<Session> sessionsArray;

    public SessionsAdapter(Context context, ArrayList<Session> sessionsArray) {
        this.context = context;
        this.sessionsArray = sessionsArray;
    }

    @NonNull
    @Override
    public SessionsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SessionsAdapter.MyViewHolder holder, int position) {
    Session session = sessionsArray.get(position);

    holder.sessionName.setText(session.sessionName);
    holder.date.setText(session.date);
    }

    @Override
    public int getItemCount() {
        return sessionsArray.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView sessionName, date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sessionName = itemView.findViewById(R.id.sessionId);
            date = itemView.findViewById(R.id.dateId);

        }
    }
}
