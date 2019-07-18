package what.is.jsonpractice.sync;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ShibeIntentService extends IntentService {
    private static final String TAG = "ShibeIntentService";
    public String count;
    public String[] urls;

    public ShibeIntentService() {
        super("ShibeIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        count = intent.getStringExtra("urlSent");
        Bundle b = new Bundle();
        b.putStringArray("queryResults", queryAPI(count));

        if (receiver != null)
            receiver.send(1337, b);
    }

    public String[] queryAPI(String count) {
        HttpURLConnection httpURLConnection = null;
        String baseURL = "http://shibe.online/api/shibes";
        String query = "?count=" + count;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(baseURL + query);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null)
                httpURLConnection.disconnect();
        }
        Log.d(TAG, "doInBackground: " + result);
        String removeBrackets = result.substring(1, result.length() - 1);
        String removeQuotes = removeBrackets.replace("\"", "");
        urls = removeQuotes.split(",");
        return urls;
    }

}
