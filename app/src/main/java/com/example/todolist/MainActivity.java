package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.todolist.Model.ToDoModel;
import com.example.todolist.ToDoAdapter.ToDoAdapter;
import com.example.todolist.Utils.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogCloseListener{
    private RecyclerView tasksRecyclerview;
    private ToDoAdapter tasksAdapter;
    private List<ToDoModel> tasklist;
    private DatabaseHandler db;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        db= new DatabaseHandler(this);
        db.openDatabase();
        tasksRecyclerview = findViewById(R.id.Tasksrecyclerview);
        tasksRecyclerview.setLayoutManager( new LinearLayoutManager(this));
        tasksAdapter= new ToDoAdapter(db,MainActivity.this);
        tasksRecyclerview.setAdapter(tasksAdapter);


        //ItemTouchHelper itemTouchHelper = new
          //      ItemTouchHelper(new net.penguincoders.doit.RecyclerItemTouchHelper(tasksAdapter));
        //itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        fab = findViewById(R.id.fab);

        tasklist = db.getAllTasks();
        Collections.reverse(tasklist);
        tasksAdapter.setTasks(tasklist);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });
    }
    @Override
    public void handleDialogClose(DialogInterface dialog)
    {
        tasklist = db.getAllTasks();
        Collections.reverse(tasklist);
        tasksAdapter.setTasks(tasklist);
        tasksAdapter.notifyDataSetChanged();


    }
}