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

import com.example.akashraj.moviemanager.data.MovieContract;

/**
 * Created by prakhar on 1/4/17.
 */

public class ShowsEditorActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {


    private boolean mMovieHasChanged = false;


    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mMovieHasChanged = true;
            return false;
        }
    };

    /**
     * Identifier for the movie data loader
     */
    private static final int EXISTING_MOVIE_LOADER = 0;

    /**
     * Content URI for the existing movie (null if it's a new movie)
     */
    private Uri mCurrentMovieUri;

    /**
     * EditText field to enter the movie's name
     */

    private EditText mID1EditText;/**
     * EditText field to enter the movie's length
     */
    private EditText mID2EditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_shows);

        // Examine the intent that was used to launch this activity,
        // in order to figure out if we're creating a new movie or editing an existing one.
        Intent intent = getIntent();
        mCurrentMovieUri = intent.getData();

        // If the intent DOES NOT contain a movie content URI, then we know that we are
        // creating a new movie.
        if (mCurrentMovieUri == null) {
            // This is a new movie, so change the app bar to say "Add a movie"
            setTitle("Add show");
        } else {
            // Otherwise this is an existing movie, so change app bar to say "Edit movie"
            setTitle("Edit show");

            // Initialize a loader to read the movie data from the database
            // and display the current values in the editor
            getLoaderManager().initLoader(EXISTING_MOVIE_LOADER, null, this);
        }

        // Find all relevant views that we will need to read user input from
        mID1EditText = (EditText) findViewById(R.id.edit_ID_movie);
        mID2EditText = (EditText) findViewById(R.id.edit_id_theatre);
        
        mID1EditText.setOnTouchListener(mTouchListener);
        mID2EditText.setOnTouchListener(mTouchListener);

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

        String id2 = mID2EditText.getText().toString().trim();
        String id1 = mID1EditText.getText().toString().trim();
        // Read from input fields
        // Use trim to eliminate leading or trailing white space

        // Create database helper
//        MovieDbHelper mDbHelper = new MovieDbHelper(this);
//
//        // Gets the database in write mode
//        SQLiteDatabase db = mDbHelper.getWritableDatabase();
//
//        // Create a ContentValues object where column names are the keys,
//        // and movie attributes from the editor are the values.
//        ContentValues values = new ContentValues();
//        values.put(MovieContract.MovieEntry._ID21, nameString);
//        values.put(MovieContract.MovieEntry.COLUMN_ADDRESS, addressString);
//        values.put(MovieContract.MovieEntry._ID31, ScreensString);
//
//
//        // Insert a new row for movie in the database, returning the ID of that new row.
//        long newRowId = db.insert(MovieContract.MovieEntry.TABLE2_NAME, null, values);
//
//        // Show a toast message depending on whether or not the insertion was successful
//        if (newRowId == -1) {
//            // If the row ID is -1, then there was an error with insertion.
//            Toast.makeText(this, "Error with saving movie", Toast.LENGTH_SHORT).show();
//        } else {
//            // Otherwise, the insertion was successful and we can display a toast with the row ID.
//            Toast.makeText(this, "movie saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
//        }


        if (mCurrentMovieUri == null &&
                TextUtils.isEmpty(id1) &&
                TextUtils.isEmpty(id2)) {
            return;
        }


        // and movie attributes from the editor are the values.
        ContentValues values = new ContentValues();
        
        int length1 = 0;
        if (!TextUtils.isEmpty(id1)) {
            length1 = Integer.parseInt(id1);
        }
        values.put(MovieContract.MovieEntry._ID21,length1);

        int length = 0;
        if (!TextUtils.isEmpty(id2)) {
            length = Integer.parseInt(id2);
        }
        values.put(MovieContract.MovieEntry._ID31, length);

        // Determine if this is a new or existing movie by checking if mCurrentMovieUri is null or not
        if (mCurrentMovieUri == null) {
            // This is a NEW movie, so insert a new movie into the provider,
            // returning the content URI for the new movie.
            Uri newUri = getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI3, values);

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
                    NavUtils.navigateUpFromSameTask(ShowsEditorActivity.this);
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
                                NavUtils.navigateUpFromSameTask(ShowsEditorActivity.this);
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
                MovieContract.MovieEntry._ID2,
                MovieContract.MovieEntry._ID21,
                MovieContract.MovieEntry._ID31};

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
            int lengthColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry._ID21);
            int idColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry._ID31);

            // Extract out the value from the Cursor for the given column index
            int id1 = cursor.getInt(lengthColumnIndex);
            int id2 = cursor.getInt(idColumnIndex)
                    ;
            // Update the views on the screen with the values from the database
            mID1EditText.setText(Integer.toString(id1));
            mID2EditText.setText(Integer.toString(id2));

            // Gender is a dropdown spinner, so map the constant value from the database
            // into one of the dropdown options (0 is Unknown, 1 is Male, 2 is Female).
            // Then call setSelection() so that option is displayed on screen as the current selection.

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // If the loader is invalidated, clear out all the data from the input fields.
        mID2EditText.setText("");
        mID1EditText.setText("");
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
}
