package com.example.yenematch;



import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    /**
     * Checks if the email already exists in the 'users' table.
     */
    @FormUrlEncoded
    @POST("check_email.php")
    Call<ResponseBody> checkEmail(
            @Field("email") String email
    );

    /**
     * Final step: Writes user and profile data to the database.
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

    /**
     * Updated to use LoginResponse to capture the dynamic 'user_id' from PHP.
     */
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );

    /**
     * Fetches the list of all users for the Discovery feed.
     */
    @GET("get_profiles.php")
    Call<List<ProfileModel>> getAllProfiles();

    /**
     * Records a 'like' or 'dislike' in the MySQL 'likes' table.
     */
    @FormUrlEncoded
    @POST("record_interaction.php")
    Call<ResponseBody> recordInteraction(
            @Field("liker_id") int likerId,
            @Field("liked_id") int likedId,
            @Field("status") String status
    );

    @GET("get_matches.php")
    Call<List<MatchModel>> getMyMatches(@Query("user_id") int userId);

    @GET("get_chat_history.php")
    Call<List<MessageModel>> getChatMessages(@Query("match_id") int matchId);

    @FormUrlEncoded
    @POST("send_message.php")
    Call<ResponseBody> sendChatMessage(
            @Field("match_id") int matchId,
            @Field("sender_id") int senderId,
            @Field("message_type") String type,
            @Field("message_content") String content
    );
}
