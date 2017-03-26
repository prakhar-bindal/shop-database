package com.example.akashraj.moviemanager.data;

/**
 * Created by prakhar on 22/3/17.
 */


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.akashraj.moviemanager.data.PetContract.PetEntry;

/**
 * Created by prakhar on 19/2/17.
 */

public class PetProvider extends ContentProvider {

    /** Tag for the log messages */
//    public static final String LOG_TAG = PetProvider.class.getSimpleName();
//
//    /** URI matcher code for the content URI for the MOVIEs table */
//    private static final int PETS = 100;
//
//    /** URI matcher code for the content URI for a single MOVIE in the MOVIEs table */
//    private static final int PET_ID = 101;
//
//    /**
//     * UriMatcher object to match a content URI to a corresponding code.
//     * The input passed into the constructor represents the code to return for the root URI.
//     * It's common to use NO_MATCH as the input for this case.
//     */
//    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
//
//    // Static initializer. This is run the first time anything is called from this class.
//    static {
//        // The calls to addURI() go here, for all of the content URI patterns that the provider
//        // should recognize. All paths added to the UriMatcher have a corresponding code to return
//        // when a match is found.
//
//        // The content URI of the form "content://com.example.android.MOVIEs/MOVIEs" will map to the
//        // integer code {@link #MOVIES}. This URI is used to provide access to MULTIPLE rows
//        // of the MOVIEs table.
//        sUriMatcher.addURI(PetContract.CONTENT_AUTHORITY, PetContract.PATH_PETS, PETS);
//
//        // The content URI of the form "content://com.example.android.MOVIEs/MOVIEs/#" will map to the
//        // integer code {@link #MOVIE_ID}. This URI is used to provide access to ONE single row
//        // of the MOVIEs table.
//        //
//        // In this case, the "#" wildcard is used where "#" can be substituted for an integer.
//        // For example, "content://com.example.android.MOVIEs/MOVIEs/3" matches, but
//        // "content://com.example.android.MOVIEs/MOVIEs" (without a number at the end) doesn't match.
//        sUriMatcher.addURI(PetContract.CONTENT_AUTHORITY, PetContract.PATH_PETS + "/#", PET_ID);
//    }
//
//    /** Database helper object */
//    private PetDbHelper mDbHelper;
//
//    @Override
//    public boolean onCreate() {
//        mDbHelper = new PetDbHelper(getContext());
//        return true;
//    }
//
//    @Override
//    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
//                        String sortOrder) {
//        // Get readable database
//        SQLiteDatabase database = mDbHelper.getReadableDatabase();
//
//        // This cursor will hold the result of the query
//        Cursor cursor;
//
//        // Figure out if the URI matcher can match the URI to a specific code
//        int match = sUriMatcher.match(uri);
//        switch (match) {
//            case PETS:
//                // For the MOVIES code, query the MOVIEs table directly with the given
//                // projection, selection, selection arguments, and sort order. The cursor
//                // could contain multiple rows of the MOVIEs table.
//                cursor = database.query(PetContract.MovieEntry.TABLE_NAME, projection, selection, selectionArgs,
//                        null, null, sortOrder);
//                break;
//            case PET_ID:
//                // For the MOVIE_ID code, extract out the ID from the URI.
//                // For an example URI such as "content://com.example.android.MOVIEs/MOVIEs/3",
//                // the selection will be "_id=?" and the selection argument will be a
//                // String array containing the actual ID of 3 in this case.
//                //
//                // For every "?" in the selection, we need to have an element in the selection
//                // arguments that will fill in the "?". Since we have 1 question mark in the
//                // selection, we have 1 String in the selection arguments' String array.
//                selection = MovieEntry._ID + "=?";
//                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
//
//                // This will perform a query on the MOVIEs table where the _id equals 3 to return a
//                // Cursor containing that row of the table.
//                cursor = database.query(MovieEntry.TABLE_NAME, projection, selection, selectionArgs,
//                        null, null, sortOrder);
//                break;
//            default:
//                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
//        }
//
//        // Set notification URI on the Cursor,
//        // so we know what content URI the Cursor was created for.
//        // If the data at this URI changes, then we know we need to update the Cursor.
//        cursor.setNotificationUri(getContext().getContentResolver(), uri);
//
//        // Return the cursor
//        return cursor;
//    }
//
//    @Override
//    public Uri insert(Uri uri, ContentValues contentValues) {
//        final int match = sUriMatcher.match(uri);
//        switch (match) {
//            case PETS:
//                return insertmovie(uri, contentValues);
//            default:
//                throw new IllegalArgumentException("Insertion is not supported for " + uri);
//        }
//    }
//
//    /**
//     * Insert a MOVIE into the database with the given content values. Return the new content URI
//     * for that specific row in the database.
//     */
//    private Uri insertmovie(Uri uri, ContentValues values) {
//        // Check that the name is not null
//        String name1 = values.getAsString(MovieEntry.COLUMN_MOVIE_NAME);
//        if (name1 == null) {
//            throw new IllegalArgumentException("movie requires a name");
//        }
//
//        String director = values.getAsString(MovieEntry.COLUMN_DIRECTOR_NAME);
//        if (director == null) {
//            throw new IllegalArgumentException("Director requires a name");
//        }
//
//        String producer = values.getAsString(MovieEntry.COLUMN_PRODUCER_NAME);
//        if (producer == null) {
//            throw new IllegalArgumentException("Producer requires a name");
//        }
//
//        // Check that the gender is valid
//
//        // If the weight is provided, check that it's greater than or equal to 0 kg
//        Integer runtime = values.getAsInteger(MovieEntry.COLUMN_RUN_TIME);
//        if (runtime != null && runtime < 0) {
//            throw new IllegalArgumentException("MOVIE requires valid weight");
//        }
//
//        // No need to check the breed, any value is valid (including null).
//
//        // Get writeable database
//        SQLiteDatabase database = mDbHelper.getWritableDatabase();
//
//        // Insert the new MOVIE with the given values
//        long id = database.insert(PetContract.MovieEntry.TABLE_NAME, null, values);
//        // If the ID is -1, then the insertion failed. Log an error and return null.
//        if (id == -1) {
//            Log.e(LOG_TAG, "Failed to insert row for " + uri);
//            return null;
//        }
//
//        // Notify all listeners that the data has changed for the MOVIE content URI
//        getContext().getContentResolver().notifyChange(uri, null);
//
//        // Return the new URI with the ID (of the newly inserted row) appended at the end
//        return ContentUris.withAppendedId(uri, id);
//    }
//
//    @Override
//    public int update(Uri uri, ContentValues contentValues, String selection,
//                      String[] selectionArgs) {
//        final int match = sUriMatcher.match(uri);
//        switch (match) {
//            case PETS:
//                return updateMOVIE(uri, contentValues, selection, selectionArgs);
//            case PET_ID:
//                // For the MOVIE_ID code, extract out the ID from the URI,
//                // so we know which row to update. Selection will be "_id=?" and selection
//                // arguments will be a String array containing the actual ID.
//                selection = PetContract.MovieEntry._ID + "=?";
//                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
//                return updateMOVIE(uri, contentValues, selection, selectionArgs);
//            default:
//                throw new IllegalArgumentException("Update is not supported for " + uri);
//        }
//    }
//
//    /**
//     * Update MOVIEs in the database with the given content values. Apply the changes to the rows
//     * specified in the selection and selection arguments (which could be 0 or 1 or more MOVIEs).
//     * Return the number of rows that were successfully updated.
//     */
//    private int updateMOVIE(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
//        // If the {@link MovieEntry#COLUMN_MOVIE_NAME} key is present,
//        // check that the name value is not null.
//        if (values.containsKey(PetContract.MovieEntry.COLUMN_MOVIE_NAME)) {
//            String name1 = values.getAsString(MovieEntry.COLUMN_MOVIE_NAME);
//            if (name1 == null) {
//                throw new IllegalArgumentException("MOVIE requires a name");
//            }
//        }
//
//        // If the {@link MovieEntry#COLUMN_MOVIE_GENDER} key is present,
//        // check that the gender value is valid.
////        if (values.containsKey(MovieEntry.COLUMN_MOVIE_GENDER)) {
////            Integer gender = values.getAsInteger(MovieEntry.COLUMN_MOVIE_GENDER);
////            if (gender == null || !PetContract.MovieEntry.isValidGender(gender)) {
////                throw new IllegalArgumentException("MOVIE requires valid gender");
////            }
////        }
//
//        // If the {@link MovieEntry#COLUMN_MOVIE_WEIGHT} key is present,
//        // check that the weight value is valid.
//        if (values.containsKey(MovieEntry.COLUMN_RUN_TIME)) {
//            // Check that the weight is greater than or equal to 0 kg
//            Integer weight = values.getAsInteger(MovieEntry.COLUMN_RUN_TIME);
//            if (weight != null && weight < 0) {
//                throw new IllegalArgumentException("MOVIE requires valid weight");
//            }
//        }
//
//        // No need to check the breed, any value is valid (including null).
//
//        // If there are no values to update, then don't try to update the database
//        if (values.size() == 0) {
//            return 0;
//        }
//
//        // Otherwise, get writeable database to update the data
//        SQLiteDatabase database = mDbHelper.getWritableDatabase();
//
//        // Perform the update on the database and get the number of rows affected
//        int rowsUpdated = database.update(MovieEntry.TABLE_NAME, values, selection, selectionArgs);
//
//        // If 1 or more rows were updated, then notify all listeners that the data at the
//        // given URI has changed
//        if (rowsUpdated != 0) {
//            getContext().getContentResolver().notifyChange(uri, null);
//        }
//
//        // Return the number of rows updated
//        return rowsUpdated;
//    }
//
//    @Override
//    public int delete(Uri uri, String selection, String[] selectionArgs) {
//        // Get writeable database
//        SQLiteDatabase database = mDbHelper.getWritableDatabase();
//
//        // Track the number of rows that were deleted
//        int rowsDeleted;
//
//        final int match = sUriMatcher.match(uri);
//        switch (match) {
//            case PETS:
//                // Delete all rows that match the selection and selection args
//                rowsDeleted = database.delete(MovieEntry.TABLE_NAME, selection, selectionArgs);
//                break;
//            case PET_ID:
//                // Delete a single row given by the ID in the URI
//                selection = PetContract.MovieEntry._ID + "=?";
//                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
//                rowsDeleted = database.delete(MovieEntry.TABLE_NAME, selection, selectionArgs);
//                break;
//            default:
//                throw new IllegalArgumentException("Deletion is not supported for " + uri);
//        }
//
//        // If 1 or more rows were deleted, then notify all listeners that the data at the
//        // given URI has changed
//        if (rowsDeleted != 0) {
//            getContext().getContentResolver().notifyChange(uri, null);
//        }
//
//        // Return the number of rows deleted
//        return rowsDeleted;
//    }
//
//    @Override
//    public String getType(Uri uri) {
//        final int match = sUriMatcher.match(uri);
//        switch (match) {
//            case PETS:
//                return PetContract.MovieEntry.CONTENT_LIST_TYPE;
//            case PET_ID:
//                return PetContract.MovieEntry.CONTENT_ITEM_TYPE;
//            default:
//                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
//        }
//    }



    public static final String LOG_TAG = PetProvider.class.getSimpleName();

    /**
     * URI matcher code for the content URI for the pets table
     */
    private static final int PETS = 100;

    /**
     * URI matcher code for the content URI for a single pet in the pets table
     */
    private static final int PET_ID = 101;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        // The content URI of the form "content://com.example.android.pets/pets" will map to the
        // integer code {@link #PETS}. This URI is used to provide access to MULTIPLE rows
        // of the pets table.
        sUriMatcher.addURI(PetContract.CONTENT_AUTHORITY, PetContract.PATH_PETS, PETS);

        // The content URI of the form "content://com.example.android.pets/pets/#" will map to the
        // integer code {@link #PET_ID}. This URI is used to provide access to ONE single row
        // of the pets table.
        //
        // In this case, the "#" wildcard is used where "#" can be substituted for an integer.
        // For example, "content://com.example.android.pets/pets/3" matches, but
        // "content://com.example.android.pets/pets" (without a number at the end) doesn't match.
        sUriMatcher.addURI(PetContract.CONTENT_AUTHORITY, PetContract.PATH_PETS + "/#", PET_ID);
    }

    /**
     * Database helper object
     */
    private PetDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new PetDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        // Get readable database
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);
        switch (match) {
            case PETS:
                // For the PETS code, query the pets table directly with the given
                // projection, selection, selection arguments, and sort order. The cursor
                // could contain multiple rows of the pets table.
                cursor = database.query(PetContract.PetEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case PET_ID:
                // For the PET_ID code, extract out the ID from the URI.
                // For an example URI such as "content://com.example.android.pets/pets/3",
                // the selection will be "_id=?" and the selection argument will be a
                // String array containing the actual ID of 3 in this case.
                //
                // For every "?" in the selection, we need to have an element in the selection
                // arguments that will fill in the "?". Since we have 1 question mark in the
                // selection, we have 1 String in the selection arguments' String array.
                selection = PetContract.PetEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                // This will perform a query on the pets table where the _id equals 3 to return a
                // Cursor containing that row of the table.
                cursor = database.query(PetContract.PetEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        // Set notification URI on the Cursor,
        // so we know what content URI the Cursor was created for.
        // If the data at this URI changes, then we know we need to update the Cursor.
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        // Return the cursor
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PETS:
                return insertPet(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    /**
     * Insert a pet into the database with the given content values. Return the new content URI
     * for that specific row in the database.
     */
    private Uri insertPet(Uri uri, ContentValues values) {
        // Check that the name is not null
        String name = values.getAsString(PetContract.PetEntry.COLUMN_MOVIE_NAME);
        if (name == null) {
            throw new IllegalArgumentException("Pet requires a name");
        }

        // Check that the gender is valid
//        Integer gender = values.getAsInteger(PetContract.PetEntry.COLUMN_PET_GENDER);
//        if (gender == null || !PetContract.PetEntry.isValidGender(gender)) {
//            throw new IllegalArgumentException("Pet requires valid gender");
//        }

        // If the weight is provided, check that it's greater than or equal to 0 kg
        Integer weight = values.getAsInteger(PetContract.PetEntry.COLUMN_RUN_TIME);
        if (weight != null && weight < 0) {
            throw new IllegalArgumentException("Pet requires valid weight");
        }

        // No need to check the breed, any value is valid (including null).

        // Get writeable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Insert the new pet with the given values
        long id = database.insert(PetContract.PetEntry.TABLE_NAME, null, values);
        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Notify all listeners that the data has changed for the pet content URI
        getContext().getContentResolver().notifyChange(uri, null);

        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PETS:
                return updatePet(uri, contentValues, selection, selectionArgs);
            case PET_ID:
                // For the PET_ID code, extract out the ID from the URI,
                // so we know which row to update. Selection will be "_id=?" and selection
                // arguments will be a String array containing the actual ID.
                selection = PetContract.PetEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updatePet(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    /**
     * Update pets in the database with the given content values. Apply the changes to the rows
     * specified in the selection and selection arguments (which could be 0 or 1 or more pets).
     * Return the number of rows that were successfully updated.
     */
    private int updatePet(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // If the {@link PetEntry#COLUMN_MOVIE_NAME} key is present,
        // check that the name value is not null.
        if (values.containsKey(PetContract.PetEntry.COLUMN_MOVIE_NAME)) {
            String name = values.getAsString(PetContract.PetEntry.COLUMN_MOVIE_NAME);
            if (name == null) {
                throw new IllegalArgumentException("Pet requires a name");
            }
        }

        // If the {@link PetEntry#COLUMN_PET_GENDER} key is present,
        // check that the gender value is valid.
//        if (values.containsKey(PetContract.PetEntry.COLUMN_PET_GENDER)) {
//            Integer gender = values.getAsInteger(PetContract.PetEntry.COLUMN_PET_GENDER);
//            if (gender == null || !PetContract.PetEntry.isValidGender(gender)) {
//                throw new IllegalArgumentException("Pet requires valid gender");
//            }
//        }

        // If the {@link PetEntry#COLUMN_RUN_TIME} key is present,
        // check that the weight value is valid.
        if (values.containsKey(PetContract.PetEntry.COLUMN_RUN_TIME)) {
            // Check that the weight is greater than or equal to 0 kg
            Integer weight = values.getAsInteger(PetContract.PetEntry.COLUMN_RUN_TIME);
            if (weight != null && weight < 0) {
                throw new IllegalArgumentException("Pet requires valid weight");
            }
        }

        // No need to check the breed, any value is valid (including null).

        // If there are no values to update, then don't try to update the database
        if (values.size() == 0) {
            return 0;
        }

        // Otherwise, get writeable database to update the data
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Perform the update on the database and get the number of rows affected
        int rowsUpdated = database.update(PetContract.PetEntry.TABLE_NAME, values, selection, selectionArgs);

        // If 1 or more rows were updated, then notify all listeners that the data at the
        // given URI has changed
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows updated
        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Get writeable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Track the number of rows that were deleted
        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PETS:
                // Delete all rows that match the selection and selection args
                rowsDeleted = database.delete(PetContract.PetEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case PET_ID:
                // Delete a single row given by the ID in the URI
                selection = PetContract.PetEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(PetContract.PetEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        // If 1 or more rows were deleted, then notify all listeners that the data at the
        // given URI has changed
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows deleted
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PETS:
                return PetContract.PetEntry.CONTENT_LIST_TYPE;
            case PET_ID:
                return PetContract.PetEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }
}