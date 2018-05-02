package geogram.example.sportnews.rest.api;

import org.json.JSONObject;

import geogram.example.sportnews.rest.models.TestResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by geogr on 14.04.2018.
 */

public interface ApiTest {
    @Multipart
    @POST("check")
    Call<TestResponseModel> test(@Part("package") String pack,
                                 @Part("sim_operator") String sim_oper,
                                 @Part("sim_country") String sim_country,
                                 @Part("network_operator") String operator,
                                 @Part("locale") String local,
                                 @Part("device_manufacturer") String device_manufacturer,
                                 @Part("device_model") String device_model,
                                 @Part("additional_data") String additional_data);

    @GET("http://tech-encoder.info/test301")
    Call<JSONObject> getRequestStatus();
}
