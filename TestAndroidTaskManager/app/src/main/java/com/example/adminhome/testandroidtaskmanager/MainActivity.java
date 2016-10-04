package com.example.adminhome.testandroidtaskmanager;

import android.content.Intent;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialization of view elements
        mButtonCreate = (Button) findViewById(R.id.btnCreateTask);
        mButtonDelete = (Button) findViewById(R.id.btnRemoveTasks);
        mListView = (ListView) findViewById(R.id.lvCurrentTasks);
        mTasksAL = new ArrayList<>();

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

        //==========================================================================================
        ArrayList <Stage> stageArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Stage stage = new Stage("Stage " + i);
            stageArrayList.add(stage);
        }
        for (int i = 0; i < 10; i++) {
            Task task = new Task("Task " + i, "01.01.01", "01.01.01");
            task.setmStagesAL(stageArrayList);
            mTasksAL.add(task);
        }
        mArrayAdapter.notifyDataSetChanged();
        //==========================================================================================


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
}
