package com.heka.firstsamplemvc;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.heka.firstsamplemvc.Model.Note;

import java.util.ArrayList;

public class NotesDiffCallBack extends DiffUtil.Callback  {
    private final ArrayList<Note> mOldNoteList;
    private final ArrayList<Note> mNewNoteList;

    public NotesDiffCallBack(ArrayList<Note> oldNoteList, ArrayList<Note> newNoteList) {
        this.mOldNoteList = oldNoteList;
        this.mNewNoteList = newNoteList;
    }

    @Override
    public int getOldListSize() {
        return mOldNoteList.size();
    }
    @Override
    public int getNewListSize() {
        return mNewNoteList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldNoteList.get(oldItemPosition)== mNewNoteList.get(
                newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Note oldEmployee = mOldNoteList.get(oldItemPosition);
        final Note newEmployee = mNewNoteList.get(newItemPosition);

        return oldEmployee.getCreateTime().equals(newEmployee.getCreateTime());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
