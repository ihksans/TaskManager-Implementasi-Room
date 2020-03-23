package com.san47.taskmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String NameFromIntent1;
    String DetailFromIntent1;
    String DateFromIntent1;

    TaskViewModel mTaskViewModel;

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    MainFragment MainFragment;

    Intent data = new Intent("com.san47.taskmanager.AddDeadlineActivity");

    static ArrayList<Task> taskArrayList = new ArrayList<Task>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        MainFragment = new MainFragment();

        fragmentTransaction.replace(
                android.R.id.content, MainFragment);

        fragmentTransaction.commit();
    }

    public void onDataDelete(Task task){
        mTaskViewModel.delete(task);
    }

    public void addDeadline(View view) {
        startActivityForResult(data,1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                NameFromIntent1 = data.getStringExtra("deadlineName");
                DetailFromIntent1 = data.getStringExtra("deadlineDetail");
                DateFromIntent1 = data.getStringExtra("deadlineDate");

                Task deadlinefromIntent = new Task(NameFromIntent1,DetailFromIntent1,DateFromIntent1);
                mTaskViewModel.insert(deadlinefromIntent);
                Toast.makeText(this, "Insert To Database", Toast.LENGTH_LONG).show();
            }
        }
        MainFragment.adapter.notifyDataSetChanged();
        super.onActivityResult(requestCode,resultCode,data);
    }
}
