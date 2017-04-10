package com.example.akashraj.moviemanager.data;

/**
 * Created by prakhar on 22/3/17.
 */


import android.net.Uri;
import android.provider.BaseColumns;
import android.content.ContentResolver;

/**
 * Created by prakhar on 25/12/16.
 */

public final class MovieContract {

    private MovieContract(){}

    public static final String CONTENT_AUTHORITY = "com.example.akashraj.moviemanager";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.movies/movies/ is a valid path for
     * looking at movie data. content://com.example.android.movies/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_MOVIES = "movies";
    public static final String PATH_THEATRES = "theatres";
    public static final String PATH_SHOWS = "shows";
    public static final String PATH_CUSTOMER = "customer";
    public static final String PATH_BUYS = "buys";

    public static abstract class MovieEntry implements BaseColumns {

//        public static boolean isValidGender(int gender) {
//            if (gender == GENDER_UNKNOWN || gender == GENDER_MALE || gender == GENDER_FEMALE) {
//                return true;
//            }
//            return false;
//        }

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_MOVIES);
        public static final Uri CONTENT_URI2 = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_THEATRES);
        public static final Uri CONTENT_URI3 = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_SHOWS);
        public static final Uri CONTENT_URI4 = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CUSTOMER);
        public static final Uri CONTENT_URI5 = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BUYS);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;

        public static final String CONTENT_LIST_TYPE2 =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_THEATRES;
        public static final String CONTENT_LIST_TYPE3 =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SHOWS;

        public static final String CONTENT_LIST_TYPE4 =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CUSTOMER;
        public static final String CONTENT_LIST_TYPE5 =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BUYS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single movie.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;
        public static final String CONTENT_ITEM_TYPE2 =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_THEATRES;
        public static final String CONTENT_ITEM_TYPE3 =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SHOWS;
        public static final String CONTENT_ITEM_TYPE4 =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CUSTOMER;
        public static final String CONTENT_ITEM_TYPE5 =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BUYS;

        public static final String TABLE_NAME="movie";
        public static final String _ID=BaseColumns._ID;
        public static final String COLUMN_MOVIE_NAME="name";
        public static final String COLUMN_DIRECTOR_NAME="Director";
        public static final String COLUMN_PRODUCER_NAME="producer";
        public static final String COLUMN_RUN_TIME="length";



        public static final String TABLE1_NAME="theatres";
        public static final String _ID1=BaseColumns._ID;
        public static final String COLUMN_THEATRE_NAME="name";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_NUM_OF_SCREENS = "screens";
//
        public static final String TABLE2_NAME="shows";
        public static final String _ID2=BaseColumns._ID;
        public static final String _ID21="id_from_movie";
        public static final String _ID31="id_from_theatres";

        public static final String TABLE3_NAME="buys";
        public static final String _ID3=BaseColumns._ID;
        public static final String _IDshows="id_from_shows";
        public static final String _IDtheatres="id_from_theatres";
        public static final String _IDMOVIES="id_from_movie";
        public static final String _IDCUSTOMER="id_from_customer";

        public static final String TABLE4_NAME="customer";
        public static final String _ID4=BaseColumns._ID;
        public static final String COLUMN_CUSTOMER_NAME="customer_name";
        public static final String COLUMN_CUSTOMER_ADDRESS="customer_address";
        public static final String COLUMN_CUSTOMER_PHONE="phone_number";










    }
}
