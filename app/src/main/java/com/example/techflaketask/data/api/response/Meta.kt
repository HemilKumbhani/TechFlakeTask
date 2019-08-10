package com.example.techflaketask.data.api.response


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("msg")
    var msg: String,
    @SerializedName("response_id")
    var responseId: String,
    @SerializedName("status")
    var status: Int
)