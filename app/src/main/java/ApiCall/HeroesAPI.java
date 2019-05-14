package ApiCall;

import java.util.List;

import model.Heroes;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HeroesAPI {

    @GET("uploads/{imgName}")
    Call<ResponseBody> getImage(@Path("imgName") String imgName);


    @GET("heroes")
    Call<List<Heroes>> getAllHeroes();

}
