package com.parth.roomsimpleapp.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName ="Students")
public class Student {

    @ColumnInfo(name ="Student_id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name ="Student_name")
    private String name;

    @ColumnInfo(name = "Student_email")
    private String email;

    @ColumnInfo(name = "Student_country")
    private String country;

    @ColumnInfo(name = "Student_date")
    private String date;

    public Student(int id, String name, String email, String country, String date) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
