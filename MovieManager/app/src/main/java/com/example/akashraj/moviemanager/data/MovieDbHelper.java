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
    private static final String DATABASE_NAME = "MOVIEMANAGER3.db";

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
//         Create a String that contains the SQL statement to create the movies table
        String SQL_CREATE_MOVIE_TABLE =  "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " ("
                + MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY NOT NULL, "
                + MovieEntry.COLUMN_MOVIE_NAME + " TEXT NOT NULL, "
                + MovieContract.MovieEntry.COLUMN_DIRECTOR_NAME + " TEXT NOT NULL, "
                + MovieContract.MovieEntry.COLUMN_PRODUCER_NAME + " TEXT NOT NULL, "
                + MovieContract.MovieEntry.COLUMN_RUN_TIME + " INTEGER NOT NULL DEFAULT 0);";

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

        String SQL_CREATE_CUSTOMER_TABLE =  "CREATE TABLE " + MovieEntry.TABLE4_NAME + " ("
                + MovieEntry._ID4 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MovieEntry.COLUMN_CUSTOMER_NAME + " TEXT NOT NULL, "
                + MovieEntry.COLUMN_CUSTOMER_ADDRESS + " TEXT NOT NULL, "
                + MovieEntry.COLUMN_CUSTOMER_PHONE + " INTEGER NOT NULL DEFAULT 0);";
//
//        // Execute the SQL statement
        db.execSQL(SQL_CREATE_CUSTOMER_TABLE);

        String SQL_CREATE_BUYS_TABLE =  "CREATE TABLE " + MovieEntry.TABLE3_NAME + " ("
                + MovieEntry._ID3 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MovieEntry._IDshows + " INTEGER, "
                + MovieEntry._IDtheatres + " INTEGER, "
                + MovieEntry._IDCUSTOMER + " INTEGER, "
                + MovieEntry._IDMOVIES + " INTEGER, "
                + " FOREIGN KEY ("+MovieEntry._IDshows+") REFERENCES "+MovieEntry.TABLE2_NAME+"("+MovieEntry._ID2+")" + " ON DELETE CASCADE ON UPDATE CASCADE ,"
                + " FOREIGN KEY ("+MovieEntry._IDMOVIES+") REFERENCES "+MovieEntry.TABLE_NAME+"("+MovieEntry._ID+")" + " ON DELETE CASCADE ON UPDATE CASCADE ,"
                + " FOREIGN KEY ("+MovieEntry._IDCUSTOMER+") REFERENCES "+MovieEntry.TABLE4_NAME+"("+MovieEntry._ID4+")" + " ON DELETE CASCADE ON UPDATE CASCADE ,"
                + " FOREIGN KEY ("+MovieEntry._IDtheatres+") REFERENCES "+MovieEntry.TABLE1_NAME+"("+MovieEntry._ID1+")" + " ON DELETE CASCADE ON UPDATE CASCADE);";

        db.execSQL(SQL_CREATE_BUYS_TABLE);

    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}
