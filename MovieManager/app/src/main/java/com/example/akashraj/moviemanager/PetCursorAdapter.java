package com.example.akashraj.moviemanager;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.akashraj.moviemanager.data.MovieContract;

/**
 * Created by prakhar on 19/2/17.
 */

public class PetCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link PetCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public PetCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textname = (TextView) view.findViewById(R.id.name);
        TextView textsummary = (TextView) view.findViewById(R.id.summary);
        TextView textproducer = (TextView) view.findViewById(R.id.producer);
        TextView textlength = (TextView) view.findViewById(R.id.runtime);
        TextView textid = (TextView) view.findViewById(R.id.id);

        int nameColumnIndex=cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_NAME);
        int idColumnIndex=cursor.getColumnIndex(MovieContract.MovieEntry._ID);
        int summaryColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_DIRECTOR_NAME);
        int ProducerColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_PRODUCER_NAME);
        int runtimeColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_RUN_TIME);
        // Extract properties from cursor
        String name = cursor.getString(nameColumnIndex);
        String summary = cursor.getString(summaryColumnIndex);
        String producer = cursor.getString(ProducerColumnIndex);
        String id = cursor.getString(idColumnIndex);
        int length = cursor.getInt(runtimeColumnIndex);
        String run = Integer.toString(length);
      //  String run = toString(length);
        // Populate fields with extracted properties
        textname.setText(name);
        textid.setText(id);
        textsummary.setText(summary);
        textproducer.setText(producer);
        textlength.setText(run);



//        int nameColumnIndex=cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_PET_NAME);
//        int summaryColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_PET_BREED);
//        // Extract properties from cursor
//        String name = cursor.getString(nameColumnIndex);
//        String summary = cursor.getString(summaryColumnIndex);
//        // Populate fields with extracted properties
//        textname.setText(name);
//        textsummary.setText(summary);
    }
}
