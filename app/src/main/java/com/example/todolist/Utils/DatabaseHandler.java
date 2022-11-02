package com.example.todolist.Utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.todolist.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private  static final int VERSION =1;
    private  static final String NAME="TODODATABASE";
    private static final String TABLE="TODO";
    private static final String ID="id";
    private  static final String TASK="task";
    private static final String STATUS="status";
    private static final String CREATE_TODO_TABLE= "CREATE TABLE " + NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TASK + " TEXT, "
            + STATUS + " INTEGER)";
    private SQLiteDatabase db;
    public DatabaseHandler(Context context)
    {
        super(context,NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //Creating DB
        db.execSQL(CREATE_TODO_TABLE);
    }
    @Override
    public  void onUpgrade(SQLiteDatabase db, int newversion,int oldversion )
    {
        //Dropping off TABLE
        db.execSQL("DROP TABLE IF EXISTS "+NAME);
        //creating new db
        onCreate(db);

    }

    public void openDatabase()
    {
        db=this.getWritableDatabase();
    }
    public void insertTask(ToDoModel todo)
    {
        ContentValues cv= new ContentValues();
        cv.put(TASK,todo.getTask());
        cv.put(STATUS,todo.getStatus());
        db.insert(NAME,null,cv);
    }
    @SuppressLint("Range")
    public List<ToDoModel> getAllTasks()
    {
        List<ToDoModel>allTasks= new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try
        {
            cur=db.query(NAME, null, null, null, null, null, null, null);
            if(cur!=null)
            {
                do{
                ToDoModel task= new ToDoModel();
                    task.setId(cur.getInt(cur.getColumnIndex(ID)));
                    task.setTask(cur.getString(cur.getColumnIndex(TASK)));
                    task.setStatus(cur.getInt(cur.getColumnIndex(STATUS)));
                    allTasks.add(task);
                }while (cur.moveToNext());
            }
        }
        finally {
            db.endTransaction();
            cur.close();

        }
        return allTasks;
    }
    public void updateStatus(int id, int status)
    {
        ContentValues cv= new ContentValues();
        cv.put(STATUS,status);
        db.update(NAME,cv,ID+"=?",new String[] {String.valueOf(id)});
    }

    public void updateTask(int id, String Task)
    {
        ContentValues cv = new ContentValues();
        cv.put(TASK,Task);
        db.update(NAME,cv,ID+"=?", new String[]{String.valueOf(id)});
    }
    public void deleteTask(int id)
    {
        db.delete(NAME,ID+"=?", new String[]{String.valueOf(id)});
    }


}
