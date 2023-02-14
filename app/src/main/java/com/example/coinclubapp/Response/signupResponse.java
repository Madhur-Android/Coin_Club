package com.example.coinclubapp.Response;

import com.example.coinclubapp.result.Id;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class signupResponse {

    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("id")
    @Expose
    private Id id;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }
}
