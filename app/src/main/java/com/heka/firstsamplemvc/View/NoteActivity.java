package com.heka.firstsamplemvc.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.heka.firstsamplemvc.Adapter.MyRecyclerViewAdapter;
import com.heka.firstsamplemvc.Controller.DateTimeHelper;
import com.heka.firstsamplemvc.Controller.INoteController;
import com.heka.firstsamplemvc.Controller.NoteController;
import com.heka.firstsamplemvc.Model.Note;
import com.heka.firstsamplemvc.R;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity implements INoteView, MyRecyclerViewAdapter.ItemClickListener  {

    EditText etNote;
    Button btnSaveNote;
    INoteController iNoteController;
    RecyclerView recyclerView;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    ArrayList<Note> noteList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_activity);
        initViews();
        iNoteController.createDb(this);
        fetchNote();
    }

    private void initViews(){
        btnSaveNote = findViewById(R.id.buttonSaveNote);
        etNote = findViewById(R.id.editTextNoteInput);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

    public void fetchNote(){
        noteList = iNoteController.getNoteList(this);
        for(Note note : noteList){
            System.out.println("HKLOGG NOTELIST "+ note.getNote() + " create time " + note.getCreateTime());
        }
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, noteList);
        myRecyclerViewAdapter.setClickListener(this);
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }


    @Override
    public void OnNoteSaveSuccess(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnNoteSaveError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + myRecyclerViewAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}
