package com.heka.firstsamplemvc.Controller;

import android.content.Context;

public interface INoteController {
    long OnSave(String noteText, Context context);
}
