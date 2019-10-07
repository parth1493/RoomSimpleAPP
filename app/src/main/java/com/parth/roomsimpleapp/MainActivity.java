package com.parth.roomsimpleapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.parth.roomsimpleapp.adapter.StudentAdapter;
import com.parth.roomsimpleapp.db.StudentApplicationDb;
import com.parth.roomsimpleapp.db.entity.Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private StudentAdapter studentAdapter;
    private ArrayList<Student> studentArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private StudentApplicationDb myAppDatabase;


    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" Student Record ");

        recyclerView = findViewById(R.id.recycler_view_contacts);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: add entry");
                addAndEditContacts(false, null, -1);
            }
        });

        myAppDatabase = Room.databaseBuilder(getApplicationContext(), StudentApplicationDb.class, "StudentDB").build();

        load();

        studentAdapter = new StudentAdapter(this, studentArrayList, MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(studentAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Student studentDelete = studentArrayList.get(viewHolder.getAdapterPosition());
                deleteStudent(studentDelete);
            }
        }).attachToRecyclerView(recyclerView);
    }

        private void deleteStudent(Student student){

        new DeleteStudentsAsyncTask().execute(student);
    }

    public void load(){
        new GetAllStudentsAsyncTask().execute();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addAndEditContacts(final boolean isUpdate, final Student student, final int position) {

        Intent intent = new Intent(MainActivity.this,AddStudent.class);
        Bundle b = new Bundle();
        b.putBoolean("isUpdate", isUpdate);
        if(isUpdate){
            Log.i(TAG, "addAndEditContacts: "+student.getId());
            b.putLong("studentId",student.getId());
        }
        intent.putExtras(b);
        startActivity(intent);
    }

    private class GetAllStudentsAsyncTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            studentArrayList.addAll(myAppDatabase.myDao().getStuents());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPreExecute();
            studentAdapter.notifyDataSetChanged();
        }
    }

    private class DeleteStudentsAsyncTask extends AsyncTask<Student,Void,Void>{

        @Override
        protected Void doInBackground(Student... students) {
            myAppDatabase.myDao().deteteStudent(students[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
