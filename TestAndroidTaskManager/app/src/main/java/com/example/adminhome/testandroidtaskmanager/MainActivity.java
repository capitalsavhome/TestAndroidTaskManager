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
                        Intent intent = new Intent(MainActivity.this, WatchOneTaskActivity.class);
                        int taskId = 0;
                        //TODO code for getting id of current selected Task
                        intent.putExtra("Task_id", taskId);
                        startActivity(intent);
                    }
                });

                return view;
            }
        };


//        AL = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            String string = "Test " + Integer.toString(i);
//            AL.add(string);
//        }
//
//        final LayoutInflater inflater = this.getLayoutInflater();
//
//        ListView listView = (ListView) findViewById(R.id.lvCurrentTasks);
//
//        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, AL){
//            @Override
//            public  View getView(final int position, View convertView, ViewGroup parent) {
//
//                View view = inflater.inflate(R.layout.tasks_list_view_item, parent, false);
//                //getting values from each holiday
//                TextView textView = (TextView) view.findViewById(R.id.tvCurrentTitle);
//                String string = AL.get(position);
//                //System.out.println("==========" + string);
//                textView.setText(string);
//
//                view.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(MainActivity.this, "Test" + position, Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                return view;
//            }
//        };
//        listView.setAdapter(arrayAdapter);
////        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                Toast.makeText(MainActivity.this, "Test ", Toast.LENGTH_SHORT).show();
////                System.out.println("=====Test " + i);
////            }
////        });
    }

    public void btnMainClick(View view) {
        Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
        startActivity(intent);
    }

    public void loadTasksFromDb() {
        Cursor cursor = mDatabase.query(MySQLiteOpenHelper.TASKS_TABLE_NAME, null, null, null, null,
                null, MySQLiteOpenHelper.TITLE_COLUMN);

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
                mArrayAdapter.notifyDataSetChanged();
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        else {
            Log.d(Constants.TAG, "Can\'t position on first string of cursor");
        }
    }
}
