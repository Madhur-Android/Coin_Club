package com.example.coinclubapp.Response;

import com.example.coinclubapp.result.Userclub;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserClubResponse {
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("userclub")
    @Expose
    private List<Userclub> userclub;
    @SerializedName("usermobileno")
    @Expose
    private String usermobileno;
    @SerializedName("usercity")
    @Expose
    private String usercity;
    @SerializedName("useroccupation")
    @Expose
    private String useroccupation;
    @SerializedName("usermotive")
    @Expose
    private String usermotive;
    @SerializedName("userincome")
    @Expose
    private String userincome;
    @SerializedName("userwallet_amount")
    @Expose
    private String userwalletAmount;
    @SerializedName("usermonthlycontribution")
    @Expose
    private String usermonthlycontribution;
    @SerializedName("userprofileimg")
    @Expose
    private String userprofileimg;
    @SerializedName("useremail")
    @Expose
    private String useremail;
    @SerializedName("useralternateno")
    @Expose
    private String useralternateno;
    @SerializedName("usercreate_at")
    @Expose
    private String usercreateAt;

    private String total_subscription;

    public String getTotal_subscription() {
        return total_subscription;
    }

    public void setTotal_subscription(String total_subscription) {
        this.total_subscription = total_subscription;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Userclub> getUserclub() {
        return userclub;
    }

    public void setUserclub(List<Userclub> userclub) {
        this.userclub = userclub;
    }

    public String getUsermobileno() {
        return usermobileno;
    }

    public void setUsermobileno(String usermobileno) {
        this.usermobileno = usermobileno;
    }

    public String getUsercity() {
        return usercity;
    }

    public void setUsercity(String usercity) {
        this.usercity = usercity;
    }

    public String getUseroccupation() {
        return useroccupation;
    }

    public void setUseroccupation(String useroccupation) {
        this.useroccupation = useroccupation;
    }

    public String getUsermotive() {
        return usermotive;
    }

    public void setUsermotive(String usermotive) {
        this.usermotive = usermotive;
    }

    public String getUserincome() {
        return userincome;
    }

    public void setUserincome(String userincome) {
        this.userincome = userincome;
    }

    public String getUserwalletAmount() {
        return userwalletAmount;
    }

    public void setUserwalletAmount(String userwalletAmount) {
        this.userwalletAmount = userwalletAmount;
    }

    public String getUsermonthlycontribution() {
        return usermonthlycontribution;
    }

    public void setUsermonthlycontribution(String usermonthlycontribution) {
        this.usermonthlycontribution = usermonthlycontribution;
    }

    public String getUserprofileimg() {
        return userprofileimg;
    }

    public void setUserprofileimg(String userprofileimg) {
        this.userprofileimg = userprofileimg;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUseralternateno() {
        return useralternateno;
    }

    public void setUseralternateno(String useralternateno) {
        this.useralternateno = useralternateno;
    }

    public String getUsercreateAt() {
        return usercreateAt;
    }

    public void setUsercreateAt(String usercreateAt) {
        this.usercreateAt = usercreateAt;
    }

}
