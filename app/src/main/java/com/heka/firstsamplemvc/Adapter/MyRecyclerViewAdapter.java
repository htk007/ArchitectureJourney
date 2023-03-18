package com.heka.firstsamplemvc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    public MyRecyclerViewAdapter(Context context, ArrayList<Note> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.note_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note currentNote = mData.get(position);
        String note = currentNote.getNote();
        String createTime = currentNote.getCreateTime();
        holder.noteTextView.setText(note);
        holder.createTimeTextView.setText(createTime);
    }

    @Override
    public int getItemCount() {
       return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       TextView noteTextView;
       TextView createTimeTextView;
        ImageView imageViewEdit;
        ImageView imageViewDelete;

        ViewHolder(View itemView) {
            super(itemView);
            noteTextView = itemView.findViewById(R.id.textViewNote);
            createTimeTextView = itemView.findViewById(R.id.textViewCreateTime);
            imageViewEdit = itemView.findViewById(R.id.imageViewEdit);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            imageViewDelete.setOnClickListener(this);
            imageViewEdit.setOnClickListener(this);
            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.getId() == imageViewDelete.getId()){
                //Toast.makeText(imageViewDelete.getContext(), "delete clicked!", Toast.LENGTH_SHORT).show();
                mClickListener.onDeleteClick(view, getAdapterPosition());
            }
            else if(view.getId() == imageViewEdit.getId()){
                //Toast.makeText(imageViewEdit.getContext(), "update clicked!", Toast.LENGTH_SHORT).show();
                mClickListener.onUpdateClick(view, getAdapterPosition());
            }
           // if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public Note getItem(int position) {
   return mData.get(position);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
        void onDeleteClick(View view, int position);
        void onUpdateClick(View view, int positio);
    }

    public void updateEmployeeListItems(ArrayList<Note> notes) {
        final NotesDiffCallBack diffCallback = new NotesDiffCallBack(this.mData, notes);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.mData.clear();
        this.mData.addAll(notes);
        diffResult.dispatchUpdatesTo(this);
    }
}