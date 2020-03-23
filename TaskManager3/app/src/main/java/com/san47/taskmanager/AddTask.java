package com.san47.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddTask extends AppCompatActivity {

    EditText taskName;
    EditText taskDetail;
    EditText taskDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deadline);

        taskName = (EditText)findViewById(R.id.deadlineNameForm);
        taskDetail = (EditText)findViewById(R.id.deadlineDetailForm);
        taskDate = (EditText)findViewById(R.id.deadlineDateForm);

        Button button1 = findViewById(R.id.insertButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();

                String Name = taskName.getText().toString();
                String Detail = taskDetail.getText().toString();
                String Date = taskDate.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("deadlineName",Name);
                bundle.putString("deadlineDetail",Detail);
                bundle.putString("deadlineDate",Date);

                i.putExtras(bundle);
                setResult(RESULT_OK,i);
                finish();
            }
        });

    }
}
