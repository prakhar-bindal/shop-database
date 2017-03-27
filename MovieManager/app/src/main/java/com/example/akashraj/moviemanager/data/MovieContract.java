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
     * For instance, content://com.example.android.pets/pets/ is a valid path for
     * looking at pet data. content://com.example.android.pets/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_PETS = "pets2";
    public static final String PATH_THEATRES = "theatres2";

    public static abstract class MovieEntry implements BaseColumns {

//        public static boolean isValidGender(int gender) {
//            if (gender == GENDER_UNKNOWN || gender == GENDER_MALE || gender == GENDER_FEMALE) {
//                return true;
//            }
//            return false;
//        }

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);
        public static final Uri CONTENT_URI2 = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_THEATRES);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;

        public static final String CONTENT_LIST_TYPE2 =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_THEATRES;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;
        public static final String CONTENT_ITEM_TYPE2 =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_THEATRES;

        public static final String TABLE_NAME="movie";
        public static final String _ID=BaseColumns._ID;
        public static final String COLUMN_MOVIE_NAME="name";
        public static final String COLUMN_DIRECTOR_NAME="Director";
        public static final String COLUMN_PRODUCER_NAME="producer";
        public static final String COLUMN_RUN_TIME="length";

//        public static final String TABLE_NAME="pets1";
//        public static final String _ID=BaseColumns._ID;
//        public static final String COLUMN_PET_NAME="name";
//        public static final String COLUMN_PET_BREED="breed";
//        public static final String COLUMN_PET_GENDER="gender";
//        public static final String COLUMN_PET_WEIGHT="weight";
//        public static final int GENDER_MALE=1;
//        public static final int GENDER_FEMALE=2;
//        public static final int GENDER_UNKNOWN=0;

        public static final String TABLE1_NAME="theatres";
        public static final String _ID1=BaseColumns._ID;
        public static final String COLUMN_THEATRE_NAME="name";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_NUM_OF_SCREENS = "screens";
//
//        public static final String TABLE2_NAME="shows";
//        public static final String _ID21="id from movie";
//        public static final String _ID31="id from theatres";






    }
}
