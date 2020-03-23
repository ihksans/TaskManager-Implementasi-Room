package com.san47.taskmanager;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository mRepository;
    private LiveData<List<Task>> mAllDeadline;

    public TaskViewModel(Application application){
        super(application);
        mRepository = new TaskRepository(application);
        mAllDeadline = mRepository.getAllDeadline();
    }

    LiveData<List<Task>> getAllDeadline() {
        return mAllDeadline;
    }
    void insert(Task task){
        mRepository.insert(task);
    }
    void delete(Task task) {mRepository.delete(task);}
}
