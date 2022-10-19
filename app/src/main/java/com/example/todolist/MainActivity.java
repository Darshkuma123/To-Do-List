package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.todolist.Model.ToDoModel;
import com.example.todolist.ToDoAdapter.ToDoAdapter;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView tasksRecyclerview;
    private ToDoAdapter tasksAdapter;
    private List<ToDoModel> tasklist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        tasksRecyclerview = findViewById(R.id.Tasksrecyclerview);
        tasksRecyclerview.setLayoutManager( new LinearLayoutManager(this));
        tasksAdapter= new ToDoAdapter(db,MainActivity.this);
        tasksRecyclerview.setAdapter(tasksAdapter);

    }
}