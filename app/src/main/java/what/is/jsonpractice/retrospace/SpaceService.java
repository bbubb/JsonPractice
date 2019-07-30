package what.is.jsonpractice.retrospace;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpaceService {
    @GET("v3/image/200")
    Call<ArrayList<Objects>> loadSpace();
}
