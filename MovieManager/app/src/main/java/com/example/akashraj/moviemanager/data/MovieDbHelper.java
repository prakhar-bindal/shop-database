package com.example.akashraj.moviemanager.data;

/**
 * Created by prakhar on 22/3/17.
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.akashraj.moviemanager.data.MovieContract.MovieEntry;

/**
 * Created by prakhar on 28/12/16.
 */

public class MovieDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = MovieDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "MOVIE1234567890112345678910123.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 18;

    /**
     * Constructs a new instance of {@link MovieDbHelper}.
     *
     * @param context of the app
     */
    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     *
     */

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//         Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_MOVIE_TABLE =  "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " ("
                + MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY NOT NULL, "
                + MovieEntry.COLUMN_MOVIE_NAME + " TEXT NOT NULL, "
                + MovieContract.MovieEntry.COLUMN_DIRECTOR_NAME + " TEXT NOT NULL, "
                + MovieContract.MovieEntry.COLUMN_PRODUCER_NAME + " TEXT NOT NULL, "
                + MovieContract.MovieEntry.COLUMN_RUN_TIME + " INTEGER NOT NULL DEFAULT 0);";

//        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + MovieEntry.TABLE_NAME + " ("
//                + MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + MovieEntry.COLUMN_PET_NAME + " TEXT NOT NULL, "
//                + MovieEntry.COLUMN_PET_BREED + " TEXT, "
//                + MovieEntry.COLUMN_PET_GENDER + " INTEGER NOT NULL, "
//                + MovieEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement


//        db.execSQL(SQL_CREATE_PETS_TABLE);
        
//        String SQL_CREATE_MOVIE_TABLE =  "CREATE TABLE " + MovieEntry.TABLE_NAME + " ("
//                + MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + MovieEntry.COLUMN_MOVIE_NAME + " TEXT NOT NULL, "
//                + MovieEntry.COLUMN_DIRECTOR_NAME + " TEXT, "
//                + MovieEntry.COLUMN_PRODUCER_NAME + " TEXT, "
//                + MovieEntry.COLUMN_RUN_TIME + " INTEGER NOT NULL DEFAULT 0);";
//
//        // Execute the SQL statement
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
        
//
        String SQL_CREATE_THEATRE_TABLE =  "CREATE TABLE " + MovieEntry.TABLE1_NAME + " ("
                + MovieEntry._ID1 + " INTEGER PRIMARY KEY NOT NULL, "
                + MovieEntry.COLUMN_THEATRE_NAME + " TEXT NOT NULL, "
                + MovieEntry.COLUMN_ADDRESS + " TEXT NOT NULL, "
                + MovieEntry.COLUMN_NUM_OF_SCREENS + " INTEGER NOT NULL DEFAULT 0);";
//
//        // Execute the SQL statement
        db.execSQL(SQL_CREATE_THEATRE_TABLE);
//
        String SQL_CREATE_SHOWS_TABLE =  "CREATE TABLE " + MovieEntry.TABLE2_NAME + " ("
                + MovieEntry._ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MovieEntry._ID21 + " INTEGER, "
                + MovieEntry._ID31 + " INTEGER, "
                + " FOREIGN KEY ("+MovieEntry._ID21+") REFERENCES "+MovieEntry.TABLE_NAME+"("+MovieEntry._ID+")" + " ON DELETE CASCADE ON UPDATE CASCADE ,"
                + " FOREIGN KEY ("+MovieEntry._ID31+") REFERENCES "+MovieEntry.TABLE1_NAME+"("+MovieEntry._ID1+")" + " ON DELETE CASCADE ON UPDATE CASCADE);";

        db.execSQL(SQL_CREATE_SHOWS_TABLE);
   }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}
