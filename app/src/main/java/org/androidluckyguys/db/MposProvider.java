package org.androidluckyguys.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

import org.androidluckyguys.db.ProviderMetadata.*;

import java.util.HashMap;

/**
 * Created by XKL0275 on 5/10/2015.
 */
public class MposProvider extends ContentProvider {
    private static final String TAG = "Sample Provider";
    private DatabaseHelper mOpenHelper;



    private static HashMap<String, String> sMasterStatesProjectionMap;

    {
        sMasterStatesProjectionMap = new HashMap<String, String>();
        //map for store user table
        sMasterStatesProjectionMap.put(PersonObjectMetaData._ID, PersonObjectMetaData._ID);
        sMasterStatesProjectionMap.put(PersonObjectMetaData.REC_MASTER_NAME,
                PersonObjectMetaData.REC_MASTER_NAME);
        sMasterStatesProjectionMap.put(PersonObjectMetaData.REC_MASTER_EMAIL,
                PersonObjectMetaData.REC_MASTER_EMAIL);
        sMasterStatesProjectionMap.put(PersonObjectMetaData.REC_MASTER_PHONE,
                PersonObjectMetaData.REC_MASTER_PHONE);

    }




    private static final UriMatcher sUriMatcher;

    private static final int TYPE_PROFILE = 19;
    private static final int TYPE_STATES = 20;


    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        sUriMatcher.addURI(ProviderMetadata.AUTHORITY, PersonObjectMetaData.TABLE_NAME, TYPE_STATES);

    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "main onCreate called");
        mOpenHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        switch (sUriMatcher.match(uri)) {


            case TYPE_STATES:
                qb.setTables(PersonObjectMetaData.TABLE_NAME);
                qb.setProjectionMap(sMasterStatesProjectionMap);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        // If no sort order is specified use the default
        String orderBy = BaseColumns._ID + " DESC ";
        if (sortOrder != null)
        {
            orderBy = sortOrder;
        }
        String groupBy = null;

        // Get the database and run the query
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        //Cursor c = qb.query(db, projection, selection, selectionArgs, groupBy, null, orderBy);
        Cursor c = qb.query(db, projection, selection, selectionArgs, groupBy, null, orderBy);

        // Tell the cursor what uri to watch,
        // so it knows when its source data changes
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {

            case TYPE_STATES:
                return PersonObjectMetaData.CONTENT_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {
        // Validate the requested uri
        ContentValues values;
        if (initialValues != null)
        {
            values = new ContentValues(initialValues);
        }
        else
        {
            values = new ContentValues();
        }
       if(sUriMatcher.match(uri) == TYPE_STATES){
            SQLiteDatabase db = mOpenHelper.getWritableDatabase();
            long rowId = db.insertWithOnConflict(PersonObjectMetaData.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
            if (rowId > 0) {
                Uri tempUri = ContentUris.withAppendedId(PersonObjectMetaData.CONTENT_URI, rowId);
                getContext().getContentResolver().notifyChange(tempUri, null);
                System.out.println("Record inserted..");
                return tempUri;
            }
            else{
                return null;
            }
        }


        else
        {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) {


            case TYPE_STATES:
                count = db.delete(PersonObjectMetaData.TABLE_NAME,	where, whereArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) {


            case TYPE_STATES:
                count = db.update(PersonObjectMetaData.TABLE_NAME,	values, where, whereArgs);
                break;


            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
