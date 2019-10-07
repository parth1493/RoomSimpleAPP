package com.parth.roomsimpleapp.db.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.parth.roomsimpleapp.BR;

@Entity(tableName ="Students")
public class Student extends BaseObservable {

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
    @Ignore
    public Student(){

    }
    @Bindable
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        notifyPropertyChanged(BR.country);
    }

    @Bindable
    public String getDate() {
        return date;
    }

    @Bindable
    public int getId() {
        return id;
    }

    @Bindable
    public String getName() { return name; }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setDate(String date) {
        this.date = date;
        notifyPropertyChanged(BR.date);
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }
}
