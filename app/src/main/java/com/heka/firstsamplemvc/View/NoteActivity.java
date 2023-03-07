package com.heka.firstsamplemvc.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.heka.firstsamplemvc.Controller.INoteController;
import com.heka.firstsamplemvc.Controller.NoteController;
import com.heka.firstsamplemvc.R;

public class NoteActivity extends AppCompatActivity implements INoteView {

    EditText etNote;
    Button btnSaveNote;
    INoteController iNoteController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_activity);
        initViews();
    }

    private void initViews(){
        btnSaveNote = findViewById(R.id.buttonSaveNote);
        etNote = findViewById(R.id.editTextNoteInput);
        iNoteController = new NoteController();
    }

    public void addNote(View view){
        Toast.makeText(this, "save note click", Toast.LENGTH_SHORT).show();
        iNoteController.OnSave(etNote.getText().toString(),getApplicationContext());
    }


    @Override
    public void OnNoteSaveSuccess(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnNoteSaveError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
