package com.san47.taskmanager;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class TaskRepository {
    private TaskDao mTaskDao;
    private LiveData<List<Task>> mAllDeadline;

    TaskRepository(Application application){
        TaskDatabase db = TaskDatabase.getInstance(application);
        mTaskDao = db.DeadlineDao();
        mAllDeadline = mTaskDao.getDeadlineList();
    }

    LiveData<List<Task>> getAllDeadline() {
        return mAllDeadline;
    }

    void insert(final Task deadlines) {
        TaskDatabase.databaseWriteExecutor.execute(() -> {
            mTaskDao.insertDeadline(deadlines);
        });
    }

    public void delete(Task task)  {
        new deleteWordAsyncTask(mTaskDao).execute(task);
    }

    private TaskDao mAsyncTaskDao;


    private static class deleteWordAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao mAsyncTaskDao;

        deleteWordAsyncTask(TaskDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            mAsyncTaskDao.deleteDeadline(params[0]);
            return null;
        }
    }

}
