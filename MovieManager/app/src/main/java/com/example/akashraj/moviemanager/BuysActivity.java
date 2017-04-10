package com.example.akashraj.moviemanager;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.akashraj.moviemanager.data.MovieContract;

/**
 * Created by prakhar on 4/4/17.
 */

public class BuysActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    /**
     * Identifier for the movie data loader
     */
    private static final int MOVIE_LOADER = 0;

    /**
     * Adapter for the ListView
     */
    BuysCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open MovieEditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuysActivity.this, BuysEditorActivity.class);
                startActivity(intent);
            }
        });

        // Find the ListView which will be populated with the movie data
        ListView movieListView = (ListView) findViewById(R.id.list_view_movie);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        View emptyView = findViewById(R.id.empty_view);
        movieListView.setEmptyView(emptyView);

        // Setup an Adapter to create a list item for each row of movie data in the Cursor.
        // There is no movie data yet (until the loader finishes) so pass in null for the Cursor.
        mCursorAdapter = new BuysCursorAdapter(this, null);
        movieListView.setAdapter(mCursorAdapter);

        // Setup the item click listener
        movieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Create new intent to go to {@link MovieEditorActivity}
                Intent intent = new Intent(BuysActivity.this, BuysEditorActivity.class);

                // Form the content URI that represents the specific movie that was clicked on,
                // by appending the "id" (passed as input to this method) onto the
                // {@link MovieEntry#CONTENT_URI}.
                // For example, the URI would be "content://com.example.android.movies/movies/2"
                // if the movie with ID 2 was clicked on.

                Uri currentMovieUri = ContentUris.withAppendedId(MovieContract.MovieEntry.CONTENT_URI5, id);

                // Set the URI on the data field of the intent
                intent.setData(currentMovieUri);

                // Launch the {@link MovieEditorActivity} to display the data for the current movie.
                startActivity(intent);
            }
        });

        // Kick off the loader
        getLoaderManager().initLoader(MOVIE_LOADER, null, this);
    }

    /**
     * Helper method to insert hardcoded movie data into the database. For debugging purposes only.
     * //
     */
//    private void insertmovie() {
//        // Create a ContentValues object where column names are the keys,
//        // and Toto's movie attributes are the values.
//        ContentValues values = new ContentValues();
//        values.put(MovieContract.MovieEntry.COLUMN_MOVIE_NAME, "3 idots");
//        values.put(MovieContract.MovieEntry.COLUMN_DIRECTOR_NAME, "Rajkumar Hirani");
//        values.put(MovieContract.MovieEntry.COLUMN_PRODUCER_NAME,"UTV");
//        values.put(MovieContract.MovieEntry.COLUMN_RUN_TIME, 120);
//
//        // Insert a new row for Toto into the provider using the ContentResolver.
//        // Use the {@link MovieEntry#CONTENT_URI} to indicate that we want to insert
//        // into the movies database table.
//        // Receive the new content URI that will allow us to access Toto's data in the future.
//        Uri newUri = getContentResolver().insert(MovieEntry.CONTENT_URI, values);
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option

            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                deleteAllMovies();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Define a projection that specifies the columns from the table we care about.F
        String[] projection = {
                MovieContract.MovieEntry._ID3,
                MovieContract.MovieEntry._IDCUSTOMER,
                MovieContract.MovieEntry._IDtheatres,
                MovieContract.MovieEntry._IDMOVIES,
                MovieContract.MovieEntry._IDshows};

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                MovieContract.MovieEntry.CONTENT_URI5,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Update {@link MovieCursorAdapter} with this new cursor containing updated movie data
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Callback called when the data needs to be deleted
        mCursorAdapter.swapCursor(null);
    }

    private void deleteAllMovies() {
        int rowsDeleted = getContentResolver().delete(MovieContract.MovieEntry.CONTENT_URI5, null, null);
        Log.v("BuysActivity", rowsDeleted + " rows deleted from movie database");
    }
}
