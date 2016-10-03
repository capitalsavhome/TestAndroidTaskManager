package com.example.adminhome.testandroidtaskmanager;

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
}
