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

public class BuysCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link PetCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public BuysCursorAdapter(Context context, Cursor c) {
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
        return LayoutInflater.from(context).inflate(R.layout.list_item_buys, parent, false);
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
        TextView textid1 = (TextView) view.findViewById(R.id.id1);
        TextView textid2 = (TextView) view.findViewById(R.id.id2);
        TextView textid = (TextView) view.findViewById(R.id.id);
        TextView textid3 = (TextView) view.findViewById(R.id.id3);
        TextView textid4 = (TextView) view.findViewById(R.id.id4);

        int idColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry._ID3);
        int moviesColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry._IDMOVIES);
        int theatreColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry._IDtheatres);
        int customerColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry._IDCUSTOMER);
        int showsColumnIndex = cursor.getColumnIndex(MovieContract.MovieEntry._IDshows);
        // Extract properties from cursor

        int length = cursor.getInt(idColumnIndex);
        String id1b= Integer.toString(length);

        int length1 = cursor.getInt(moviesColumnIndex);
        String id2b = Integer.toString(length1);

        int prime = cursor.getInt((theatreColumnIndex));
        String id3b = Integer.toString(prime);

        int length2 = cursor.getInt(customerColumnIndex);
        String id4b = Integer.toString(length2);

        int length3 = cursor.getInt(showsColumnIndex);
        String id5b = Integer.toString(length3);
        //  String run = toString(length);
        // Populate fields with extracted properties
        textid.setText(id1b);
        textid1.setText(id2b);
        textid2.setText(id3b);
        textid3.setText(id4b);
        textid4.setText(id5b);





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

