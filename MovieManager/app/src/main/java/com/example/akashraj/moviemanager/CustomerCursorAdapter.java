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
 * Created by prakhar on 4/4/17.
 */

public class CustomerCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link MovieCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public CustomerCursorAdapter(Context context, Cursor c) {
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
     * This method binds the movie data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current movie can be set on the name TextView
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
        TextView textid = (TextView) view.findViewById((R.id.id));

        int nameColumnIndex=cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_CUSTOMER_NAME);
        int summaryColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_CUSTOMER_ADDRESS);
        int ProducerColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_CUSTOMER_PHONE);
        int idColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry._ID4);
        // Extract properties from cursor
        String name = cursor.getString(nameColumnIndex);
        String summary = cursor.getString(summaryColumnIndex);
        String id = cursor.getString(idColumnIndex);
        int length = cursor.getInt(ProducerColumnIndex);
        String run = Integer.toString(length);
        //  String run = toString(length);
        // Populate fields with extracted properties
        textname.setText(name);
        textsummary.setText(summary);
        textproducer.setText(run);
        textid.setText(id);



//        int nameColumnIndex=cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_movie_NAME);
//        int summaryColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_movie_BREED);
//        // Extract properties from cursor
//        String name = cursor.getString(nameColumnIndex);
//        String summary = cursor.getString(summaryColumnIndex);
//        // Populate fields with extracted properties
//        textname.setText(name);
//        textsummary.setText(summary);
    }
}

