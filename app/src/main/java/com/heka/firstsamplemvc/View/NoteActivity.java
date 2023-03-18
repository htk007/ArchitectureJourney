package com.heka.firstsamplemvc.View;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, noteList);
        myRecyclerViewAdapter.setClickListener(this);
        recyclerView.setAdapter(myRecyclerViewAdapter);
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
        note.setNote(etNote.getText().toString());
        String currentDateTime =  DateTimeHelper.getCurrentDateTime();
        note.setCreateTime(currentDateTime);
        if(iNoteController.isValidNote(note)){
            iNoteController.OnSave(note,this);
            fetchNote();
            myRecyclerViewAdapter.updateEmployeeListItems(noteList);
        }else{
            Toast.makeText(this, "NOT EMPTY!", Toast.LENGTH_SHORT).show();
        }

    }

    public void fetchNote(){
        noteList = iNoteController.getNoteList(this);
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

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + myRecyclerViewAdapter.getItem(position).getId()+ " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(View view, int position) {
        //myRecyclerViewAdapter.getItem(position).getId()
        //Toast.makeText(this, "delete clicked!!!", Toast.LENGTH_SHORT).show();
        iNoteController.deleteNote(myRecyclerViewAdapter.getItem(position).getId());
        fetchNote();
        myRecyclerViewAdapter.updateEmployeeListItems(noteList);
    }

    @Override
    public void onUpdateClick(View view, int positio) {
        showAlertDialog(myRecyclerViewAdapter.getItem(positio));
    }

    private void showAlertDialog(Note note){
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.CustomAlertDialog);

        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.update_dialog, null);
        builder.setView(customLayout);
        EditText editText = customLayout.findViewById(R.id.editText);
        editText.setText(note.getNote());
        // add a button
        builder.setPositiveButton("UPDATE", (dialog, which) -> {
            // send data from the AlertDialog to the Activity
            //EditText editText = customLayout.findViewById(R.id.editText);
            note.setNote(editText.getText().toString());
            sendDialogDataToActivity(note);
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    // Do something with the data coming from the AlertDialog
    private void sendDialogDataToActivity(Note note) {
        iNoteController.updateNote(note);
        fetchNote();
        myRecyclerViewAdapter.updateEmployeeListItems(noteList);
    }
}
