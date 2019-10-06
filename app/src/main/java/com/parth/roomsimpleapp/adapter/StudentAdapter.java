package com.parth.roomsimpleapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.parth.roomsimpleapp.MainActivity;
import com.parth.roomsimpleapp.R;
import com.parth.roomsimpleapp.db.entity.Student;

import java.util.ArrayList;


public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Student> studentsList;
    private MainActivity mainActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView emil;
        public TextView country;
        public TextView date;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.studentName);
            emil = view.findViewById(R.id.studentEmail);
            country = view.findViewById(R.id.countryName);
            date = view.findViewById(R.id.date);
        }
    }


    public StudentAdapter(Context context, ArrayList<Student> contacts, MainActivity mainActivity) {
        this.context = context;
        this.studentsList = contacts;
        this.mainActivity = mainActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        final Student student = studentsList.get(position);

        holder.name.setText(student.getName());
        holder.emil.setText(student.getEmail());
        holder.country.setText(student.getCountry());
        holder.date.setText(student.getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                mainActivity.addAndEditContacts(true, student, position);
            }
        });

    }

    @Override
    public int getItemCount() {

        return studentsList.size();
    }


}

