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
        long result = noteDbAdapter.insertNote(note);
        return result;
    }

    @Override
    public ArrayList<Note> getNoteList(Context context) {
        noteDbAdapter = new NoteDbAdapter(context);
        ArrayList<Note> noteList = noteDbAdapter.getNotes();
        return noteList;
    }

    @Override
    public void createDb(Context context) {
        noteDbAdapter = new NoteDbAdapter(context);
        noteDbAdapter.createDb();
    }

    @Override
    public boolean isValidNote(Note note) {
        if (note.getNote().trim().isEmpty()) return false;
        else return true;
    }

    @Override
    public int deleteNote(int noteId) {
        int result =0;
        result = noteDbAdapter.deleteNoteWithId(noteId);
        return result;
    }

    @Override
    public int updateNote(Note note) {
        int result = 0;
        result = noteDbAdapter.updateNote(note.getNote(), note.getId());
        return result;
    }
}
