/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.akashraj.moviemanager;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//import com.example.akashraj.moviemanager.data.MovieContract.MovieEntry;
import com.example.akashraj.moviemanager.data.MovieContract.MovieEntry;

/**
 * Allows user to create a new movie or edit an existing one.
 */
public class EditorActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {


    private boolean mMovieHasChanged = false;


    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                        mMovieHasChanged = true;
                        return false;
                    }
            };

    /** Identifier for the movie data loader */
    private static final int EXISTING_MOVIE_LOADER = 0;

    /** Content URI for the existing movie (null if it's a new movie) */
    private Uri mCurrentMovieUri;

    /** EditText field to enter the movie's name */
    private EditText mNameEditText;

    /** EditText field to enter the movie's director */
    private EditText mDirectorEditText;

    private EditText mProducerEditText;

    /** EditText field to enter the movie's length */
    private EditText mlengthEditText;
    private EditText meditmovieidText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp);

        // Examine the intent that was used to launch this activity,
        // in order to figure out if we're creating a new movie or editing an existing one.
        Intent intent = getIntent();
        mCurrentMovieUri = intent.getData();

        // If the intent DOES NOT contain a movie content URI, then we know that we are
        // creating a new movie.
        if (mCurrentMovieUri == null) {
            // This is a new movie, so change the app bar to say "Add a movie"
            setTitle(getString(R.string.editor_activity_title_new_movie));
        } else {
            // Otherwise this is an existing movie, so change app bar to say "Edit movie"
            setTitle(getString(R.string.editor_activity_title_edit_movie));

            // Initialize a loader to read the movie data from the database
            // and display the current values in the editor
            getLoaderManager().initLoader(EXISTING_MOVIE_LOADER, null, this);
        }

        // Find all relevant views that we will need to read user input from
        mNameEditText = (EditText) findViewById(R.id.edit_movie_name);
        mDirectorEditText = (EditText) findViewById(R.id.edit_movie_director);
        mProducerEditText = (EditText) findViewById(R.id.edit_movie_producer);
        mlengthEditText = (EditText) findViewById(R.id.edit_movie_time);
        meditmovieidText = (EditText) findViewById(R.id.edit_movie_ID);

        mNameEditText.setOnTouchListener(mTouchListener);
        mDirectorEditText.setOnTouchListener(mTouchListener);
        mProducerEditText.setOnTouchListener(mTouchListener);
        mlengthEditText.setOnTouchListener(mTouchListener);
        meditmovieidText.setOnTouchListener(mTouchListener);

    }

    /**
     * Setup the dropdown spinner that allows the user to select the gender of the movie.
     */
//    private void setupSpinner() {
//        // Create adapter for spinner. The list options are from the String array it will use
//        // the spinner will use the default layout
//        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
//                R.array.array_gender_options, android.R.layout.simple_spinner_item);
//
//        // Specify dropdown layout style - simple list view with 1 item per line
//        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//
//        // Apply the adapter to the spinner
//        mGenderSpinner.setAdapter(genderSpinnerAdapter);
//
//        // Set the integer mSelected to the constant values
//        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selection = (String) parent.getItemAtPosition(position);
//                if (!TextUtils.isEmpty(selection)) {
//                    if (selection.equals(getString(R.string.gender_male))) {
//                        mGender = MovieEntry.GENDER_MALE;
//                    } else if (selection.equals(getString(R.string.gender_female))) {
//                        mGender = MovieEntry.GENDER_FEMALE;
//                    } else {
//                        mGender = MovieEntry.GENDER_UNKNOWN;
//                    }
//                }
//            }
//
//            // Because AdapterView is an abstract class, onNothingSelected must be defined
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                mGender = MovieEntry.GENDER_UNKNOWN;
//            }
//        });
//    }

    /**
     * Get user input from editor and save movie into database.
     */
    private void saveMovie() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = mNameEditText.getText().toString().trim();
        String directorString = mDirectorEditText.getText().toString().trim();
        String producerString = mProducerEditText.getText().toString().trim();
        String lengthString = mlengthEditText.getText().toString().trim();
        String idString = meditmovieidText.getText().toString().trim();

        // Create a ContentValues object where column names are the keys,
        if (mCurrentMovieUri == null &&
                TextUtils.isEmpty(nameString) && TextUtils.isEmpty(directorString) && TextUtils.isEmpty(producerString) &&
                TextUtils.isEmpty(lengthString)) {return;}


        // and movie attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(MovieEntry._ID,idString);
        values.put(MovieEntry.COLUMN_MOVIE_NAME, nameString);
        values.put(MovieEntry.COLUMN_DIRECTOR_NAME,directorString);
        values.put(MovieEntry.COLUMN_PRODUCER_NAME,producerString);

        int length = 0;
        if (!TextUtils.isEmpty(lengthString)) {
            length = Integer.parseInt(lengthString);
        }
        values.put(MovieEntry.COLUMN_RUN_TIME, length);

        // Determine if this is a new or existing movie by checking if mCurrentMovieUri is null or not
        if (mCurrentMovieUri == null) {
            // This is a NEW movie, so insert a new movie into the provider,
            // returning the content URI for the new movie.
            Uri newUri = getContentResolver().insert(MovieEntry.CONTENT_URI, values);

            // Show a toast message depending on whether or not the insertion was successful.
            if (newUri == null) {
                // If the new content URI is null, then there was an error with insertion.
                Toast.makeText(this, getString(R.string.editor_insert_movie_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the insertion was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.editor_insert_movie_successful),
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            // Otherwise this is an EXISTING movie, so update the movie with content URI: mCurrentMovieUri
            // and pass in the new ContentValues. Pass in null for the selection and selection args
            // because mCurrentMovieUri will already identify the correct row in the database that
            // we want to modify.
            int rowsAffected = getContentResolver().update(mCurrentMovieUri, values, null, null);

            // Show a toast message depending on whether or not the update was successful.
            if (rowsAffected == 0) {
                // If no rows were affected, then there was an error with the update.
                Toast.makeText(this, getString(R.string.editor_update_movie_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the update was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.editor_update_movie_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save movie to database
                saveMovie();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                // If the movie hasn't changed, continue with navigating up to parent activity
// which is the {@link CatalogActivity}.
                if (!mMovieHasChanged) {
                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    return true;
                }

// Otherwise if there are unsaved changes, setup a dialog to warn the user.
// Create a click listener to handle the user confirming that
// changes should be discarded.
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button, navigate to parent activity.
                                NavUtils.navigateUpFromSameTask(EditorActivity.this);
                            }
                        };

// Show a dialog that notifies the user they have unsaved changes
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // If the movie hasn't changed, continue with handling back button press
        if (!mMovieHasChanged) {
            super.onBackPressed();
            return;
        }

        // Otherwise if there are unsaved changes, setup a dialog to warn the user.
        // Create a click listener to handle the user confirming that changes should be discarded.
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };

        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Since the editor shows all movie attributes, define a projection that contains
        // all columns from the movie table
        String[] projection = {
                MovieEntry._ID,
                MovieEntry.COLUMN_MOVIE_NAME,
                MovieEntry.COLUMN_PRODUCER_NAME,
                MovieEntry.COLUMN_DIRECTOR_NAME,
                MovieEntry.COLUMN_RUN_TIME};

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                mCurrentMovieUri,         // Query the content URI for the current movie
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Bail early if the cursor is null or there is less than 1 row in the cursor
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        // Proceed with moving to the first row of the cursor and reading data from it
        // (This should be the only row in the cursor)
        if (cursor.moveToFirst()) {
            // Find the columns of movie attributes that we're interested in
            int nameColumnIndex = cursor.getColumnIndex(MovieEntry.COLUMN_MOVIE_NAME);
            int idColumnIndex = cursor.getColumnIndex(MovieEntry._ID);
            int directorColumnIndex = cursor.getColumnIndex(MovieEntry.COLUMN_DIRECTOR_NAME);
            int producerColumnIndex = cursor.getColumnIndex(MovieEntry.COLUMN_PRODUCER_NAME);
            int lengthColumnIndex = cursor.getColumnIndex(MovieEntry.COLUMN_RUN_TIME);

            // Extract out the value from the Cursor for the given column index
            String name = cursor.getString(nameColumnIndex);
            String director = cursor.getString(directorColumnIndex);
            String producer = cursor.getString(producerColumnIndex);
            int length = cursor.getInt(lengthColumnIndex);
            String id = cursor.getString(idColumnIndex);

            // Update the views on the screen with the values from the database
            mNameEditText.setText(name);
            meditmovieidText.setText(id);
            mDirectorEditText.setText(director);
            mProducerEditText.setText(producer);
            mlengthEditText.setText(Integer.toString(length));

            // Gender is a dropdown spinner, so map the constant value from the database
            // into one of the dropdown options (0 is Unknown, 1 is Male, 2 is Female).
            // Then call setSelection() so that option is displayed on screen as the current selection.

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // If the loader is invalidated, clear out all the data from the input fields.
        mNameEditText.setText("");
        meditmovieidText.setText("");
        mDirectorEditText.setText("");
        mProducerEditText.setText("");
        mlengthEditText.setText("");
    }
    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the movie.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showDeleteConfirmationDialog() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the movie.
                deleteMovie();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
                // and continue editing the movie.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Perform the deletion of the movie in the database.
     */
    private void deleteMovie() {
        // Only perform the delete if this is an existing movie.
        if (mCurrentMovieUri != null) {
            // Call the ContentResolver to delete the movie at the given content URI.
            // Pass in null for the selection and selection args because the mCurrentMovieUri
            // content URI already identifies the movie that we want.
            int rowsDeleted = getContentResolver().delete(mCurrentMovieUri, null, null);

            // Show a toast message depending on whether or not the delete was successful.
            if (rowsDeleted == 0) {
                // If no rows were deleted, then there was an error with the delete.
                Toast.makeText(this, getString(R.string.editor_delete_movie_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the delete was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.editor_delete_movie_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }

        // Close the activity
        finish();
    }












//private boolean mMovieHasChanged = false;
//
//
//    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            mMovieHasChanged = true;
//            return false;
//        }
//    };
//
//    /** Identifier for the movie data loader */
//    private static final int EXISTING_MOVIE_LOADER = 0;
//
//    /** Content URI for the existing movie (null if it's a new movie) */
//    private Uri mCurrentMovieUri;
//
//    /** EditText field to enter the movie's name */
//    private EditText mNameEditText;
//
//    /** EditText field to enter the movie's breed */
//    private EditText mBreedEditText;
//
//    /** EditText field to enter the movie's weight */
//    private EditText mWeightEditText;
//
//    /** EditText field to enter the movie's gender */
//    private Spinner mGenderSpinner;
//
////    /**
////     * Gender of the movie. The possible valid values are in the MovieContract.java file:
////     * {@link MovieEntry#GENDER_UNKNOWN}, {@link MovieEntry#GENDER_MALE}, or
////     * {@link MovieEntry#GENDER_FEMALE}.
////     */
////    private int mGender = MovieEntry.GENDER_UNKNOWN;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.temp);
//
//        // Examine the intent that was used to launch this activity,
//        // in order to figure out if we're creating a new movie or editing an existing one.
//        Intent intent = getIntent();
//        mCurrentMovieUri = intent.getData();
//
//        // If the intent DOES NOT contain a movie content URI, then we know that we are
//        // creating a new movie.
//        if (mCurrentMovieUri == null) {
//            // This is a new movie, so change the app bar to say "Add a movie"
//            setTitle(getString(R.string.editor_activity_title_new_movie));
//        } else {
//            // Otherwise this is an existing movie, so change app bar to say "Edit movie"
//            setTitle(getString(R.string.editor_activity_title_edit_movie));
//
//            // Initialize a loader to read the movie data from the database
//            // and display the current values in the editor
//            getLoaderManager().initLoader(EXISTING_MOVIE_LOADER, null, this);
//        }
//
//        // Find all relevant views that we will need to read user input from
//        mNameEditText = (EditText) findViewById(R.id.edit_movie_name);
//        mBreedEditText = (EditText) findViewById(R.id.edit_movie_director);
//        mWeightEditText = (EditText) findViewById(R.id.edit_movie_time);
//    //    mGenderSpinner = (Spinner) findViewById(R.id.spinner_gender);
//
//        mNameEditText.setOnTouchListener(mTouchListener);
//        mBreedEditText.setOnTouchListener(mTouchListener);
//        mWeightEditText.setOnTouchListener(mTouchListener);
//      //  mGenderSpinner.setOnTouchListener(mTouchListener);
//
//      //  setupSpinner();
//    }
//
//    /**
//     * Setup the dropdown spinner that allows the user to select the gender of the movie.
//     */
////    private void setupSpinner() {
////        // Create adapter for spinner. The list options are from the String array it will use
////        // the spinner will use the default layout
////        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
////                R.array.array_gender_options, android.R.layout.simple_spinner_item);
////
////        // Specify dropdown layout style - simple list view with 1 item per line
////        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
////
////        // Apply the adapter to the spinner
////        mGenderSpinner.setAdapter(genderSpinnerAdapter);
////
////        // Set the integer mSelected to the constant values
////        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////            @Override
////            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                String selection = (String) parent.getItemAtPosition(position);
////                if (!TextUtils.isEmpty(selection)) {
////                    if (selection.equals(getString(R.string.gender_male))) {
////                        mGender = MovieEntry.GENDER_MALE;
////                    } else if (selection.equals(getString(R.string.gender_female))) {
////                        mGender = MovieEntry.GENDER_FEMALE;
////                    } else {
////                        mGender = MovieEntry.GENDER_UNKNOWN;
////                    }
////                }
////            }
////
////            // Because AdapterView is an abstract class, onNothingSelected must be defined
////            @Override
////            public void onNothingSelected(AdapterView<?> parent) {
////                mGender = MovieEntry.GENDER_UNKNOWN;
////            }
////        });
////    }
//
//    /**
//     * Get user input from editor and save movie into database.
//     */
//    private void saveMovie() {
//        // Read from input fields
//        // Use trim to eliminate leading or trailing white space
//        String nameString = mNameEditText.getText().toString().trim();
//        String breedString = mBreedEditText.getText().toString().trim();
//        String weightString = mWeightEditText.getText().toString().trim();
//
//        // Create a ContentValues object where column names are the keys,
//        if (mCurrentMovieUri == null &&
//                TextUtils.isEmpty(nameString) && TextUtils.isEmpty(breedString) &&
//                TextUtils.isEmpty(weightString)) {return;}
//
//
//        // and movie attributes from the editor are the values.
//        ContentValues values = new ContentValues();
//        values.put(MovieEntry.COLUMN_MOVIE_NAME, nameString);
//        values.put(MovieContract.MovieEntry.COLUMN_DIRECTOR_NAME, breedString);
//     //   values.put(MovieEntry.COLUMN_movie_GENDER, mGender);
//
//        int weight = 0;
//        if (!TextUtils.isEmpty(weightString)) {
//            weight = Integer.parseInt(weightString);
//        }
//        values.put(MovieContract.MovieEntry.COLUMN_RUN_TIME, weight);
//
//        // Determine if this is a new or existing movie by checking if mCurrentMovieUri is null or not
//        if (mCurrentMovieUri == null) {
//            // This is a NEW movie, so insert a new movie into the provider,
//            // returning the content URI for the new movie.
//            Uri newUri = getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, values);
//
//            // Show a toast message depending on whether or not the insertion was successful.
//            if (newUri == null) {
//                // If the new content URI is null, then there was an error with insertion.
//                Toast.makeText(this, getString(R.string.editor_insert_movie_failed),
//                        Toast.LENGTH_SHORT).show();
//            } else {
//                // Otherwise, the insertion was successful and we can display a toast.
//                Toast.makeText(this, getString(R.string.editor_insert_movie_successful),
//                        Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            // Otherwise this is an EXISTING movie, so update the movie with content URI: mCurrentMovieUri
//            // and pass in the new ContentValues. Pass in null for the selection and selection args
//            // because mCurrentMovieUri will already identify the correct row in the database that
//            // we want to modify.
//            int rowsAffected = getContentResolver().update(mCurrentMovieUri, values, null, null);
//
//            // Show a toast message depending on whether or not the update was successful.
//            if (rowsAffected == 0) {
//                // If no rows were affected, then there was an error with the update.
//                Toast.makeText(this, getString(R.string.editor_update_movie_failed),
//                        Toast.LENGTH_SHORT).show();
//            } else {
//                // Otherwise, the update was successful and we can display a toast.
//                Toast.makeText(this, getString(R.string.editor_update_movie_successful),
//                        Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu options from the res/menu/menu_editor.xml file.
//        // This adds menu items to the app bar.
//        getMenuInflater().inflate(R.menu.menu_editor, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // User clicked on a menu option in the app bar overflow menu
//        switch (item.getItemId()) {
//            // Respond to a click on the "Save" menu option
//            case R.id.action_save:
//                // Save movie to database
//                saveMovie();
//                // Exit activity
//                finish();
//                return true;
//            // Respond to a click on the "Delete" menu option
//            case R.id.action_delete:
//                showDeleteConfirmationDialog();
//                return true;
//            // Respond to a click on the "Up" arrow button in the app bar
//            case android.R.id.home:
//                // Navigate back to parent activity (CatalogActivity)
//                // If the movie hasn't changed, continue with navigating up to parent activity
//// which is the {@link CatalogActivity}.
//                if (!mMovieHasChanged) {
//                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
//                    return true;
//                }
//
//// Otherwise if there are unsaved changes, setup a dialog to warn the user.
//// Create a click listener to handle the user confirming that
//// changes should be discarded.
//                DialogInterface.OnClickListener discardButtonClickListener =
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                // User clicked "Discard" button, navigate to parent activity.
//                                NavUtils.navigateUpFromSameTask(EditorActivity.this);
//                            }
//                        };
//
//// Show a dialog that notifies the user they have unsaved changes
//                showUnsavedChangesDialog(discardButtonClickListener);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onBackPressed() {
//        // If the movie hasn't changed, continue with handling back button press
//        if (!mMovieHasChanged) {
//            super.onBackPressed();
//            return;
//        }
//
//        // Otherwise if there are unsaved changes, setup a dialog to warn the user.
//        // Create a click listener to handle the user confirming that changes should be discarded.
//        DialogInterface.OnClickListener discardButtonClickListener =
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        // User clicked "Discard" button, close the current activity.
//                        finish();
//                    }
//                };
//
//        // Show dialog that there are unsaved changes
//        showUnsavedChangesDialog(discardButtonClickListener);
//    }
//
//    @Override
//    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
//        // Since the editor shows all movie attributes, define a projection that contains
//        // all columns from the movie table
//        String[] projection = {
//                MovieEntry._ID,
//                MovieContract.MovieEntry.COLUMN_MOVIE_NAME,
//                MovieContract.MovieEntry.COLUMN_DIRECTOR_NAME,
//            //    MovieEntry.COLUMN_movie_GENDER,
//                MovieEntry.COLUMN_RUN_TIME };
//
//        // This loader will execute the ContentProvider's query method on a background thread
//        return new CursorLoader(this,   // Parent activity context
//                mCurrentMovieUri,         // Query the content URI for the current movie
//                projection,             // Columns to include in the resulting Cursor
//                null,                   // No selection clause
//                null,                   // No selection arguments
//                null);                  // Default sort order
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
//        // Bail early if the cursor is null or there is less than 1 row in the cursor
//        if (cursor == null || cursor.getCount() < 1) {
//            return;
//        }
//
//        // Proceed with moving to the first row of the cursor and reading data from it
//        // (This should be the only row in the cursor)
//        if (cursor.moveToFirst()) {
//            // Find the columns of movie attributes that we're interested in
//            int nameColumnIndex = cursor.getColumnIndex(MovieEntry.COLUMN_MOVIE_NAME);
//            int breedColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_DIRECTOR_NAME);
//     //       int genderColumnIndex = cursor.getColumnIndex(MovieEntry.COLUMN_movie_GENDER);
//            int weightColumnIndex = cursor.getColumnIndex(MovieEntry.COLUMN_RUN_TIME);
//
//            // Extract out the value from the Cursor for the given column index
//            String name = cursor.getString(nameColumnIndex);
//            String breed = cursor.getString(breedColumnIndex);
//    //        int gender = cursor.getInt(genderColumnIndex);
//            int weight = cursor.getInt(weightColumnIndex);
//
//            // Update the views on the screen with the values from the database
//            mNameEditText.setText(name);
//            mBreedEditText.setText(breed);
//            mWeightEditText.setText(Integer.toString(weight));
//
//            // Gender is a dropdown spinner, so map the constant value from the database
//            // into one of the dropdown options (0 is Unknown, 1 is Male, 2 is Female).
//            // Then call setSelection() so that option is displayed on screen as the current selection.
////            switch (gender) {
////                case MovieEntry.GENDER_MALE:
////                    mGenderSpinner.setSelection(1);
////                    break;
////                case MovieEntry.GENDER_FEMALE:
////                    mGenderSpinner.setSelection(2);
////                    break;
////                default:
////                    mGenderSpinner.setSelection(0);
////                    break;
////            }
//        }
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//        // If the loader is invalidated, clear out all the data from the input fields.
//        mNameEditText.setText("");
//        mBreedEditText.setText("");
//        mWeightEditText.setText("");
//        mGenderSpinner.setSelection(0); // Select "Unknown" gender
//    }
//    private void showUnsavedChangesDialog(
//            DialogInterface.OnClickListener discardButtonClickListener) {
//        // Create an AlertDialog.Builder and set the message, and click listeners
//        // for the postivie and negative buttons on the dialog.
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage(R.string.unsaved_changes_dialog_msg);
//        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
//        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                // User clicked the "Keep editing" button, so dismiss the dialog
//                // and continue editing the movie.
//                if (dialog != null) {
//                    dialog.dismiss();
//                }
//            }
//        });
//
//        // Create and show the AlertDialog
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
//
//    private void showDeleteConfirmationDialog() {
//        // Create an AlertDialog.Builder and set the message, and click listeners
//        // for the postivie and negative buttons on the dialog.
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage(R.string.delete_dialog_msg);
//        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                // User clicked the "Delete" button, so delete the movie.
//                deleteMovie();
//            }
//        });
//        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                // User clicked the "Cancel" button, so dismiss the dialog
//                // and continue editing the movie.
//                if (dialog != null) {
//                    dialog.dismiss();
//                }
//            }
//        });
//
//        // Create and show the AlertDialog
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
//
//    /**
//     * Perform the deletion of the movie in the database.
//     */
//    private void deleteMovie() {
//        // Only perform the delete if this is an existing movie.
//        if (mCurrentMovieUri != null) {
//            // Call the ContentResolver to delete the movie at the given content URI.
//            // Pass in null for the selection and selection args because the mCurrentMovieUri
//            // content URI already identifies the movie that we want.
//            int rowsDeleted = getContentResolver().delete(mCurrentMovieUri, null, null);
//
//            // Show a toast message depending on whether or not the delete was successful.
//            if (rowsDeleted == 0) {
//                // If no rows were deleted, then there was an error with the delete.
//                Toast.makeText(this, "Failed",
//                        Toast.LENGTH_SHORT).show();
//            } else {
//                // Otherwise, the delete was successful and we can display a toast.
//                Toast.makeText(this, "successful",
//                        Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        // Close the activity
//        finish();
//    }
}