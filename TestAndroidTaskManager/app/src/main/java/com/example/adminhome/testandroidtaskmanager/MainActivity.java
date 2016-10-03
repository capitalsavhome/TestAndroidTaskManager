package com.example.adminhome.testandroidtaskmanager;

import android.content.Intent;
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

    private Button mButtonCreate;

    private ArrayList<String> AL = new ArrayList<>();

    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonCreate = (Button) findViewById(R.id.btnCreateTask);

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
