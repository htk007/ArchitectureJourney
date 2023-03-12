package com.heka.firstsamplemvc.Controller;

import android.content.Context;

import java.util.ArrayList;

public interface INoteController {
    long OnSave(String noteText, Context context);
    ArrayList<String> getNoteList(Context context);
    void createDb(Context context);
}
