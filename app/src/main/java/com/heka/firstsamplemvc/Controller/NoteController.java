package com.heka.firstsamplemvc.Controller;

import android.content.Context;

import com.heka.firstsamplemvc.Adapter.NoteDbAdapter;

public class NoteController implements INoteController{
    NoteDbAdapter noteDbAdapter;
    @Override
    public long OnSave(String noteText, Context context) {
        noteDbAdapter  = new NoteDbAdapter(context);
        long result = noteDbAdapter.insertNote(noteText);
        return result;
    }
}
