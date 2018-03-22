package com.example.dell.DTUhack.room_db;

import android.arch.persistence.room.*;

import com.example.dell.DTUhack.models.Task;

import java.util.List;

@Dao
public interface Task_Dao {
    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("SELECT * FROM task WHERE id IN (:taskIds)")
    List<Task> loadAllByIds(int[] taskIds);

    @Query("SELECT * FROM task WHERE id LIKE :id LIMIT 1")
    Task findById(int id);

    @Query("SELECT COUNT(*) FROM task")
    int numofTasks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Task... tasks);

    @Delete
    void delete(Task task);
}
