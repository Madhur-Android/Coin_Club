package com.example.coinclubapp.InterFace;

import com.example.coinclubapp.Response.KycResponse;
import com.example.coinclubapp.result.BankDetailsResult;
import com.example.coinclubapp.result.ClubResult;
import com.example.coinclubapp.result.LoginResult;
import com.example.coinclubapp.result.FormTwoResult;
import com.example.coinclubapp.result.RoundsResult;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("regs_list/")
    Call<FormTwoResult> registerNewUser(@Field("full_name") String full_name,
                                        @Field("mobileno") String mobileno,
                                        @Field("city") String city,
                                        @Field("password") String password,
                                        @Field("gender") String gender,
                                        @Field("occupation") String occupation,
                                        @Field("motive") String motive,
                                        @Field("income") String income,
                                        @Field("monthlycontribution") String monthlycontribution,
                                        @Field("profileimg") String profileimg,
                                        @Field("email") String email
    );

    @GET("regs_list/")
    Call<List<FormTwoResult>> getAllRegisteredUsers();


    @FormUrlEncoded
    @POST("bslogin/")
    Call<LoginResult> UserLogin(@Field("mobileno") String mobileno,
                                @Field("password") String password);


    @Multipart
    @POST("bankdetails/")
    Call<BankDetailsResult> postBankDetails(
            @Part("registerno") RequestBody registerno,
            @Part("IFSCcode") RequestBody IFSCcode,
            @Part("accountname") RequestBody accountname,
            @Part("accountnumber") RequestBody accountnumber,
            @Part MultipartBody.Part passbookimg
    );

    @Multipart
    @POST("userkyc/")
    Call<KycResponse> postKycItems(@Part("full_name") RequestBody full_name,
                                   @Part("address") RequestBody address,
                                   @Part("mobile") RequestBody mobile,
                                   @Part("email") RequestBody email,
                                   @Part("aadharno") RequestBody aadharno,
                                   @Part("panno") RequestBody panno,
                                   @Part MultipartBody.Part aadharfrontimg,
                                   @Part MultipartBody.Part aadharbackimg,
                                   @Part MultipartBody.Part panimg
    );


    @GET("https://meetjob.techpanda.art/club")
    Call<List<ClubResult>> getAllClubs();

    @GET("rounds/")
    Call<List<RoundsResult>> getAllRounds();

    @GET("userkyc/")
    Call<List<KycResponse>> getKycItem();

    @GET("userkyc/{id}")
    Call<KycResponse> getKycById(@Path("id") int id);


}