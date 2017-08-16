package org.androidluckyguys.services;

import android.app.IntentService;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.androidluckyguys.App;
import org.androidluckyguys.R;
import org.androidluckyguys.db.DataBaseOperationWrapper;
import org.androidluckyguys.manager.Utils;
import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by XKL0275 on 20/10/2015.
 */
public class MposIntentService extends IntentService   {

    public static final String GET_MASTER_STATES_LIST = "GET_MASTER_STATES_LIST";
    public ProgressDialog pDialog;

    public static final String GET_PERSON_OBJECT = "GET_PERSON_OBJECT";


    private IntentFilter matcher;

    public MposIntentService() {
        super("MposIntentService");

        //Create the filter for matching incoming requests
        matcher = new IntentFilter();

        matcher.addAction(GET_PERSON_OBJECT);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Check for a valid request
        if(!matcher.matchAction(intent.getAction())) {
            Toast.makeText(this, "MposIntentService: Invalid Request",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        else if (TextUtils.equals(intent.getAction(), GET_PERSON_OBJECT))
        {
            if (Utils.isNetworkConnected(this, true, R.style.AppCompatAlertDialogStyle))
            {

                getPersonObjectData();
            }
        }
    }
    private void getPersonObjectData() {
// Tag used to cancel the request
        String tag_json_obj = "json_obj_req";

        String url = "https://api.androidhive.info/volley/person_object.json";

        // we can not use progress dialog in background service
      /*   pDialog = new ProgressDialog(this);
         pDialog.setMessage("Loading...");
         pDialog.show();*/

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        JSONObject personObject = null;
                        try {
                             personObject = new JSONObject(response.toString());
                            String name = personObject.getString("name");
                            String email = personObject.getString("email");
                            String phone = personObject.getString("phone");

                            insertPersonDataIntoDatabase(name,email,phone);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        //pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.data != null) {
                    String jsonError = new String(networkResponse.data);
                    System.out.print(jsonError + "");
                }
                // hide the progress dialog
               // pDialog.hide();
            }
        });

// Adding request to request queue
        App.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void insertPersonDataIntoDatabase(String name,String email,String phone) {
        DataBaseOperationWrapper.insertPersonObjectData(this,name,email,phone);
    }


}
