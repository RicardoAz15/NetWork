package com.example.volley_montepio;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.example.volley_montepio.ResponsePack.Objects.ContentToSend;
import com.example.volley_montepio.ResponsePack.ResponseContent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;

    private ImageView[] dots;

    //Time Measures
    private long request_start;
    private long request_response_get;
    private long request_finish;
    private TextView Timer;
    private Map<String, String> headers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configQueue();

        Timer = findViewById(R.id.Timer);

        headers = new HashMap<>();
        this.headers = initHeaders(headers);

        request_start = SystemClock.elapsedRealtime();

        performRequest();
    }

    private void configQueue() {
        queue = Volley.newRequestQueue(getApplicationContext());

        RequestQueue.RequestFinishedListener<Object> listener = new RequestQueue.RequestFinishedListener<Object>() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onRequestFinished(Request<Object> request) {
                request_finish = SystemClock.elapsedRealtime();
                long timeElapsedStart_Stop = request_finish - request_start;
                Timer.append(String.format("\nTime to finish: %d", timeElapsedStart_Stop));
            }
        };
        queue.addRequestFinishedListener(listener);
    }

    @SuppressLint("DefaultLocale")
    private void initUI(ResponseContent result) {

        long timeElapsedStart_Get = request_response_get - request_start;

        Timer.setText(
                String.format("Time to get response: %d", timeElapsedStart_Get));

        final RecyclerView recyclerView = findViewById(R.id.viewPagerContent);

        final List<ResponseContent.ResponseContentResult> contentResult =
                result.getResult().getContentResult();

        final Adapter viewHolderAdapter =
                new Adapter(MainActivity.this,
                        R.layout.content, contentResult, contentResult.size());

        recyclerView.setAdapter(viewHolderAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.HORIZONTAL,
                false);

        recyclerView.setLayoutManager(layoutManager);

        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        initDots(recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_SETTLING) {

                    for (ImageView dot : dots)
                        dot.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                                R.drawable.not_active_dot));

                    View snapView;
                    if ((snapView = snapHelper.findSnapView(recyclerView.getLayoutManager())) != null) {

                        int position = (recyclerView.getLayoutManager()).getPosition(snapView);

                        dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                                R.drawable.active_dot));
                    }
                }
            }
        });

    }

    Map<String, String> initHeaders(Map<String, String> headers) {

        headers.put("ITSAPP-DEVICE", "ANDROIDPHONE");
        headers.put("ITSAPP-LANG", "pt-PT");
        headers.put("ITSAPP-SO", "24");
        headers.put("ITSAPP-VER", "2.38");
        headers.put("MGAppId", "Android-Mobile");
        headers.put("MGIP", "192.168.102.23");
        headers.put("MGMdwVersion", "5");
        headers.put("MGScreen", "LoginFragment");
        return headers;
    }

    private void initDots(RecyclerView recyclerView) {
        LinearLayout sliderDot = findViewById(R.id.SliderDots);

        int dotsCount = recyclerView.getAdapter().getItemCount();

        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(MainActivity.this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                    R.drawable.not_active_dot));

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDot.addView(dots[i], params);

            dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                    R.drawable.active_dot));
        }
    }

    void performRequest() {

        final ContentToSend contentToSend = new ContentToSend("MARKETING");
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JSONObject contentToSendJson = null;
        try {
            contentToSendJson = new JSONObject(gson.toJson(contentToSend));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = "http://mobile-montepio.itsector.local/public/contentByGroup";

        //Synchronous Request
        /*
        final RequestFuture<JSONObject> requestFuture = RequestFuture.newFuture();

        JsonObjectRequest request =
                new JsonObjectRequest(Request.Method.POST, url, contentToSendJson,requestFuture, requestFuture) {
                    @Override
                    public Map<String, String> getHeaders() {
                        return headers;
                    }
                };

        queue.start();
        queue.add(request);

        requestFuture.setRequest(request);

        @SuppressLint("StaticFieldLeak") AsyncTask task =
                new AsyncTask<Object, Void,ResponseContent>() {

                    @Override
                    protected ResponseContent doInBackground(Object... objects) {
                        String response = null;
                        try {
                            response = requestFuture.get(10,TimeUnit.SECONDS).toString();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (TimeoutException e) {
                            e.printStackTrace();
                        }

                        ResponseContent responseContent = gson.fromJson(response,ResponseContent.class);

                        return responseContent;
                    }

                    @Override
                    protected void onPostExecute(ResponseContent responseContent){
                        request_response_get = SystemClock.elapsedRealtime();
                        initUI(responseContent);
                    }
                };

        task.execute();
        */


        // Asynchronous Request

        JsonObjectRequest request =
                new JsonObjectRequest(Request.Method.POST, url, contentToSendJson,new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                final ResponseContent result;
                                result = gson.fromJson(response.toString(), ResponseContent.class);
                                request_response_get = SystemClock.elapsedRealtime();
                                initUI(result);
                            }
                        }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            MainActivity.super.onRestart();
                        }
                        }) {
                        @Override
                        public Map<String, String> getHeaders() {
                        return headers;
                    }
                        };

        queue.start();
        queue.add(request);


                }
    }


