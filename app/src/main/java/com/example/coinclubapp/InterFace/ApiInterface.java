package com.example.coinclubapp.InterFace;

import com.example.coinclubapp.Response.AddMoneyResponse;
import com.example.coinclubapp.Response.AddOrWithdrawMoneyResponsePost;
import com.example.coinclubapp.Response.AllClubsGet;
import com.example.coinclubapp.Response.AllUserProfilesGet;
import com.example.coinclubapp.Response.BankResponsePost;
import com.example.coinclubapp.Response.ClubInviteResponse;
import com.example.coinclubapp.Response.ClubUserResponse;
import com.example.coinclubapp.Response.CustomerResponse;
import com.example.coinclubapp.Response.IssuesForSpinnerResponseGet;
import com.example.coinclubapp.Response.KycResponse;
import com.example.coinclubapp.Response.ProfileResponse;
import com.example.coinclubapp.Response.RoundCompletedPatchResponse;
import com.example.coinclubapp.Response.UserClubResponse;
import com.example.coinclubapp.Response.UserLoginResponse;
import com.example.coinclubapp.Response.UserRegistrationPost;
import com.example.coinclubapp.Response.WinnerPatchToRound;
import com.example.coinclubapp.Response.WithdrawMoneyResponse;
import com.example.coinclubapp.result.Issue;
import com.example.coinclubapp.result.RoundsResult;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    // this is for signup
    @FormUrlEncoded
    @POST("profile/")
    Call<UserRegistrationPost> registerUser(@Field("full_name") String full_name,
                                            @Field("mobileno") String mobileno,
                                            @Field("city") String city,
                                            @Field("password") String password,
                                            @Field("gender") String gender,
                                            @Field("occupation") String occupation,
                                            @Field("motive") String motive,
                                            @Field("income") String income,
                                            @Field("token") String token,
                                            @Field("uid") String uid,
                                            @Field("monthlycontribution") String monthlycontribution,
                                            @Field("email") String email);

    // this is for login
    @FormUrlEncoded
    @POST("bslogin/")
    Call<UserLoginResponse> loginUser(@Field("mobileno") String mobileno,
                                      @Field("password") String password);


    // this is for getting all users
    @GET("profile/")
    Call<List<AllUserProfilesGet>> getAllUsers();

    // this is for getting all clubs in hot club activity
    @GET("club/")
    Call<List<AllClubsGet>> getAllClubs();

    // this is for getting clubs by id in club activity
    @GET("club/{id}")
    Call<AllClubsGet> getClubById(@Path("id") int id);

    //    http://meetjob.techpanda.art/bankaccount
    @Multipart
    @POST("bankaccount/")
    Call<BankResponsePost> postBankDetails(
            @Part("registeruser") RequestBody registeruser,
            @Part("googlepay_number") RequestBody googlepay_number,
            @Part("paytm_number") RequestBody paytm_number,
            @Part("phonepe_number") RequestBody phonepe_number,
            @Part("bhim_upi") RequestBody bhim_upi,
            @Part MultipartBody.Part document_image
    );

    //    http://meetjob.techpanda.art/bankaccount
    @Multipart
    @POST("bankaccount/")
    Call<BankResponsePost> postBankDetailsWithoutImage(
            @Part("registeruser") RequestBody registeruser,
            @Part("googlepay_number") RequestBody googlepay_number,
            @Part("paytm_number") RequestBody paytm_number,
            @Part("phonepe_number") RequestBody phonepe_number,
            @Part("bhim_upi") RequestBody bhim_upi
    );


    @Multipart
    @POST("userkyc/")
    Call<KycResponse> postKycItems(@Part("full_name") RequestBody full_name,
                                   @Part("address") RequestBody address,
                                   @Part("mobile") RequestBody mobile,
                                   @Part("email") RequestBody email,
                                   @Part("aadharno") RequestBody aadharno,
                                   @Part("panno") RequestBody panno,
                                   @Part("registeruser") RequestBody registeruser,
                                   @Part MultipartBody.Part aadharfrontimg,
                                   @Part MultipartBody.Part aadharbackimg,
                                   @Part MultipartBody.Part panimg
    );

    @Multipart
    @POST("addmoney/")
    Call<AddMoneyResponse> addMoneyPost(
            @Part MultipartBody.Part walletimg,
            @Part("walletamount") RequestBody walletamount,
            @Part("userwallet") RequestBody userwallet
    );

    @FormUrlEncoded
    @POST("withdrawmoney/")
    Call<WithdrawMoneyResponse> withdrawMoneyPost(@Field("userwallet") int userwallet,
                                                  @Field("withdrawamount") String withdrawamount);


    @GET("wallet")
    Call<AddOrWithdrawMoneyResponsePost> getWalletDetails();


    @GET("roundview/")
    Call<List<RoundsResult>> getAllRounds();

    @GET("roundview/{id}")
    Call<RoundsResult> getRoundsById(@Path("id") int id);

    @GET("userkyc/")
    Call<List<KycResponse>> getKycItem();

    @GET("userkyc/{id}")
    Call<KycResponse> getKycById(@Path("id") int id);

    @GET("issuemessages/")
    Call<List<IssuesForSpinnerResponseGet>> getAllIssuesForSpinner();


    @GET("customercare/")
    Call<List<CustomerResponse>> getCustomerIssue();

    @FormUrlEncoded
    @POST("customercare/")
    Call<Issue> postCustomerIssue(@Field("discription") String discription,
                                  @Field("issue") String issue);

    @GET("profile/{id}/")
    Call<ProfileResponse> getProfileItemById(@Path("id") int id);

    @FormUrlEncoded
    @PATCH("profile/{id}/")
    Call<ProfileResponse> changePasswordPatch(@Path("id") int id
            , @Field("password") String password);

    @FormUrlEncoded
    @PATCH("roundview/{id}/")
    Call<RoundCompletedPatchResponse> setRoundCompletedPatchById(@Path("id") int id,
                                                                 @Field("is_completed") Boolean b);


    // getting users by club id
    @GET("clubuser/{id}")
    Call<List<ClubUserResponse>> getUsersByClubId(@Path("id") int id);

    // getting clubs by user id
    @GET("userclub/{id}")
    Call<List<UserClubResponse>> getClubsByUserId(@Path("id") int id);

    @FormUrlEncoded
    @POST("clubinvite/")
    Call<ClubInviteResponse> joinClub(@Field("clubname") int clubname,
                                      @Field("inviteto") int inviteto,
                                      @Field("is_join") boolean is_join);

//
//    @FormUrlEncoded
//    @PATCH("roundview/{id}/")
//    Call<WinnerPatchToRound> patchWinner(@Path("id") int id,
//    @Field("winner") String winner);


//    @Multipart
//    @PATCH("profile/{id}")
//    Call<PatchProfileResponse> patchProfileById(@Path("id") int id,
//                                                @Part MultipartBody.Part profileimg);
}