package com.example.retrofit_montepio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;

import android.os.SystemClock;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.retrofit_montepio.ResponsePack.Objects.ContentToSend;
import com.example.retrofit_montepio.ResponsePack.ResponseContent;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ImageView[] dots;
    private LinearLayout sliderDot;
    private int dotsCount;

    private List<ResponseContent.ResponseContentResult> result;

    private MyService service;
    protected Call<ResponseContent> call;
    protected MyService.Request request;

    private long request_start;
    private long request_response_get;
    private long request_finish;
    private TextView Timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timer = findViewById(R.id.Timer);

        request_start = SystemClock.elapsedRealtime();
        service = new MyService();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(service.API_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        request = retrofit.create(MyService.Request.class);
        Map<String, String> headers = new HashMap<>();
        initHeaders(headers);
        performRequest(headers);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    protected Map<String, String> initHeaders(Map<String, String> headers) {

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

    private void initUI(){

        long timeElapsedStart_Get = request_response_get - request_start;

        Timer.setText(
                String.format("Time to get response: %d",timeElapsedStart_Get));

        final RecyclerView recyclerView = findViewById(R.id.viewPagerContent);

        final Adapter viewHolderAdapter =
                new Adapter(MainActivity.this, R.layout.content, result, result.size() + 1);

        recyclerView.setAdapter(viewHolderAdapter);

        LinearLayoutManager layoutManager =  new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.HORIZONTAL,
                false);

        recyclerView.setLayoutManager(layoutManager);


        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        initDots(recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(newState == recyclerView.SCROLL_STATE_SETTLING){

                    for (ImageView dot : dots)
                        dot.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                                R.drawable.not_active_dot));

                    int position = recyclerView.getLayoutManager().getPosition(
                            snapHelper.findSnapView(recyclerView.getLayoutManager()));

                    dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                            R.drawable.active_dot));
                }
            }
        });
    }

    private void initDots(RecyclerView recyclerView){
        sliderDot = findViewById(R.id.SliderDots);

        dotsCount = recyclerView.getAdapter().getItemCount();

        dots = new ImageView[dotsCount];

        for (int i=0;i<dotsCount;i++) {
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

    protected JsonObject jsonToSend(){
        ContentToSend contentToSend = new ContentToSend("MARKETING");
        Gson gson = new Gson();
        return new JsonParser().parse(gson.toJson(contentToSend)).getAsJsonObject();
    }

    protected void performRequest(Map<String, String> headers) {

        call = request.getContent(headers,jsonToSend());
        call.enqueue(new Callback<ResponseContent>() {
            @Override
            public void onResponse(Call<ResponseContent> call, Response<ResponseContent> response) {

                if (response.body().getResult().getContentResult() == null)
                    System.exit(0);

                result = response.body().getResult().getContentResult();
                request_response_get = SystemClock.elapsedRealtime();
                initUI();
            }
            @Override
            public void onFailure(Call<ResponseContent> call, Throwable t) {
                System.out.println("ERROR");
            }
        });
}
}
