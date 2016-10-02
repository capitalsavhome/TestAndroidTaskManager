package com.example.adminhome.testandroidtaskmanager;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Capitalsav on 01.10.2016.
 * Class for work with SQLite Data Base
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper implements BaseColumns{

    /**
     * constant with name of data base
     */
    public static final String DATABASE_NAME = "my_task_database.db";

    /**
     * constant with version of data base
     */
    public static final int DATABASE_VERSION = 1;

    /**
     * constant with name of table
     */
    public static final String TABLE_NAME = "tasks_table";

    /**
     * constant with name of column which contains title of task
     */
    public static final String TITLE_COLUMN = "task_title";

    /**
     * constant with name of column which contains startDate of task
     */
    public static final String START_DATE_COLUMN = "start_date";

    /**
     * constant with name of column which contains endDate of task
     */
    public static final String END_DATE_COLUMN = "end_date";

    /**
     * constant with name of column which is FOREIGN KEY and contains primary key of another table
     */
    public static final String STAGE_ID_COLUMN = "stage_id";

    public static final String DATABASE_CREATE_SCRIPT = "CREATE TABLE "
            + TABLE_NAME + " (" + BaseColumns._ID
            + " integer primary key autoincrement, " + TITLE_COLUMN
            + " text not null, " + START_DATE_COLUMN + " text, " + END_DATE_COLUMN
            + " text);";

    public MySQLiteOpenHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                              int version) {
        super(context, name, factory, version);
    }

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                              int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(Constants.TAG, "Update DB from version = " + i + " to version = " + i1);
    }
}
