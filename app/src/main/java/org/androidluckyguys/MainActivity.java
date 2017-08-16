package org.androidluckyguys;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.androidluckyguys.db.ProviderMetadata;
import org.androidluckyguys.model.NameModel;
import org.androidluckyguys.services.MposIntentService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  LoaderManager.LoaderCallbacks<Cursor> {
    Activity activity;
    NamesListAdapter mAdapter;

    private LoaderManager.LoaderCallbacks<Cursor> mProgressCallbacks;
    private LoaderManager loaderManager;
    private RecyclerView recylerView;
    public ArrayList<NameModel> dbDataList = new ArrayList<NameModel>();
    public static final int LOADER_ID_PERSON_OBJECT_TYPE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         activity = MainActivity.this;
         recylerView = (RecyclerView)findViewById(R.id.recylerView);

        getPersonObjectData(activity);

        addCallbacks();

    }

    public void loadDataAgain(View v)
    {
        getPersonObjectData(activity);
    }
    private void getPersonObjectData( Activity activity)
    {
        Intent intentBalance = new Intent(activity, MposIntentService.class);
        intentBalance.setAction(MposIntentService.GET_PERSON_OBJECT);
        activity.startService(intentBalance);
    }
    private void addCallbacks() {

        mProgressCallbacks = this;
        loaderManager = getLoaderManager();

        loaderManager.initLoader(LOADER_ID_PERSON_OBJECT_TYPE, null, mProgressCallbacks);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case LOADER_ID_PERSON_OBJECT_TYPE:

                String[] projCvp = {ProviderMetadata.PersonObjectMetaData._ID, ProviderMetadata.PersonObjectMetaData.REC_MASTER_NAME,
                        ProviderMetadata.PersonObjectMetaData.REC_MASTER_EMAIL,ProviderMetadata.PersonObjectMetaData.REC_MASTER_PHONE
                };
                // Create a new CursorLoader with the following query parameters.
                return new CursorLoader(this, ProviderMetadata.PersonObjectMetaData.CONTENT_URI,
                        projCvp, null, null, null);

            default:
                return null;
        }
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        switch (loader.getId()) {
            case LOADER_ID_PERSON_OBJECT_TYPE:
                if (cursor!=null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    String name = cursor.getString(cursor.getColumnIndex(ProviderMetadata.PersonObjectMetaData.REC_MASTER_NAME));
                    /*String storeName = cursor.getString(cursor.getColumnIndex(
                            ProviderMetadata.AccountMetaData.REC_ACCOUNT_STORE_NAME));*/
                    if (name != null) {


                        Toast.makeText(this,name+" "+cursor.getCount()+"",Toast.LENGTH_LONG).show();

                        NameModel model = new NameModel();
                        model.setName(cursor.getString(cursor.getColumnIndex(ProviderMetadata.PersonObjectMetaData.REC_MASTER_NAME)));
                        model.setEmail(cursor.getString(cursor.getColumnIndex(ProviderMetadata.PersonObjectMetaData.REC_MASTER_EMAIL)));
                        model.setPhone(cursor.getString(cursor.getColumnIndex(ProviderMetadata.PersonObjectMetaData.REC_MASTER_PHONE)));
                        dbDataList.add(model);


                    }

                }

                updateRecylerView(dbDataList);
                break;
        }}

    private void updateRecylerView(ArrayList<NameModel> dbDataList ) {
        mAdapter = new NamesListAdapter(this,dbDataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recylerView.setLayoutManager(mLayoutManager);
        recylerView.setItemAnimator(new DefaultItemAnimator());
        recylerView.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        //no swaping needed for progress update
    }

    public void onFragmentInteraction(Uri uri) {

    }

}
