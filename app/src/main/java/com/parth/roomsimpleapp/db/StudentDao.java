package com.parth.roomsimpleapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.parth.roomsimpleapp.db.entity.Student;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert
    public long addStudent(Student student);

    @Query("select * from students")
    public List<Student> getStuents();

    @Query("select * from students where Student_id == :studentId")
    public Student getStudent(long studentId);

    @Update
    public void updateStudent(Student student);

    @Delete
    public void deteteStudent(Student student);
}
