package org.androidluckyguys.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by XKL0275 on 5/10/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    DatabaseHelper(Context context) {
        super(context, ProviderMetadata.DATABASE_NAME, null, ProviderMetadata.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //Creating person object data Table
        db.execSQL("CREATE TABLE " + ProviderMetadata.PersonObjectMetaData.TABLE_NAME + " ("
                + ProviderMetadata.PersonObjectMetaData._ID + " INTEGER PRIMARY KEY,"
                + ProviderMetadata.PersonObjectMetaData.REC_MASTER_NAME + " TEXT,"
                + ProviderMetadata.PersonObjectMetaData.REC_MASTER_EMAIL + " TEXT,"
                + ProviderMetadata.PersonObjectMetaData.REC_MASTER_PHONE + " TEXT"

               // +  " unique ("+ ProviderMetadata.StatesMetaData.REC_MASTER_NAME +")"
                + ");");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + ProviderMetadata.PersonObjectMetaData.TABLE_NAME);
        onCreate(db);
    }
}
