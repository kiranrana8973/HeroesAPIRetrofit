package ApiCall;

import java.util.List;
import java.util.Map;

import model.Heroes;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface HeroesAPI {

    @GET("uploads/{imgName}")
    Call<ResponseBody> getImage(@Path("imgName") String imgName);

    @GET("heroes")
    Call<List<Heroes>> getAllHeroes();

    @POST("heroes")
    Call<Void> registerHero(@Body Heroes hero);

    @FormUrlEncoded
    @POST("heroes")
    Call<Void> registerHero(@Field("name") String name,@Field("desc") String desc,@Field("image") String image);

    @FormUrlEncoded
    @POST("heroes")
    Call<Void> registerHero(@FieldMap Map<String,String> map);

    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part img );
}