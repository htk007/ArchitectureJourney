package com.heka.firstsamplemvc.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.heka.firstsamplemvc.Controller.DateTimeHelper;
import com.heka.firstsamplemvc.Controller.INoteController;
import com.heka.firstsamplemvc.Controller.NoteController;
import com.heka.firstsamplemvc.Model.Note;
import com.heka.firstsamplemvc.R;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity implements INoteView {

    EditText etNote;
    Button btnSaveNote, btnGetNote;
    INoteController iNoteController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_activity);
        initViews();
        iNoteController.createDb(this);
    }

    private void initViews(){
        btnSaveNote = findViewById(R.id.buttonSaveNote);
        btnGetNote = findViewById(R.id.buttonListNotes);
        etNote = findViewById(R.id.editTextNoteInput);
        iNoteController = new NoteController();
    }

    public void addNote(View view){
        Note note = new Note();
        Toast.makeText(this, "save note click", Toast.LENGTH_SHORT).show();
        note.setNote(etNote.getText().toString());
        String currentDateTime =  DateTimeHelper.getCurrentDateTime();
        note.setCreateTime(currentDateTime);
        iNoteController.OnSave(note,this);
    }

    public void fetchNote(View view){
        ArrayList<Note> noteList = iNoteController.getNoteList(this);
        for(Note note : noteList){
            System.out.println("HKLOGG NOTELIST "+ note.getNote() + " create time " + note.getCreateTime());
        }

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
