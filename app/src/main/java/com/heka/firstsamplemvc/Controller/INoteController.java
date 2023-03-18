package com.heka.firstsamplemvc.Controller;

import android.content.Context;

import com.heka.firstsamplemvc.Model.Note;

import java.util.ArrayList;

public interface INoteController {
    long OnSave(Note note, Context context);
    ArrayList<Note> getNoteList(Context context);
    void createDb(Context context);
    boolean isValidNote(Note note);
    int deleteNote(int noteId);
}
