package com.example.techflaketask.data.api.response


import com.google.gson.annotations.SerializedName

data class WStill(
    @SerializedName("height")
    var height: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("width")
    var width: String
)