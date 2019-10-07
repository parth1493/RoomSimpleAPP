package com.parth.roomsimpleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parth.roomsimpleapp.databinding.ActivityAddStudentBinding;
import com.parth.roomsimpleapp.db.StudentApplicationDb;
import com.parth.roomsimpleapp.db.entity.Student;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddStudent extends AppCompatActivity {
    private static final String TAG = "AddStudent";
    long studentId;
    private StudentApplicationDb myAppDatabase;
    Calendar calander;
    SimpleDateFormat simpledateformat;
    String date;
    private Student student;
    private ActivityAddStudentBinding mActivityAddStudentBinding;
    private EventHandlerDatabinding mEventHandlerDatabinding;
    boolean value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);


        Bundle b = getIntent().getExtras();
        value = b.getBoolean("isUpdate");
        student = new Student();
        myAppDatabase = Room.databaseBuilder(getApplicationContext(),StudentApplicationDb.class,"StudentDB").build();

        mActivityAddStudentBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_student);

        mEventHandlerDatabinding = new EventHandlerDatabinding(this);
        mActivityAddStudentBinding.setSubmit(mEventHandlerDatabinding);

        if(value){
            studentId = b.getLong("studentId");
            new GetStudentAsyncTask().execute(studentId);
        }else {
            mActivityAddStudentBinding.setStudent(student);
        }
    }

    private class CreateStudentAsyncTask extends AsyncTask<Student,Void,Void> {

        @Override
        protected Void doInBackground(Student... students) {

            myAppDatabase.myDao().addStudent(students[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(AddStudent.this,MainActivity.class);
            startActivity(intent);
        }
    }

    private class UpdateStudentAsyncTask extends AsyncTask<Student,Void,Void> {

        @Override
        protected Void doInBackground(Student... students) {
            myAppDatabase.myDao().updateStudent(students[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(AddStudent.this,MainActivity.class);
            startActivity(intent);
        }
    }

    private class GetStudentAsyncTask extends AsyncTask<Long,Void,Void> {

        @Override
        protected Void doInBackground(Long... longs) {

            student = myAppDatabase.myDao().getStudent(longs[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mActivityAddStudentBinding.setStudent(student);
        }
    }

    public class EventHandlerDatabinding {

        private Context context;

        public EventHandlerDatabinding(Context context) {
            this.context = context;
        }

        public void submitButtonClickEvent(View view){
            Toast.makeText(context, "Enroll button click", Toast.LENGTH_SHORT).show();

            if(value){
                updateData();
            }
            else {
                CreateRecord();
            }
        }
    }

    private void CreateRecord() {
        new CreateStudentAsyncTask().execute(new Student(0,student.getName(),student.getEmail(),student.getCountry(),getCurrentTime()));
    }

    private String getCurrentTime() {
        calander = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("EEE,d MMM yyyy");
        return date = simpledateformat.format(calander.getTime());
    }

    private void updateData() {

        student.setCountry(student.getCountry());
        student.setName(student.getName());
        student.setDate(getCurrentTime());
        student.setEmail(student.getEmail());
        new UpdateStudentAsyncTask().execute(student);
    }

}
