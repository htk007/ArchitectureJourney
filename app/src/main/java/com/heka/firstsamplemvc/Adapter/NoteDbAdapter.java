package com.heka.firstsamplemvc.Adapter;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.heka.firstsamplemvc.Model.DbConstants;
import com.heka.firstsamplemvc.Model.Note;
import com.heka.firstsamplemvc.Model.NoteDbHelper;

import java.util.ArrayList;

public class NoteDbAdapter {
    NoteDbHelper noteDbHelper;
    public NoteDbAdapter(Context context){
        noteDbHelper = new NoteDbHelper(context);
    }
    public void createDb(){
        SQLiteDatabase dbb = noteDbHelper.getWritableDatabase();
        //noteDbHelper.onCreate(dbb);
    }



    public long insertNote(Note note)
    {
        SQLiteDatabase dbb = noteDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstants.NOTE, note.getNote());
        contentValues.put(DbConstants.CREATE_TIME, note.getCreateTime());
        long id = dbb.insert(DbConstants.TABLE_NAME, null , contentValues);
        return id;
    }

    public ArrayList<Note> getNotes()
    {
        SQLiteDatabase db = noteDbHelper.getWritableDatabase();
        String[] columns = {DbConstants.NID,DbConstants.NOTE, DbConstants.CREATE_TIME};
        Cursor cursor =db.query(DbConstants.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        ArrayList<Note> noteList = new ArrayList<>();
        while (cursor.moveToNext())
        {
            Note note = new Note();
           int cid =cursor.getInt(cursor.getColumnIndex(DbConstants.NID));
           String noteText =cursor.getString(cursor.getColumnIndex(DbConstants.NOTE));
           String createTime =cursor.getString(cursor.getColumnIndex(DbConstants.CREATE_TIME));
           note.setNote(noteText);
           note.setCreateTime(createTime);
           note.setId(cid);
           noteList.add(note);
        }
        return noteList;
    }


    public  int deleteNoteWithId(int  noteId)
    {
        SQLiteDatabase db = noteDbHelper.getWritableDatabase();
        String[] selectionArgs = { String.valueOf(noteId) };

        int count =db.delete(DbConstants.TABLE_NAME ,DbConstants.NID+" = ?", selectionArgs);
        return  count;
    }


    public int updateNote(String newNote, int noteId)
    {
        SQLiteDatabase db = noteDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstants.NOTE,newNote);
        String[] selectionArgs = { String.valueOf(noteId) };
        int count =db.update(DbConstants.TABLE_NAME,contentValues, DbConstants.NID+" = ?",selectionArgs );
        return count;
    }
}
