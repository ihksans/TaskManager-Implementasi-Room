package com.san47.taskmanager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.san47.taskmanager.MainActivity.taskArrayList;

public class MainFragment extends Fragment {
    public ArrayList<Task> tasks;
    public TaskAdapter adapter;
    private Context context;

    TaskViewModel mTaskViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ArrayList<Task> createDeadlineList() {
        context = getActivity();

        String Nama;
        String Details;
        String Date;

        return taskArrayList;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment, container, false);
        RecyclerView rvDeadline = view.findViewById(R.id.rvDeadline);

        tasks = createDeadlineList();

        adapter = new TaskAdapter(tasks);

        LinearLayoutManager layoutmanager = new LinearLayoutManager(this.getActivity().getBaseContext());
        rvDeadline.setAdapter(adapter);
        rvDeadline.setLayoutManager(layoutmanager);
        rvDeadline.setHasFixedSize(true);

        mTaskViewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        mTaskViewModel.getAllDeadline().observe(requireActivity(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.setDeadline(tasks);
            }
        });

        // Inflate the layout for this fragment
        return view;

    }
}
