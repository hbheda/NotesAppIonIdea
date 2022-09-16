package com.harshit.notesapplication.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.harshit.notesapplication.database.NotesPojo;
import com.harshit.notesapplication.databinding.NoteItemBinding;
import com.harshit.notesapplication.utils.OnItemClickListener;

import java.util.List;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.ViewHolder> {

    private List<NotesPojo> notesDataSet;
    private final OnItemClickListener listener;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private NoteItemBinding noteItemBinding;

        public ViewHolder(NoteItemBinding noteItemBinding) {
            super(noteItemBinding.getRoot());
            this.noteItemBinding = noteItemBinding;
        }
    }

    public NotesListAdapter(List<NotesPojo> dataSet, Context context, OnItemClickListener listener) {
        notesDataSet = dataSet;
        mContext = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        NoteItemBinding noteItemBinding = NoteItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        /*View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.app_item, viewGroup, false);*/

        return new ViewHolder(noteItemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        NotesPojo notesPojo = notesDataSet.get(position);

        viewHolder.noteItemBinding.txtTitle.setText(notesPojo.getTitle());
        viewHolder.noteItemBinding.txtDesc.setText(notesPojo.getDescription());

        viewHolder.noteItemBinding.root.setOnClickListener(
                v -> {
                    Log.e("Adapter","clicked item index is "+notesPojo.getTitle());
                    listener.onItemClick(notesPojo, position);
                }
        );

        viewHolder.noteItemBinding.imgDelete.setOnClickListener(
                v -> {
                    Log.e("Adapter","clicked item index is "+notesPojo.getTitle());
                    listener.onItemDeleteClick(notesPojo, position);
                }
        );

    }

    public void sortList(List<NotesPojo> filterlist) {
        notesDataSet = filterlist;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return notesDataSet.size();
    }
}

