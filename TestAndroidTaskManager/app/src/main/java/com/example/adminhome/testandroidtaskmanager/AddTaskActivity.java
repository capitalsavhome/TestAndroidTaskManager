package com.example.adminhome.testandroidtaskmanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
    private AlertDialog.Builder mAlertDialogBuilder;

    /**
     * variable show which date is setting now (startDate or endDate)
     */
    private int mWhatDateSet;

    /**
     * arrayList for current stages
     */
    private ArrayList<Stage> mStagesAL;

    /**
     * view for dialogView addStage;
     */
    private View mDialogView;

    /**
     * editText for Title of Stage, which shows in dialogView
     */
    private EditText mEditTextStageTitle;

    /**
     * adapter for ListView with tasks
     */
    private ArrayAdapter mArrayAdapter;

    /**
     * MySQLiteOpenHelper for access to dataBase
     */
    private MySQLiteOpenHelper mMySQLiteOpenHelper;




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

        final LayoutInflater inflater = this.getLayoutInflater();
        mDialogView = inflater.inflate(R.layout.dialog_maket, null, false);
        mEditTextStageTitle = (EditText) mDialogView.findViewById(R.id.edt_text_input_stage_name);

        mStagesAL = new ArrayList<>();

        //mMySQLiteOpenHelper = new MySQLiteOpenHelper(this);

        mArrayAdapter = new ArrayAdapter<Stage>(this, android.R.layout.simple_list_item_1,
                mStagesAL){
            @Override
            public  View getView(final int position, View convertView, ViewGroup parent) {

                View view = inflater.inflate(R.layout.activity_add_task, parent, false);
                //getting values from each holiday
                TextView textViewNumber = (TextView) view.findViewById(R.id.tv_num_stage_lv);
                TextView textViewName = (TextView) view.findViewById(R.id.tv_name_stage_lv);
                for (int i = 0; i < mStagesAL.size(); i++) {
                    textViewNumber.setText(Integer.toString(i + 1));
                    Stage stage = mStagesAL.get(i);
                    textViewName.setText(stage.getmSageName());
                }

                return view;
            }
        };
        mListViewStages.setAdapter(mArrayAdapter);

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
            case R.id.btn_add_stage :
                showAddStageDialog();
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

        Task task = new Task(taskTitle, mStartDateSelected, mEndDateSelected);
        task.setmStagesAL(mStagesAL);

        writeNewTaskToDb(task);


    }

    public void writeNewTaskToDb(Task task) {
        SQLiteDatabase database = mMySQLiteOpenHelper.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("task_title", task.getmTitle());
        row.put("start_date", task.getmStartDate());
        row.put("end_date", task.getmEndDate());
    }

    /**
     * method add new Stage to arrayList of Stages
     * @param stageName - String with name of Stage
     */
    public void addNewStage(String stageName) {
        Stage stage = new Stage(stageName);
        mStagesAL.add(stage);
    }

    /**
     * method show datePickerDialog with current date
     * @param whatDateSet - final value, which show is it datePickerDialog for startDate or for
     *                    end date
     */
    public void showDatePicker(int whatDateSet) {
        //get current date to Calendar
        Calendar calendar = Calendar.getInstance();

        //create datePickerDialog with current date from calendar
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, myDataListener,
                calendar.get(calendar.YEAR), calendar.get(calendar.MONTH),
                calendar.get(calendar.DAY_OF_MONTH));
        //set variable, which show which date is setting now (startDate or endDate)
        mWhatDateSet = whatDateSet;
        //show datePickerDialog
        datePickerDialog.show();
    }

    /**
     * Listener for DatePickerDialog
     */
    private DatePickerDialog.OnDateSetListener myDataListener =
            new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            //create calendar which contain date from DataPickerDialog
            Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
            //convert calendar to readable format
            String string = new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime());
            //make string of selected date to put it into data Base
            String dataBaseDate = dayOfMonth + "-" + month + "-" + year;
            switch (mWhatDateSet) {
                case SET_START_DATE :
                    mStartDateSelected = dataBaseDate;
                    mTextViewStartDate.setText(string);
                    break;
                case SET_END_DATE :
                    mEndDateSelected = dataBaseDate;
                    mTextViewEndDate.setText(string);
                    break;
            }
        }
    };

    public void showAddStageDialog() {
        //initialization of builder
        mAlertDialogBuilder = new AlertDialog.Builder(this);
        //set Title of builder
        mAlertDialogBuilder.setTitle(R.string.dialog_add_stage);
        //set view for dialogBuilder
        mAlertDialogBuilder.setView(mDialogView);
        //create dialog
        mAlertDialog = mAlertDialogBuilder.create();
        //show dialog
        mAlertDialog.show();

        //set positive Button onClickListener
        mAlertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //get String with Stage title from editText
                String stageName = mEditTextStageTitle.getText().toString();
                //call method addNewStage
                addNewStage(stageName);
                //notify changes for ListView
                mArrayAdapter.notifyDataSetChanged();
                ((ViewGroup) (mDialogView.getParent())).removeAllViews();
            }
        });

        //set Negative Button onClickListener
        mAlertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((ViewGroup) (mDialogView.getParent())).removeAllViews();
            }
        });
    }

}
