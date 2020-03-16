package com.e.resincalculator;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class LoadData {

    public static final int LOAD_LIMIT = 15;
    public static String lastId = "0";
    public static boolean itShouldLoadMore = true;


    public static void firstLoadData(final Context c, final RecyclerAdapter recyclerAdapter,
                                     final ArrayList<RecyclerModel> recyclerModels,
                                     final ConstraintLayout clWifi) {

        String url= "http://robika.ir/ultitled/practice/tavasi_teb_sonati_load_data.php?action=load_resin_video&limit=" + LOAD_LIMIT + "&cat=" + UrlEncoderClass.urlEncoder("resin_calculator");
        itShouldLoadMore = false;

        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                //clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String onvan = jsonObject.getString("onvan");
                        String matn = jsonObject.getString("matn");
                        String picture = jsonObject.getString("picture");
                        String link = jsonObject.getString("link");

                        recyclerModels.add(new RecyclerModel(lastId, onvan,matn,picture,link));
                        recyclerAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

/*

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(c, error.toString(), Toast.LENGTH_SHORT).show();

                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(c, "AuthFailureError", Toast.LENGTH_SHORT).show();

                    //TODO
                } else if (error instanceof ServerError) {
                    Toast.makeText(c, "ServerError", Toast.LENGTH_SHORT).show();

                    //TODO
                } else if (error instanceof NetworkError) {
                    Toast.makeText(c, "NetworkError", Toast.LENGTH_SHORT).show();

                    //TODO
                } else if (error instanceof ParseError) {
                    Toast.makeText(c, "ParseError", Toast.LENGTH_SHORT).show();

                    //TODO
                }
*/

                itShouldLoadMore = true;
                progressDialog.dismissProgress();

                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                /*clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        LoadData.firstLoadData(c, recyclerAdapter, recyclerModels,null);
                    }
                });*/

            }
        });

        Volley.newRequestQueue(c).add(jsonArrayRequest);
    }
}
