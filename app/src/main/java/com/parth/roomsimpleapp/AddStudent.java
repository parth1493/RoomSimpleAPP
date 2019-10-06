package com.parth.roomsimpleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parth.roomsimpleapp.db.StudentApplicationDb;
import com.parth.roomsimpleapp.db.entity.Student;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddStudent extends AppCompatActivity {
    private EditText nameEditText,emailEditText,countryEditText;
    private Button submit;
    long studentId;
    private StudentApplicationDb myAppDatabase;
    Calendar calander;
    SimpleDateFormat simpledateformat;
    String date;
    private Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        nameEditText = findViewById(R.id.studentName);
        emailEditText = findViewById(R.id.studentEmail);
        countryEditText = findViewById(R.id.studentCountry);
        submit = findViewById(R.id.submit);
        Bundle b = getIntent().getExtras();
        final boolean value = b.getBoolean("isUpdate");

        myAppDatabase = Room.databaseBuilder(getApplicationContext(),StudentApplicationDb.class,"StudentDB").build();

        if(value){
            studentId = b.getLong("studentId");
            new GetStudentAsyncTask().execute(studentId);
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value){
                    student.setName(nameEditText.getText().toString());
                    student.setEmail(emailEditText.getText().toString());
                    student.setCountry(countryEditText.getText().toString());
                    new UpdateStudentAsyncTask().execute(student);
                }
                else
                {
                    calander = Calendar.getInstance();
                    simpledateformat = new SimpleDateFormat("EEE,d MMM yyyy");
                    date = simpledateformat.format(calander.getTime());
                    new CreateStudentAsyncTask().execute(new Student(0,nameEditText.getText().toString(),emailEditText.getText().toString(),countryEditText.getText().toString(),date));
                }
            }
        });
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
            nameEditText.setText(student.getName());
            countryEditText.setText(student.getCountry());
            emailEditText.setText(student.getEmail());
        }
    }

}
