package what.is.jsonpractice.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestrofitClientInstance {

    //declare and init base url
    public static final String BASE_URL = "http://shibe.online/api/";

    //declare Retrofit object
    private static Retrofit retrofit;

    //create a private constructor;
    private RestrofitClientInstance() {
    }

    //create public static method to get instance of the Retrofit Object
    public static Retrofit getRetrofit() {
        //this statement creates a new instance of Retrofit if the current instance is null
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
