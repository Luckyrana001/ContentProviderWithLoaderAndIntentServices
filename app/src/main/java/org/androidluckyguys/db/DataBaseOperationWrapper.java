package org.androidluckyguys.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import org.androidluckyguys.model.CvpBalance;
import org.androidluckyguys.model.MasterStateVOs;
import org.androidluckyguys.db.ProviderMetadata.*;

import java.util.ArrayList;

/**
 * Created by XKL0275 on 6/10/2015.
 */
public class DataBaseOperationWrapper {
    private static final String TAG = "DBOperationWrapper";



    /*public static void deleteProductCatalogItem(Context context) {

        int deletedRec = context.getContentResolver().delete(
                ProductCatalogsMetaData.CONTENT_URI, null, null);
        Log.e(TAG, "Deleted Rec:" + deletedRec);
    }



    public static void deleteChckoutData(Context context){
        context.getContentResolver().delete(
                CollectionCheckoutMetaData.CONTENT_URI, null, null);
        context.getContentResolver().delete(
                InventoryCheckoutMetaData.CONTENT_URI, null, null);
        context.getContentResolver().delete(
                RMACheckoutMetaData.CONTENT_URI, null, null);
    }


    public static boolean isEmptyShoppingCart(Context context) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(
                    ShoppingCartItemsMetaData.CONTENT_URI, null, null, null, null);
            return (cursor.getCount() > 0)? false : true;
        }
        finally {
            if(null != cursor)
            {
                cursor.close();
            }
        }

    }



    public static void updateCvpBalance(Context context, CvpBalance cvpBalance)
    {
        ContentValues dataToUpdate = new ContentValues();
        dataToUpdate.put(ProviderMetadata.NewProfileMetaData.REC_PROFILE_CVP_BALANCE, cvpBalance.getBalance());


        int recId = context.getContentResolver().update(ProviderMetadata.NewProfileMetaData.CONTENT_URI,
                dataToUpdate, null, null);
        Log.e(TAG, "CVP Record update Id:" + recId);
    }

    public static void updateConnectionStatus(Context context, boolean connected)
    {
        ContentValues dataToUpdate = new ContentValues();
        dataToUpdate.put(ProviderMetadata.MposAppMetaData.REC_APP_ONLINE_STATUS, connected);

        int recId = context.getContentResolver().update(ProviderMetadata.MposAppMetaData.CONTENT_URI,
                dataToUpdate, null, null);
        Log.e(TAG, "mpos app Record update Id:" + recId);
    }

    public static String getLoggedInUserId(Context context)
    {
        String userid = null;
        String[] projCvp = {ProviderMetadata.NewProfileMetaData._ID,
                ProviderMetadata.NewProfileMetaData.REC_PROFILE_USER_ID
        };
        Cursor cursor = context.getContentResolver().query(ProviderMetadata.NewProfileMetaData.CONTENT_URI,
                projCvp, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            userid =  cursor.getString(cursor.getColumnIndex(ProviderMetadata.NewProfileMetaData.REC_PROFILE_USER_ID));
        }
        cursor.close();
        return userid;
    }


    public static String getLoggedInUserName(Context context)
    {
        String userid = null;
        String[] projCvp = {ProviderMetadata.ProfileMetaData._ID,
                ProviderMetadata.ProfileMetaData.REC_PROFILE_FIRST_NAME
        };
        Cursor cursor = context.getContentResolver().query(ProviderMetadata.ProfileMetaData.CONTENT_URI,
                projCvp, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            userid =  cursor.getString(cursor.getColumnIndex(ProviderMetadata.ProfileMetaData.REC_PROFILE_FIRST_NAME));
        }
        cursor.close();
        return userid;
    }

    public static String getLoginId(Context context)
    {
        String loginId = null;
        String[] projCvp = {ProviderMetadata.ProfileMetaData._ID,
                ProviderMetadata.ProfileMetaData.REC_PROFILE_LOGINID
        };
        Cursor cursor = context.getContentResolver().query(ProviderMetadata.ProfileMetaData.CONTENT_URI,
                projCvp, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            loginId =  cursor.getString(cursor.getColumnIndex(ProviderMetadata.ProfileMetaData.REC_PROFILE_LOGINID));
        }
        cursor.close();
        return loginId;
    }

    public static String getOrganizationId(Context context)
    {


        String orgID = null;
        try {
            String[] projCvp = {ProviderMetadata.NewProfileMetaData._ID, ProviderMetadata.NewProfileMetaData.REC_USER_ORGANISATION_ID};
            Cursor cursor = context.getContentResolver().query(ProviderMetadata.NewProfileMetaData.CONTENT_URI, projCvp, null, null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                orgID = cursor.getString(cursor.getColumnIndex(ProviderMetadata.NewProfileMetaData.REC_USER_ORGANISATION_ID));
            }
            cursor.close();
        }
        catch(Exception e)
        {
            orgID = "0" ;
        }
        return orgID;
    }





    public static String getStoreId(Context context)
    {
        String id = null;
        String[] projCvp = {ProviderMetadata.NewProfileMetaData._ID,
                NewProfileMetaData.REC_PROFILE_STORE_ID
        };
        Cursor cursor = context.getContentResolver().query(NewProfileMetaData.CONTENT_URI,
                projCvp, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            id =  cursor.getString(cursor.getColumnIndex(NewProfileMetaData.REC_PROFILE_STORE_ID));
        }
        cursor.close();
        return id;
    }*/

    public static ArrayList<MasterStateVOs> getStatesList(Context context)
    {
       /* get data from db acc to ids
       * String[] args = { "lucky firstString", "second@string.com" };
         Cursor cursor = db.query("TABLE_NAME", null, "name=? AND email=?", args, null);

         projArgs is about how many column data you want to fetch - need to specify each column which we want to fetch

          selectionArgs is like select * from table where id = 11

          and second last args is like how many rows you want to fetch from db */

       // String[] args = { LOADER_ID_MPOS_CARD_TYPE+"" };\
       // last parameter is the sort order

        ArrayList<MasterStateVOs> statesList = new ArrayList<MasterStateVOs>();
        try {
            String[] projArgs = {ProviderMetadata.PersonObjectMetaData._ID, ProviderMetadata.PersonObjectMetaData.REC_MASTER_NAME,
                    PersonObjectMetaData.REC_MASTER_EMAIL, PersonObjectMetaData.REC_MASTER_PHONE};

            Cursor cursor = context.getContentResolver().query(ProviderMetadata.PersonObjectMetaData.CONTENT_URI, projArgs, null, null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                while(!cursor.isAfterLast()) {
                    String    stateId = cursor.getString(cursor.getColumnIndex(ProviderMetadata.PersonObjectMetaData.REC_MASTER_NAME));
                    String stateCode = cursor.getString(cursor.getColumnIndex(ProviderMetadata.PersonObjectMetaData.REC_MASTER_EMAIL));
                    String stateName = cursor.getString(cursor.getColumnIndex(ProviderMetadata.PersonObjectMetaData.REC_MASTER_PHONE));

                    MasterStateVOs model = new MasterStateVOs();
                    model.setStateId(stateId);
                    model.setStateName(stateName);
                    model.setStateCode(stateCode);

                    statesList.add(model);

                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        catch(Exception e)
        {
            Log.i("Exception",e.toString());
        }
        return statesList;
    }

    public static void insertStateObjectData(Context context, ArrayList<MasterStateVOs> masterStateslist)
    {
        for(int i = (masterStateslist.size() - 1), j = 0; i >= j; i--)
        {
            MasterStateVOs model = masterStateslist.get(i);

            ContentValues dataToInsert = new ContentValues();
            dataToInsert.put(PersonObjectMetaData.REC_MASTER_NAME,
                    model.getStateId());
            dataToInsert.put(PersonObjectMetaData.REC_MASTER_EMAIL,
                    model.getStateCode());
            dataToInsert.put(PersonObjectMetaData.REC_MASTER_PHONE,
                    model.getStateName());


            Uri uri = context.getContentResolver().insert(
                    ProviderMetadata. PersonObjectMetaData.CONTENT_URI, dataToInsert);
            //Log.e(TAG, "Master States data Inserted URI:" + uri.toString());
        }


    }

    public static void insertPersonObjectData(Context context, String name, String email, String phone)
    {
         /* get data from db acc to ids
         *
         * String[] args = { "lucky firstString", "second@string.com" };

           Cursor cursor = db.query("TABLE_NAME", null, "name=? AND email=?", args, null);

           projArgs is about how many column data you want to fetch - need to specify each column which we want to fetch

           selectionArgs is like select * from table where id = 11

          and second last args is like how many rows you want to fetch from db

           String[] args = { OBJECT_TYPE+"" };

           last parameter is to define the sort order of data */


        try {
            ContentValues dataToInsert = new ContentValues();
            dataToInsert.put(PersonObjectMetaData.REC_MASTER_NAME,
                    name);
            dataToInsert.put(PersonObjectMetaData.REC_MASTER_EMAIL,
                    email);
            dataToInsert.put(PersonObjectMetaData.REC_MASTER_PHONE,
                    phone);


            Uri uri = context.getContentResolver().insert(
                    ProviderMetadata.PersonObjectMetaData.CONTENT_URI, dataToInsert);
            Log.e(TAG, "Person Object data Inserted URI:" + uri.toString());
        }
        catch (Exception e)
        {
            Log.i("Exception",e.toString());
        }
    }





    public  static String checkNullInteger(String val){
        String value="0";
        if(val!=null){
            value = val;
        }
        return value;
    }

    public  static String checkNullDouble(String val){
        String value="0.0";
        if(val!=null){
            value = val;
        }
        return value;
    }


}
