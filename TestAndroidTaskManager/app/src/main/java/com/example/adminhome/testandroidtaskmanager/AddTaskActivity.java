package com.example.adminhome.testandroidtaskmanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {

    private final static int SET_START_DATE = 123;

    private final static int SET_END_DATE = 321;

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

    /**
     * value of startDate from datePickerDialog in String
     */
    private String mStartDateSelected;

    /**
     * value of endDate from datePickerDialog in String
     */
    private String mEndDateSelected;

    /**
     * Alert Dialog
     */
    private AlertDialog mAlertDialog;

    /**
     * AlertDialogBuilder
     */
    private AlertDialog.Builder mAlertDialogBuilde;

    /**
     * variable show which date is setting now (startDate or endDate)
     */
    private int mWhatDateSet;


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

    /**
     * method listener of btnClick from activity_add_task buttons
     * @param view - View (Buttons activity_add_task)
     */
    public void bntClickAddTask(View view) {

        switch (view.getId()) {
            case R.id.btn_save_add_task :
                System.out.println("======" + mStartDateSelected);
                break;
            case R.id.btn_add_startDate :
                showDatePicker(SET_START_DATE);
                break;
            case R.id.btn_add_endDate :
                showDatePicker(SET_END_DATE);
                break;
        }
    }

    public void saveNewTask() {
        String taskTitle = mEditTextTitle.getText().toString();


    }

    public void writeNewTaskToDb() {

    }

    public void showDatePicker(int whatDateSet) {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, myDataListener,
                calendar.get(calendar.YEAR), calendar.get(calendar.MONTH),
                calendar.get(calendar.DAY_OF_MONTH));
        mWhatDateSet = whatDateSet;
        datePickerDialog.show();
    }

    private DatePickerDialog.OnDateSetListener myDataListener =
            new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            switch (mWhatDateSet) {
                case SET_START_DATE :
                    String date = dayOfMonth + "-" + month + "-" + year;
                    mStartDateSelected = date;
                    break;
                case SET_END_DATE :
                    break;
            }
        }
    };

}
