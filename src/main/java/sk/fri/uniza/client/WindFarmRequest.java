package sk.fri.uniza.client;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import sk.fri.uniza.api.AccessToken;
import sk.fri.uniza.api.Paged;
import sk.fri.uniza.api.Person;
import sk.fri.uniza.api.PublicKey;
import sk.fri.uniza.core.Data;
import sk.fri.uniza.core.Device;

import java.util.List;
import java.util.Map;

public interface WindFarmRequest {

    @GET("/api/login/public-key")
    Call<PublicKey> getPublicKey();

    @GET("/api/login/")
    Call<ResponseBody> getLoginHtml(@QueryMap Map<String, String> oauthRequest);

    @FormUrlEncoded
    @POST("/api/login/token")
    Call<AccessToken> getAccessToken(@FieldMap Map<String, String> tokenRequest);

    @GET("/api/persons")
    Call<List<Person>> getAllPersons(@Header("Authorization") String authorization);

    @GET("/api/persons/{id}")
    Call<Person> getPerson(@Header("Authorization") String authorization, @Path("id") Long id);

    @GET("/api/persons")
    Call<Paged<List<Person>>> getPagedPersons(@Header("Authorization") String authorization, @Query("limit") Integer limit, @Query("page") Integer page);

    @POST("/api/persons")
    Call<Person> savePersons(@Header("Authorization") String authorization, @Body Person person);

    @POST("/api/persons/password")
    @FormUrlEncoded
    Call<Void> saveNewPassword(@Header("Authorization") String authorization, @Query("id") Long id,  @Field("password") String password);

    @DELETE("/api/persons")
    Call<Void> deletePerson(@Header("Authorization") String bearerToken, @Query("id") Long id);

    @GET("/api/datas/{id}")
    Call<Data> getData(@Header("Authorization") String authorization, @Path("id") Long id);

    @GET("/api/datas")
    Call<Paged<List<Data>>> getPagedData(@Header("Authorization") String authorization, @Query("limit") Integer limit, @Query("page") Integer page);

    @DELETE("/api/datas")
    Call<Void> deleteData(@Header("Authorization") String bearerToken, @Query("id") Long id);

    @POST("/api/datas")
    Call<Data> saveDatas(@Header("Authorization") String authorization, @Body Data data);

    @GET("/api/devices/{id}")
    Call<Device> getDevice(@Header("Authorization") String authorization, @Path("id") Long id);

    @GET("/api/devices")
    Call<Paged<List<Device>>> getPagedDevice(@Header("Authorization") String authorization, @Query("limit") Integer limit, @Query("page") Integer page);

    @DELETE("/api/devices")
    Call<Void> deleteDevice(@Header("Authorization") String bearerToken, @Query("id") Long id);

    @POST("/api/devices")
    Call<Device> saveDevices(@Header("Authorization") String authorization, @Body Device device);

    @GET("/api/devices")
    Call<List<Device>> getAllDevices(@Header("Authorization") String authorization);

}
