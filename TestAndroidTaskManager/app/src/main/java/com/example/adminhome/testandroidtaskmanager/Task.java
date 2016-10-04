package com.example.adminhome.testandroidtaskmanager;

import java.util.ArrayList;

/**
 * Created by Admin on 03.10.2016.
 */

public class Task {

    /**
     * title of task
     */
    private String mTitle;

    /**
     * start date of task in string format
     */
    private String mStartDate;

    /**
     * end date of task in string format
     */
    private String mEndDate;

    private ArrayList<Stage> mStagesAL = new ArrayList<>();

    public Task(String mTitle, String mStartDate, String mEndDate) {
        this.mTitle = mTitle;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmStartDate() {
        return mStartDate;
    }

    public void setmStartDate(String mStartDate) {
        this.mStartDate = mStartDate;
    }

    public String getmEndDate() {
        return mEndDate;
    }

    public void setmEndDate(String mEndDate) {
        this.mEndDate = mEndDate;
    }

    public Stage getStage(int index) {
        return this.mStagesAL.get(index);
    }

    public void setmStagesAL(ArrayList<Stage> arrayList) {
        for(int i = 0; i < arrayList.size(); i++) {
            Stage stage = arrayList.get(i);
            mStagesAL.add(stage);
        }
    }
}
