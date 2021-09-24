package com.gknsvs.instaclonejava.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gknsvs.instaclonejava.Model.Post;
import com.gknsvs.instaclonejava.databinding.RecyclerviewRowBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostView> {

    private ArrayList<Post> postArrayList;

    public PostAdapter(ArrayList<Post> postArrayList) {
        this.postArrayList = postArrayList;
    }

    @NonNull
    @Override
    public PostView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewRowBinding recyclerviewRowBinding=RecyclerviewRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PostView(recyclerviewRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostView holder, int position) {
        holder.binding.recyclerViewTxtEMail.setText(postArrayList.get(position).geteMail());
        holder.binding.recyclerViewTxtComment.setText(postArrayList.get(position).getComment());
        Picasso.get().load(postArrayList.get(position).getDownloadUrl()).into(holder.binding.recyclerViewImageView);
    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    public class PostView extends RecyclerView.ViewHolder {
        private RecyclerviewRowBinding binding;
        public PostView(RecyclerviewRowBinding binding) {
            super(binding.getRoot());
            this.binding=binding;

        }
    }
}
