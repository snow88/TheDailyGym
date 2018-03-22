package com.example.dell.DTUhack.room_db;

/**
 * Created by Dell on 14-02-2018.
 */

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.dell.DTUhack.models.Person;

import java.util.List;

@Dao
public interface Person_Dao {
    @Query("SELECT * FROM person")
    List<Person> getAll();

    @Query("SELECT * FROM person WHERE id IN (:personIds)")
    List<Person> loadAllByIds(int[] personIds);

    @Query("SELECT * FROM person WHERE id LIKE :id LIMIT 1")
    Person findById(int id);

    @Query("SELECT COUNT(*) FROM person")
    int numofPersons();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Person... persons);

    @Delete
    void delete(Person person);
}