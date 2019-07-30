package what.is.jsonpractice.retrospace;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSpaceClient {

    public static final String BASE_URL = "http://hubblesite.org/api/";
    private static Retrofit retrofit;

    private RetrofitSpaceClient() {
    }

    public static Retrofit getRetrofitSpace() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
