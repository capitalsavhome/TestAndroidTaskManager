package com.example.adminhome.testandroidtaskmanager;

import java.util.Calendar;

/**
 * Created by Admin on 03.10.2016.
 */

public class Stage {

    /**
     * name of this stage
     */
    private String mSageName;

    /**
     * id of Task which contain this stage
     */
    private int mTask_id;

    /**
     * id auto increment primary key in current stage
     */
    private int mId_Stage;

    private boolean mIsStageCompleted;

    public boolean ismIsStageCompleted() {
        return mIsStageCompleted;
    }

    public void setmIsStageCompleted(boolean mIsStageCompleted) {
        this.mIsStageCompleted = mIsStageCompleted;
    }

    public Stage(String mSageName) {
        this.mSageName = mSageName;
    }

    public String getmSageName() {
        return mSageName;
    }

    public void setmSageName(String mSageName) {
        this.mSageName = mSageName;
    }

    public int getmTask_id() {
        return mTask_id;
    }

    public void setmTask_id(int mTask_id) {
        this.mTask_id = mTask_id;
    }

    public int getmId_Stage() {
        return mId_Stage;
    }

    public void setmId_Stage(int mId_Stage) {
        this.mId_Stage = mId_Stage;
    }
}
