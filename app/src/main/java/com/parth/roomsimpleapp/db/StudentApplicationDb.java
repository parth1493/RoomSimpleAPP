package com.parth.roomsimpleapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.parth.roomsimpleapp.db.entity.Student;

@Database(entities = {Student.class}, version = 1)
public abstract class StudentApplicationDb extends RoomDatabase {

    public abstract StudentDao myDao();
}
