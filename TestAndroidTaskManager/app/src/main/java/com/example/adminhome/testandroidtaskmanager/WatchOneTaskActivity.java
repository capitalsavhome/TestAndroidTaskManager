package com.example.adminhome.testandroidtaskmanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

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

    /**
     * id of current task
     */
    private int mTaskId;

    /**
     * object for using dataBase
     */
    private SQLiteDatabase mDatabase;

    /**
     * arrayList of Stages from current Task
     */
    private ArrayList <Stage> mStagesAL;

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
        mTaskId = intent.getExtras().getInt("Task_id");

    }

    public void loadTaskFromDb() {
        Cursor cursor = mDatabase.query(MySQLiteOpenHelper.TASKS_TABLE_NAME, null, null, null, null,
                null, null);

        if (cursor.moveToFirst()) {
            int indexId = cursor.getColumnIndex(MySQLiteOpenHelper.TASK_ID);
            int indexName = cursor.getColumnIndex(MySQLiteOpenHelper.TITLE_COLUMN);
            int indexStartDate = cursor.getColumnIndex(MySQLiteOpenHelper.START_DATE_COLUMN);
            int indexEndDate = cursor.getColumnIndex(MySQLiteOpenHelper.END_DATE_COLUMN);

            do {
                int taskId = cursor.getInt(indexId);
                if (taskId == mTaskId) {
                    String taskTitle = cursor.getString(indexName);
                    String startDate = cursor.getString(indexStartDate);
                    String endDate = cursor.getString(indexEndDate);
                    showTask(taskTitle, startDate, endDate);
                }
            }
            while (cursor.moveToNext());
            cursor.close();
        }
    }

    public void loadStageFromDb() {
        Cursor cursor = mDatabase.query(MySQLiteOpenHelper.STAGES_TABLE_NAME, null, null, null, null,
                null, null);

        if (cursor.moveToFirst()) {
            int indexId = cursor.getColumnIndex(MySQLiteOpenHelper.ID_STAGE);
            int indexName = cursor.getColumnIndex(MySQLiteOpenHelper.STAGE_NAME_COLUMN);
            int indexStageId = cursor.getColumnIndex(MySQLiteOpenHelper.STAGE_ID_COLUMN);

            int stageIdColumn = cursor.getInt(indexStageId);
            if (stageIdColumn == mTaskId) {
                String stageTitle = cursor.getString(indexName);
                Stage stage = new Stage(stageTitle);
                mStagesAL.add(stage);
            }
        }
    }

    public void showTask(String taskTitle, String startDate, String endDate) {
        mTextViewTitle.setText(taskTitle);
        mTextViewStartDate.setText(startDate);
        mTextViewEndDate.setText(endDate);
    }
}
