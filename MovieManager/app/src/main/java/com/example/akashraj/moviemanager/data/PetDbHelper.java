package com.example.akashraj.moviemanager.data;

/**
 * Created by prakhar on 22/3/17.
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.akashraj.moviemanager.data.PetContract.PetEntry;

/**
 * Created by prakhar on 28/12/16.
 */

public class PetDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = PetDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "MOVIE12345.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 7;

    /**
     * Constructs a new instance of {@link PetDbHelper}.
     *
     * @param context of the app
     */
    public PetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
//         Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_MOVIE_TABLE =  "CREATE TABLE " + PetEntry.TABLE_NAME + " ("
                + PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PetEntry.COLUMN_MOVIE_NAME + " TEXT NOT NULL, "
                + PetEntry.COLUMN_DIRECTOR_NAME + " TEXT NOT NULL, "
              //  + PetEntry.COLUMN_PRODUCER_NAME + " TEXT NOT NULL, "
                + PetEntry.COLUMN_RUN_TIME + " INTEGER NOT NULL DEFAULT 0);";

//        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + PetEntry.TABLE_NAME + " ("
//                + PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + PetEntry.COLUMN_PET_NAME + " TEXT NOT NULL, "
//                + PetEntry.COLUMN_PET_BREED + " TEXT, "
//                + PetEntry.COLUMN_PET_GENDER + " INTEGER NOT NULL, "
//                + PetEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement


//        db.execSQL(SQL_CREATE_PETS_TABLE);
        
//        String SQL_CREATE_MOVIE_TABLE =  "CREATE TABLE " + PetEntry.TABLE_NAME + " ("
//                + PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + PetEntry.COLUMN_MOVIE_NAME + " TEXT NOT NULL, "
//                + PetEntry.COLUMN_DIRECTOR_NAME + " TEXT, "
//                + PetEntry.COLUMN_PRODUCER_NAME + " TEXT, "
//                + PetEntry.COLUMN_RUN_TIME + " INTEGER NOT NULL DEFAULT 0);";
//
//        // Execute the SQL statement
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
        
//
//        String SQL_CREATE_THEATRE_TABLE =  "CREATE TABLE " + PetEntry.TABLE1_NAME + " ("
//                + PetEntry._ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + PetEntry.COLUMN_THEATRE_NAME + " TEXT NOT NULL, "
//                + PetEntry.COLUMN_ADDRESS + " TEXT NOT NULL, "
//                + PetEntry.COLUMN_NUM_OF_SCREENS + " INTEGER NOT NULL DEFAULT 0);";
//
//        // Execute the SQL statement
//        db.execSQL(SQL_CREATE_THEATRE_TABLE);
//
//        String SQL_CREATE_SHOWS_TABLE =  "CREATE TABLE " + PetEntry.TABLE2_NAME + " ("
//                + PetEntry._ID21 + " INTEGER, "
//                + PetEntry.COLUMN_MOVIE_NAME + " TEXT NOT NULL, "
//                + PetEntry._ID31 + " INTEGER, "
//                + PetEntry.COLUMN_THEATRE_NAME + " TEXT NOT NULL, "
//                + " FOREIGN KEY ("+PetEntry._ID31+","+PetEntry.COLUMN_THEATRE_NAME+") REFERENCES "+PetEntry.TABLE1_NAME+"("+PetEntry._ID1+","+PetEntry.COLUMN_THEATRE_NAME+")" + " ON DELETE CASCADE ON UPDATE CASCADE,"
//                + " FOREIGN KEY ("+PetEntry._ID21+","+PetEntry.COLUMN_MOVIE_NAME+") REFERENCES "+PetEntry.TABLE_NAME+"("+PetEntry._ID+","+PetEntry.COLUMN_MOVIE_NAME+")" + " ON DELETE CASCADE ON UPDATE CASCADE);";
   }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}
