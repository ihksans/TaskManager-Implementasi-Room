package com.san47.taskmanager;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TaskDao {
    @Query("Select * from deadline")
    LiveData<List<Task>> getDeadlineList();
    @Insert
    void insertDeadline(Task mdeadline);
    @Update
    void updateDeadline(Task mdeadline);
    @Delete
    void deleteDeadline(Task mdeadline);
    @Query("Delete From deadline")
    void deleteAllDeadline();
}
