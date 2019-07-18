package what.is.jsonpractice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import what.is.jsonpractice.sync.IntentReceiver;
import what.is.jsonpractice.sync.NotificationUtil;
import what.is.jsonpractice.sync.ShibeIntentService;

public class MainActivity extends AppCompatActivity implements ShibeAdapter.OnShibeClicked, IntentReceiver.Receiver {
    private static final String TAG = "MainActivity";

    //    ShibeAdapter.OnShibeClicked listener;
    IntentReceiver mReceiver;
    ShibeAdapter shibeAdapter;
    RecyclerView recyclerView;
    ImageExpand imageExpand;
    FrameLayout frameImageExpand;
    FragmentManager manager;
    ShibeIntentService shibeIntentService;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        mReceiver = new IntentReceiver(new Handler());
        mReceiver.setReceiver(this);
        recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);

        Intent intent = new Intent(this, ShibeIntentService.class);
        String count = "20";
        intent.putExtra("receiver", mReceiver);
        intent.putExtra("urlSent", count);
        startService(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mReceiver.setReceiver(null);
    }


    @OnClick(R.id.btn_grid)
    void grid() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @OnClick(R.id.btn_linear)
    void linear() {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }

    @Override
    public void shibeClicked(String url) {
        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();

        imageExpand = new ImageExpand();
        Bundle urlBundle = new Bundle();
        urlBundle.putString("URL", url);
        imageExpand.setArguments(urlBundle);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_expand, imageExpand, url)
                .addToBackStack("ImageExpand").commit();
        frameImageExpand = findViewById(R.id.frame_expand);
        frameImageExpand.setVisibility(View.VISIBLE);

    }

//    public void volleyRequest(int count) {
//        //1: Setup url
//        String baseURL = "http://shibe.online/api/shibes";
//        String query = "?count=" + count;
//        String url = baseURL + query;
//
//        //2: create RequestQueue
//        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
//
//        //3: declare JsonArrayRequest[] or JsonObjectRequest{} (depends on your .json file structure)
//        // then init it with new JsonArrayRequest or JsonObjectRequest
//        JsonArrayRequest request = new JsonArrayRequest(
//                url, // Param 1: url string
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {  //Param 2: Success listener
//                        List<String> urlList = new ArrayList<>();
//                        for (int i = 0; i < response.length(); i++) {
//                            try {
//                                urlList.add(response.get(i).toString());
//                                Log.d(TAG, "onResponse: " + response.get(i).toString());
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        loadRecyclerView(urlList);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {  //Param 3: Error listener
//
//                Log.d(TAG, "onErrorResponse: " + error.getLocalizedMessage());
//            }
//        }
//        );
//
//        //pass the request object from step 3 into the requestQueue object from step 2
//        requestQueue.add(request);
//
//    }

//    public void retrofitRequest(int count) {
//
//        //1: declare ShibeService and init it using RetrofitClientInstance
//        ShibeService shibeService = RestrofitClientInstance.getRetrofit().create(ShibeService.class);
//
//        //2: declare ShibeService Return type and init it using the ShibeService from step 1
//        Call<List<String>> shibeCall = shibeService.loadShibes(count);
//
//        //3: use the shibeCall from step 2 and call the .enqueue method
//        shibeCall.enqueue(new Callback<List<String>>() {
//            @Override
//            public void onResponse(Call<List<String>> call, retrofit2.Response<List<String>> response) {
//
//                if (response.isSuccessful()) {
//                    Log.d(TAG, "onResponse: Success");
//                    loadRecyclerView(response.body());
//                } else {
//                    Log.d(TAG, "onResponse: Failure");
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<String>> call, Throwable t) {
//                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
//            }
//        });
//    }

    private void loadRecyclerView(List<String> strings) {
        shibeAdapter = new ShibeAdapter(strings, MainActivity.this);
        recyclerView.setAdapter(shibeAdapter);
    }

//    class AsyncTaskURL extends AsyncTask<String, Void, List<String>> {
//
//
//        @Override
//        protected List<String> doInBackground(String... strings) {
//
//            //declare url variable
//            HttpURLConnection httpURLConnection = null;
//
//            //declare and init
//            String baseURL = "http://shibe.online/api/shibes";
//            String query = "?count=" + strings[0];
//
//            //this will hold all the json
//            StringBuilder result = new StringBuilder();
//
//            //create a URL object, passing a URL string into constructor
//            try {
//                URL url = new URL(baseURL + query);
//
//                //use the url object instance to create an internet connection
//                httpURLConnection = (HttpURLConnection) url.openConnection();
//
//                //create string instance and init  with a BufferInputStream
//                //then pass the stream from the httpURLConnection instance
//                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
//
//                //declare InputStreamReader and init without InputStream
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//
//                //declare BufferReader object and init it with InputStream
//                BufferedReader reader = new BufferedReader(inputStreamReader);
//
//                String line;
//
//                //read each line from BufferReader and append it into result(StringBuilder)
//                while ((line = reader.readLine()) != null) {
//                    //if line is not null append to result
//                    result.append(line);
//                }
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                //important to close the connection(disconnect)
//                if (httpURLConnection != null)
//                    httpURLConnection.disconnect();
//            }
//            Log.d(TAG, "doInBackground: " + result);
//
//            //Convert json(String) into List<String>
//            //First remove the brackets
//            String removeBrackets = result.substring(1, result.length() - 1);
//            //second replace quotes with nothing
//            String removeQuotes = removeBrackets.replace("\"", "");
//            //third split using comma(,) which creates a new line after every comma
//            String[] urls = removeQuotes.split(",");
//
//            return Arrays.asList(urls);
//        }
//
//        @Override
//        protected void onPostExecute(List<String> strings) {
//            super.onPostExecute(strings);
//            loadRecyclerView(strings);
//        }
//    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case 1337:
                String[] urlResults = resultData.getStringArray("queryResults");
                List<String> urlList = Arrays.asList(urlResults);
                loadRecyclerView(urlList);
                NotificationUtil.notifyUserLoaded(this);
                break;
        }
    }
}


