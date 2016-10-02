package com.example.adminhome.testandroidtaskmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class AddTaskActivity extends AppCompatActivity {

    /**
     * editText for input Title of task
     */
    private EditText mEditTextTitle;

    /**
     * listView of Stages
     */
    private ListView mListViewStages;

    /**
     * button to Add new Stage of Task
     */
    private Button mButtonAddStage;

    /**
     * Textview for start Date of Task
     */
    private TextView mTextViewStartDate;

    /**
     * Button to set start date of task
     */
    private Button mButtonStartDate;

    /**
     * textView for end Date of Task
     */
    private TextView mTextViewEndDate;

    /**
     * Button to set End Date of Task
     */
    private Button mButtonEndDate;

    /**
     * Button to save new Task
     */
    private Button mButtonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        //initialization of View elements from activity_add_task
        mEditTextTitle = (EditText) findViewById(R.id.edit_text_add_task);
        mListViewStages = (ListView) findViewById(R.id.lv_stages_add_task);
        mButtonAddStage = (Button) findViewById(R.id.btn_add_stage);
        mTextViewStartDate = (TextView) findViewById(R.id.tv_startDate_add_task);
        mButtonStartDate = (Button) findViewById(R.id.btn_add_startDate);
        mTextViewEndDate = (TextView) findViewById(R.id.tv_endDate_add_task);
        mButtonEndDate = (Button) findViewById(R.id.btn_add_endDate);
        mButtonSave = (Button) findViewById(R.id.btn_save_add_task);

    }
}
