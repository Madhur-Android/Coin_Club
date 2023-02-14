package com.example.coinclubapp.result;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Id {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("mobileno")
    @Expose
    private String mobileno;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("occupation")
    @Expose
    private String occupation;
    @SerializedName("motive")
    @Expose
    private String motive;
    @SerializedName("income")
    @Expose
    private String income;
    @SerializedName("monthlycontribution")
    @Expose
    private String monthlycontribution;
    @SerializedName("profileimg")
    @Expose
    private String profileimg;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("alternateno")
    @Expose
    private String alternateno;
    @SerializedName("create_at")
    @Expose
    private String createAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getMonthlycontribution() {
        return monthlycontribution;
    }

    public void setMonthlycontribution(String monthlycontribution) {
        this.monthlycontribution = monthlycontribution;
    }

    public String getProfileimg() {
        return profileimg;
    }

    public void setProfileimg(String profileimg) {
        this.profileimg = profileimg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlternateno() {
        return alternateno;
    }

    public void setAlternateno(String alternateno) {
        this.alternateno = alternateno;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}
