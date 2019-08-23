package com.example.volley_montepio;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
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
import com.android.volley.toolbox.Volley;
import com.example.volley_montepio.ResponsePack.Objects.ContentToSend;
import com.example.volley_montepio.ResponsePack.ResponseContent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;

    private ImageView[] dots;

    private ResponseContent result;

    private Map<String, String> headers;

    //Time Measures
    private long request_start;
    private long request_response_get;
    private long request_finish;
    private TextView Timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        Timer = findViewById(R.id.Timer);
        initHeaders();
        request_start = SystemClock.elapsedRealtime();
        performRequest();
    }

    private void initUI() {

        long timeElapsedStart_Get = request_response_get - request_start;

        Timer.setText(
                String.format("Time to get response: %d",timeElapsedStart_Get));

        final RecyclerView recyclerView = findViewById(R.id.viewPagerContent);

        final List<ResponseContent.ResponseContentResult> contentResult =
                result.getResult().getContentResult();

        final Adapter viewHolderAdapter =
                new Adapter(MainActivity.this,
                        R.layout.content, contentResult, contentResult.size() + 1);

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

                    View snapview;
                    if ((snapview = snapHelper.findSnapView(recyclerView.getLayoutManager())) != null) {

                        int position = recyclerView.getLayoutManager().getPosition(snapview);

                        dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                                R.drawable.active_dot));
                    }
                }
            }
        });

    }

    private void initHeaders() {
        headers = new HashMap<>();
        headers.put("ITSAPP-DEVICE", "ANDROIDPHONE");
        headers.put("ITSAPP-LANG", "pt-PT");
        headers.put("ITSAPP-SO", "24");
        headers.put("ITSAPP-VER", "2.38");
        headers.put("MGAppId", "Android-Mobile");
        headers.put("MGIP", "192.168.102.23");
        headers.put("MGMdwVersion", "5");
        headers.put("MGScreen", "LoginFragment");
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
    private void performRequest() {

        final ContentToSend contentToSend = new ContentToSend("MARKETING");
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JSONObject contentToSendJson = null;
        try {
            contentToSendJson = new JSONObject(gson.toJson(contentToSend));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = "http://mobile-montepio.itsector.local/public/contentByGroup";

        JsonObjectRequest request =
                new JsonObjectRequest(Request.Method.POST, url, contentToSendJson,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                result = gson.fromJson(response.toString(), ResponseContent.class);
                                request_response_get = SystemClock.elapsedRealtime();
                                initUI();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() {
                        return headers;
                    }
                };

        RequestQueue.RequestFinishedListener listener = new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                request_finish = SystemClock.elapsedRealtime();
                long timeElapsedStart_Stop = request_finish - request_start;
                Timer.append(String.format("\nTime to finish: %d",timeElapsedStart_Stop));
            }
        };
        queue.addRequestFinishedListener(listener);
        queue.start();
        queue.add(request);
    }
}


