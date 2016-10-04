package com.example.adminhome.testandroidtaskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class WatchOneTaskActivity extends AppCompatActivity {

    /**
     * Title of Task
     */
    private TextView mTextViewTitle;

    /**
     * startDate of task
     */
    private TextView mTextViewStartDate;

    /**
     * endDate of Task
     */
    private TextView mTextViewEndDate;

    /**
     * name of current stage
     */
    private TextView mTextViewStage;

    /**
     * description of current stage
     */
    private TextView mTextViewStageDescr;

    /**
     * button back (return to MainActivity)
     */
    private Button mButtonBack;

    /**
     * button delete (remove current task)
     */
    private Button mButtonDelete;

    /**
     * button complete current stage
     */
    private Button mButtonStageCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_one_task);

        //initialization of view elements
        mTextViewTitle = (TextView) findViewById(R.id.view_activity_title);
        mTextViewStartDate = (TextView) findViewById(R.id.view_activity_startDate);
        mTextViewEndDate = (TextView) findViewById(R.id.view_activity_endDate);
        mTextViewStageDescr = (TextView) findViewById(R.id.view_activity_stageDescr);
        mTextViewStage = (TextView) findViewById(R.id.view_activity_currentStage);
        mButtonStageCompleted = (Button) findViewById(R.id.btn_stage_completed);
        mButtonBack = (Button) findViewById(R.id.btn_view_back);
        mButtonDelete = (Button) findViewById(R.id.btn_view_delete_task);

        Intent intent = getIntent();
        mTextViewTitle.setText(Integer.toString(intent.getExtras().getInt("Task_id")));
    }
}
