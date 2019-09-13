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

import org.jetbrains.annotations.NotNull;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* synchronous import
import java.util.concurrent.ExecutionException;
import java.io.IOException;
import android.os.AsyncTask;
 */


public class MainActivity extends AppCompatActivity {

    private final static String ITSAPP_DEVICE = "ITSAPP-DEVICE";
    private final static String ANDROIDPHONE = "ANDROIDPHONE";

    private final static String ITSAPP_LANG_KEY = "ITSAPP-LANG";
    private final static String ITSAPP_LANG_VALUE = "pt-PT";

    private final static String ITSAPP_SO_KEY = "ITSAPP-SO";
    private final static String ITSAPP_SO_VALUE = "24";

    private final static String ITSAPP_VER_KEY = "ITSAPP-VER";
    private final static String ITSAPP_VER_VALUE = "2.38";

    private final static String MGAPPID_KEY = "MGAppID";
    private final static String MGAPPID_VALUE = "Android-Mobile";

    private final static String MGIP_KEY = "MGIP";
    private final static String MGIP_VALUE = "192.168.102.23";

    private final static String MGMDWVERSION_KEY = "MGMdwVersion";
    private final static String MFMDWVERSION_VALUE = "5";

    private final static String MGSCREEN_KEY = "MGScreen";
    private final static String MGSCREEN_VALUE = "LoginFragment";

    private ImageView[] dots;

    protected Call<ResponseContent> call;
    protected MyService.Request request;

    private long request_start_timer;
    private long request_response_get_timer;
    private TextView timerTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        timerTv = findViewById(R.id.timerTv); //TODO: este nome está mal dado, isto não é um timer, é uma TextView, devias ter-lhe chamado p.e. timerTv

        Retrofit retrofit = new Retrofit.Builder().baseUrl(MyService.API_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        request = retrofit.create(MyService.Request.class);

        Map<String, String> headers = new HashMap<>();
        initHeaders(headers);

        performRequest(headers);
    }

    //TODO: podias ter usado o javadoc

    /**
     * Metodo usado para inicializar os Headers a serem enviado posteriormente no request
     * @param headers Headers a serem inicializados
     * @return Map dos headers que foram inicializados
     */
    protected Map<String, String> initHeaders(Map<String, String> headers) {

        headers.put(ITSAPP_DEVICE, ANDROIDPHONE);           //TODO: podias ter usado keys, pessoalmente não gosto de ver strings no meio do código
        headers.put(ITSAPP_LANG_KEY, ITSAPP_LANG_VALUE);
        headers.put(ITSAPP_SO_KEY, ITSAPP_SO_VALUE);
        headers.put(ITSAPP_VER_KEY, ITSAPP_VER_VALUE);
        headers.put(MGAPPID_KEY, MGAPPID_VALUE);
        headers.put(MGIP_KEY, MGIP_VALUE);
        headers.put(MGMDWVERSION_KEY, MFMDWVERSION_VALUE);
        headers.put(MGSCREEN_KEY, MGSCREEN_VALUE);

        return headers;
    }

    /**
     * Este metodo cria o UI desta actividade assim como o preenche com os valores necessarios
     * @param result Lista com os valores de text/imagem para o UI
     */
    private void initUI(List<ResponseContent.ResponseContentResult> result) {

        long timeElapsedStart_Get = request_response_get_timer - request_start_timer;
        timerTv.setText(getString(R.string.timerTv_message,timeElapsedStart_Get));   //TODO: devias ter todas as strings no resources

        RecyclerView recyclerView = initRecyclerView(result);

        initDots(recyclerView);

        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_SETTLING) {

                    for (ImageView dot : dots)
                        dot.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                                R.drawable.not_active_dot));

                    int position = Objects.requireNonNull(recyclerView.getLayoutManager()).getPosition(
                            Objects.requireNonNull(snapHelper.findSnapView(recyclerView.getLayoutManager())));

                    dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                            R.drawable.active_dot));
                }
            }
        });

    }

    /**
     * Este metodo inicia o RecyclerView
     * @param result valores a ser usados como items do RecyclerView
     * @return RecyclerView inicializado com Adapter e LayoutManager
     */
    private RecyclerView initRecyclerView(List<ResponseContent.ResponseContentResult> result){
        final RecyclerView recyclerView = findViewById(R.id.viewPagerContent);
        final Adapter viewHolderAdapter =
                new Adapter(MainActivity.this, R.layout.content, result, result.size());

        final LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.HORIZONTAL,
                false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(viewHolderAdapter);

        return recyclerView;

    }

    /**
     * Adição dos DOTS ao recyclerView
     * @param recyclerView RecyclerView a ser usado
     */
    private void initDots(RecyclerView recyclerView) {

        LinearLayout sliderDot = findViewById(R.id.SliderDots);

        int dotsCount = 1;//TODO: devias validar sempre os null, aproveita as dicas do IDE

        if(recyclerView.getAdapter()!=null)
            dotsCount = recyclerView.getAdapter().getItemCount();

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

    /**
     * Criação do json para o POST Request
     * @return JsonObject
     */
    protected JsonObject jsonToSend() {
        ContentToSend contentToSend = new ContentToSend("MARKETING");
        Gson gson = new Gson();
        return new JsonParser().parse(gson.toJson(contentToSend)).getAsJsonObject();
    }

    /**
     * Execução/Inicialização do request
     * @param headers Headers do POST Request
     */
    protected void performRequest(Map<String, String> headers) {

        call = request.getContent(headers, jsonToSend());

        request_start_timer = SystemClock.elapsedRealtime();

        //synchronous request
        /*
        @SuppressLint("StaticFieldLeak") AsyncTask<Void,Void,ResponseContent> task =
                new AsyncTask<Void, Void, ResponseContent>() {
            @Override
            protected ResponseContent doInBackground(Void... voids) {
                ResponseContent content = null;
                try {
                    content = call.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                    onRestart();
                }
                return content;
            }
        };

        try {
            result = task.execute().get().getResult().getContentResult();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        request_response_get_timer = SystemClock.elapsedRealtime();

        initUI(result);
        */

        //Asynchronous request

        call.enqueue(new Callback<ResponseContent>() {
            @Override
            public void onResponse(@NotNull Call<ResponseContent> call, @NotNull Response<ResponseContent> response) {

                if (response.body() == null)
                {
                    ((TextView) findViewById(R.id.description)) //TODO: não mates a app sem dar feedback ao user
                            .setText(R.string.netWork_error_message);
                    System.exit(0);
                }
                else {
                    final List<ResponseContent.ResponseContentResult> result =
                            response.body().getResult().getContentResult();
                    request_response_get_timer = SystemClock.elapsedRealtime();
                    initUI(result);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseContent> call, @NotNull Throwable t) {
                ((TextView) findViewById(R.id.description))
                        .setText(R.string.netWork_error_message);
                System.exit(0);
            }
        });
    }
}
