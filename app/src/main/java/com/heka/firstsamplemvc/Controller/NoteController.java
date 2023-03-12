package com.heka.firstsamplemvc.Controller;

import android.content.Context;

import com.heka.firstsamplemvc.Adapter.NoteDbAdapter;
import com.heka.firstsamplemvc.Model.Note;

import java.util.ArrayList;

public class NoteController implements INoteController{
    NoteDbAdapter noteDbAdapter;
    @Override
    public long OnSave(Note note, Context context) {
        noteDbAdapter  = new NoteDbAdapter(context);
        long result = noteDbAdapter.insertNote(note.getNote());
        return result;
    }

    @Override
    public ArrayList<String> getNoteList(Context context) {
        noteDbAdapter = new NoteDbAdapter(context);
        ArrayList<String> noteList = noteDbAdapter.getNotes();
        return noteList;
    }

    @Override
    public void createDb(Context context) {
        noteDbAdapter = new NoteDbAdapter(context);
        noteDbAdapter.createDb();
    }
}
