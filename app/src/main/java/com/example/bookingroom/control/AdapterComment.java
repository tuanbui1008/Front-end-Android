package com.example.bookingroom.control;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingroom.R;
import com.example.bookingroom.model.CommentOfCustomer;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class AdapterComment extends RecyclerView.Adapter<AdapterComment.ViewHolder>  {

    private Context context;
    private List<CommentOfCustomer> commentList;

    public AdapterComment(Context context, List<CommentOfCustomer> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_cmt, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommentOfCustomer cmt = commentList.get(position);
        if (!cmt.getAvatar().isEmpty() && Objects.nonNull(cmt.getAvatar())){
            Picasso.get().load(cmt.getAvatar()).into(holder.avtCmt);
        }
        holder.cmt.setText(cmt.getContent());
        holder.fullName.setText(cmt.getFullName());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView avtCmt;
        private TextView cmt;
        private TextView fullName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avtCmt = itemView.findViewById(R.id.avt_cmt);
            cmt = itemView.findViewById(R.id.cmt_cus);
            fullName = itemView.findViewById(R.id.fullName);
        }
    }
}
