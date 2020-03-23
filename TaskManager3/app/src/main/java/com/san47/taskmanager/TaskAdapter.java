package com.san47.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView taskName;
        public TextView taskDetails;
        public TextView taskDate;
        public Button removeButton;

        public ViewHolder(View itemView) {
            super(itemView);

            taskName = (TextView) itemView.findViewById(R.id.deadline_name);
            taskDetails = (TextView) itemView.findViewById(R.id.deadline_details);
            taskDate = (TextView) itemView.findViewById(R.id.deadline_date);
            removeButton = (Button) itemView.findViewById(R.id.remove_button);
        }
    }

    private List<Task> mTask;

    void setDeadline(List<Task> task) {
        mTask = task;
        notifyDataSetChanged();
    }

    public TaskAdapter(List<Task> tasks) {
        mTask = tasks;
    }



    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View deadlineView = inflater.inflate(R.layout.item_deadline,parent,false);

        ViewHolder viewHolder = new ViewHolder(deadlineView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder viewHolder, final int position) {
        Task deadlines = mTask.get(position);

        TextView deadlineName = viewHolder.taskName;
        deadlineName.setText(deadlines.getName());

        TextView deadlineDetails = viewHolder.taskDetails;
        deadlineDetails.setText(deadlines.getDetails());

        TextView deadlineDate = viewHolder.taskDate;
        deadlineDate.setText(deadlines.getDate());

        Button removeButton = viewHolder.removeButton;
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTask.get(position);
                mTask.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mTask.size());
            }
        });
    }


    public Task getDeadlineAtPosition (int position) {
        return mTask.get(position);
    }

    @Override
    public int getItemCount() {
        return mTask.size();
    }
}
