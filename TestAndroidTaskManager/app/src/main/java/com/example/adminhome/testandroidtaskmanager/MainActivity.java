package com.example.adminhome.testandroidtaskmanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final static int FIRST_CURRENT_TASK = 0;

    /**
     * button to create new Task (open new Activity)
     */
    private Button mButtonCreate;

    /**
     * button to delete selected Tasks
     */
    private Button mButtonDelete;

    /**
     * array adapter for ListView
     */
    private ArrayAdapter<Task> mArrayAdapter;

    /**
     * listView to show all task
     */
    private ListView mListView;

    /**
     * arrayList of Tasks (from Db)
     */
    private ArrayList<Task> mTasksAL;

    /**
     * MySQLiteOpenHelper for access to dataBase
     */
    public static MySQLiteOpenHelper mMySQLiteOpenHelper;

    /**
     * object for using dataBase
     */
    private SQLiteDatabase mDatabase;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMySQLiteOpenHelper = new MySQLiteOpenHelper(this);

        //initialization of view elements
        mButtonCreate = (Button) findViewById(R.id.btnCreateTask);
        mButtonDelete = (Button) findViewById(R.id.btnRemoveTasks);
        mListView = (ListView) findViewById(R.id.lvCurrentTasks);
        mTasksAL = new ArrayList<>();

        mDatabase = MainActivity.mMySQLiteOpenHelper.getWritableDatabase();

        loadTasksFromDb();

        final LayoutInflater inflater = this.getLayoutInflater();

        mArrayAdapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1,
                mTasksAL) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = inflater.inflate(R.layout.tasks_list_view_item, parent, false);
                TextView textView = (TextView) view.findViewById(R.id.tvCurrentTitle);
                final Task task = mTasksAL.get(position);
                textView.setText(task.getmTitle().toString());

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id = task.getmTask_id();
                        Intent intent = new Intent(MainActivity.this, WatchOneTaskActivity.class);
                        intent.putExtra("Task_id", id);
                        startActivity(intent);
                    }
                });

                return view;
            }
        };
        mListView.setAdapter(mArrayAdapter);
    }

    public void btnMainClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnCreateTask:
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
                break;
            case R.id.btnRemoveTasks:
                deleteSelectedTasks();
                break;
        }
    }

    public void loadTasksFromDb() {
        Cursor cursor = mDatabase.query(MySQLiteOpenHelper.TASKS_TABLE_NAME, null, null, null, null,
                null, null);

        if (cursor.moveToFirst()) {
            int indexId = cursor.getColumnIndex(MySQLiteOpenHelper.TASK_ID);
            int indexName = cursor.getColumnIndex(MySQLiteOpenHelper.TITLE_COLUMN);
            int indexStartDate = cursor.getColumnIndex(MySQLiteOpenHelper.START_DATE_COLUMN);
            int indexEndDate = cursor.getColumnIndex(MySQLiteOpenHelper.END_DATE_COLUMN);

            do {
                String taskName = cursor.getString(indexName);
                String startDate = cursor.getString(indexStartDate);
                String endDate = cursor.getString(indexEndDate);
                int id = cursor.getInt(indexId);
                Task task = new Task(taskName, startDate, endDate);
                task.setmTask_id(id);
                mTasksAL.add(task);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        else {
            Log.d(Constants.TAG, "Can\'t position on first string of cursor");
        }
    }

    public void deleteSelectedTasks() {
        for (int i = 0; i < mTasksAL.size(); i++) {
            View view = mListView.getChildAt(i);
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox_delete);
            if (checkBox.isChecked()) {
                Task task = mTasksAL.get(i);
                int id = task.getmTask_id();
                deleteTaskFromDataBase(id);
            }
        }
        for (int i = 0; i < mTasksAL.size(); i++) {
            mTasksAL.remove(i);
            mArrayAdapter.notifyDataSetChanged();
        }
        loadTasksFromDb();

    }

    public void deleteTaskFromDataBase(int id) {
        mDatabase.execSQL("DELETE FROM " + MySQLiteOpenHelper.STAGES_TABLE_NAME + " WHERE "
                + MySQLiteOpenHelper.STAGE_ID_COLUMN + " = " + id + ";");
        mDatabase.execSQL("DELETE FROM " + MySQLiteOpenHelper.TASKS_TABLE_NAME + " WHERE "
                + MySQLiteOpenHelper.TASK_ID + " = " + id + ";");
        Log.d(Constants.TAG, "delete successful");
    }
}
