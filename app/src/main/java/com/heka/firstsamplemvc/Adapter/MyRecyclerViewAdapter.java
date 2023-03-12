package com.heka.firstsamplemvc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.heka.firstsamplemvc.Model.Note;
import com.heka.firstsamplemvc.NotesDiffCallBack;
import com.heka.firstsamplemvc.R;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Note> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context, ArrayList<Note> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.note_list_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note currentNote = mData.get(position);
        String note = currentNote.getNote();
        String createTime = currentNote.getCreateTime();
        holder.noteTextView.setText(note);
        holder.createTimeTextView.setText(createTime);
    }

    // total number of rows
    @Override
    public int getItemCount() {
       return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       TextView noteTextView;
       TextView createTimeTextView;

        ViewHolder(View itemView) {
            super(itemView);
            noteTextView = itemView.findViewById(R.id.textViewNote);
            createTimeTextView = itemView.findViewById(R.id.textViewCreateTime);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
   return "";
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void updateEmployeeListItems(ArrayList<Note> notes) {
        final NotesDiffCallBack diffCallback = new NotesDiffCallBack(this.mData, notes);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.mData.clear();
        this.mData.addAll(notes);
        diffResult.dispatchUpdatesTo(this);
    }
}