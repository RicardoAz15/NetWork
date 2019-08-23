package com.example.montepio_campaign_json;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.montepio_campaign_json.NetWork.JsonSpiceService;
import com.example.montepio_campaign_json.NetWork.ResponseContent;
import com.example.montepio_campaign_json.jsonClasses.CampaignRequest;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.CachedSpiceRequest;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.listener.SpiceServiceListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Layout Assets

    private ImageView[] dots;
    private LinearLayout sliderDot;
    private int dotsCount;

    private List<ResponseContent.ResponseContentResult> result;

    protected SpiceManager spiceManager = new SpiceManager(JsonSpiceService.class);

    private long request_start;
    private long request_response_get;
    private long request_finish;
    private TextView Timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timer = findViewById(R.id.Timer);

    }

    @Override
    protected void onStart(){
        super.onStart();
        request_start = SystemClock.elapsedRealtime();
        spiceManager.start(this);
        performRequest();
    }

    @Override
    protected void onStop() {
        if (spiceManager.isStarted())
            spiceManager.shouldStop();
        super.onStop();
    }

    private void performRequest() {
        MainActivity.this.setProgressBarIndeterminateVisibility(true);
        CampaignRequest request = new CampaignRequest();
        String lastRequestKey = request.createCashKey();
        CampaignListRequestListener requestListener = new CampaignListRequestListener();
        spiceManager.execute(request, lastRequestKey, DurationInMillis.ONE_MINUTE, requestListener);
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


    private class CampaignListRequestListener implements RequestListener<ResponseContent> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            //update UI
            System.out.println("NOT CONNECTED");
        }

        @Override
        public void onRequestSuccess(ResponseContent nResult) {
            //update UI

            result = nResult.getResult().getContentResult();

            request_response_get = SystemClock.elapsedRealtime();

            initUI();
        }
    }
}

