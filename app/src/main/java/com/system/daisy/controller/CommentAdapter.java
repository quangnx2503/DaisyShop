package com.system.daisy.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.system.daisy.R;
import com.system.daisy.entity.Comment;


import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    Context context;
    List<Comment> commentList;

    public CommentAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.comment_row_items,parent,false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.question.setText(commentList.get(position).getEmail());
        String answer = commentList.get(position).getAnswer() == null ? "Please wait for answer..." :
                commentList.get(position).getAnswer() ;
        holder.answer.setText(answer);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView question;
        TextView answer;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.textViewQuestion);
            answer = itemView.findViewById(R.id.textViewAnswer);
        }
    }
}
