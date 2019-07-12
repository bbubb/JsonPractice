package what.is.jsonpractice.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShibeService {

    //BASE_URL        -> http://shibe.online/api/
    //@GET("shibes")  -> shibes
    //@Query("count") -> ?count = count(variable init to 10)
    @GET("shibes")
    Call<List<String>> loadShibes(@Query("count") int count);
    //  RETURN-TYPE   METHOD-NAME       METHOD-PARAMETERS
}
