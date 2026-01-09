package com.example.yeneMatch;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST

public interface ApiInterface {

    /**
     * This method checks if the email entered in the first step of signup 
     * already exists in the 'users' table in your yene_match database.
     */
    @FormUrlEncoded
    @POST("check_email.php")
    Call<ResponseBody> checkEmail(
            @Field("email") String email
    );

    /**
     * This is the final step call that writes the full user and profile 
     * data to the database.
     */
    @FormUrlEncoded
    @POST("signup.php")
    Call<ResponseBody> createAccount(
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("full_name") String fullName,
            @Field("age") int age,
            @Field("location") String location
    );
}