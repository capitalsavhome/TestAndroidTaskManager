package com.example.adminhome.testandroidtaskmanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WatchOneTaskActivity extends AppCompatActivity {

    private final static int FIRST_ELEMENT = 0;

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

        mStagesAL = new ArrayList<>();

        mDatabase = MainActivity.mMySQLiteOpenHelper.getWritableDatabase();

        Intent intent = getIntent();
        mTaskId = intent.getExtras().getInt("Task_id");

        loadTaskFromDb();
        loadStageFromDb();

        for (int i = 0; i < mStagesAL.size(); i++) {
            Log.d(Constants.TAG, "Проверка размера коллекцииCreate");
        }

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

            do {
                int stageIdColumn = cursor.getInt(indexStageId);
                if (stageIdColumn == mTaskId) {
                    int idOfStage = cursor.getInt(indexId);
                    String stageTitle = cursor.getString(indexName);
                    Stage stage = new Stage(stageTitle);
                    stage.setmId_Stage(idOfStage);
                    mStagesAL.add(stage);
                }
            }
            while (cursor.moveToNext());
            cursor.close();

            showStage();
        }
        else {
            Log.d(Constants.TAG, "Can\'t position on first string of cursor");
        }
    }

    public void showTask(String taskTitle, String startDate, String endDate) {
        mTextViewTitle.setText(taskTitle);
        mTextViewStartDate.setText(startDate);
        mTextViewEndDate.setText(endDate);
    }

    public void showStage() {
        boolean b = checkStages();
        if (b == true) {
            removeCurrentTask();
            Toast.makeText(this, "Task is finished", Toast.LENGTH_SHORT).show();
            finishThisActivity();
        }
        else {
            Stage stage = mStagesAL.get(FIRST_ELEMENT);
            String stageTitle = stage.getmSageName();
            mTextViewStageDescr.setText(stageTitle);
        }
    }

    public void removeCurrentTask() {
        mDatabase.execSQL("DELETE FROM " + MySQLiteOpenHelper.STAGES_TABLE_NAME + " WHERE "
                + MySQLiteOpenHelper.STAGE_ID_COLUMN + " = " + mTaskId + ";");
        mDatabase.execSQL("DELETE FROM " + MySQLiteOpenHelper.TASKS_TABLE_NAME + " WHERE "
                + MySQLiteOpenHelper.TASK_ID + " = " + mTaskId + ";");
        Log.d(Constants.TAG, "delete successful");
    }

    public void removeStage(int id_Stage) {
        mDatabase.execSQL("DELETE FROM " + MySQLiteOpenHelper.STAGES_TABLE_NAME + " WHERE "
                + MySQLiteOpenHelper.ID_STAGE + " = " + id_Stage + ";");
        mStagesAL.remove(FIRST_ELEMENT);
        for (int i = 0; i < mStagesAL.size(); i++) {
            Log.d(Constants.TAG, "Проверка размера коллекции");
        }
    }

    public boolean checkStages() {
        if (mStagesAL.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void removeCurrentStage() {
        Stage stage = mStagesAL.get(FIRST_ELEMENT);
        int id_stage = stage.getmId_Stage();
        removeStage(id_stage);
        showStage();
    }

    public void finishThisActivity() {
        Intent intent = new Intent(WatchOneTaskActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void btnViewTaskClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.btn_view_back :
                finishThisActivity();
                break;
            case R.id.btn_view_delete_task :
                removeCurrentTask();
                finishThisActivity();
                break;
            case R.id.btn_stage_completed :
                removeCurrentStage();
                break;
        }
    }
}
