package com.system.daisy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.system.daisy.R;
import com.system.daisy.entity.Notifications;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecyclerAdapterNotification extends RecyclerView.Adapter<RecyclerAdapterNotification.ViewHolder> {


    ArrayList<Notifications> notifications;

    public RecyclerAdapterNotification(ArrayList<Notifications> notifications) {
        this.notifications=notifications;
    }


    @NonNull
    @Override
    public RecyclerAdapterNotification.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterNotification.ViewHolder holder, int position) {
        holder.notificationTitle.setText(notifications.get(position).getTitle());
        holder.notificationContent.setText(notifications.get(position).getContent());
        holder.notificationTime.setText(changeDateToString(notifications.get(position).getTime()));
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView notificationTitle, notificationContent, notificationTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationTitle = itemView.findViewById(R.id.notificationTitle);
            notificationContent = itemView.findViewById(R.id.notificationDetail);
            notificationTime = itemView.findViewById(R.id.notificationTime);
        }
    }
    public String changeDateToString(Date date) {
        String pattern = "HH:mm dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        String dateAsString = df.format(date);
        return dateAsString;
    }
}
