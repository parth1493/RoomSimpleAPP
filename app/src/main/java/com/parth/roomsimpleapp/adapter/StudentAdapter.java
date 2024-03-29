package com.parth.roomsimpleapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.parth.roomsimpleapp.MainActivity;
import com.parth.roomsimpleapp.R;
import com.parth.roomsimpleapp.databinding.LayoutListBinding;
import com.parth.roomsimpleapp.db.entity.Student;

import java.util.ArrayList;


public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Student> studentsList;
    private MainActivity mainActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {

       private LayoutListBinding mLayoutListBinding;

        public MyViewHolder(LayoutListBinding layoutListBinding) {
            super(layoutListBinding.getRoot());

            this.mLayoutListBinding = layoutListBinding;
        }
    }


    public StudentAdapter(Context context, ArrayList<Student> contacts, MainActivity mainActivity) {
        this.context = context;
        this.studentsList = contacts;
        this.mainActivity = mainActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, parent, false);

      //  return new MyViewHolder(itemView);
        LayoutListBinding layoutListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.layout_list, parent, false);
            return new MyViewHolder(layoutListBinding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        final Student student = studentsList.get(position);

        holder.mLayoutListBinding.setStudent(student);

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

