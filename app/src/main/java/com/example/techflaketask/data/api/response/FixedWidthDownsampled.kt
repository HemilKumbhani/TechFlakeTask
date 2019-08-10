package com.example.techflaketask.data.api.response


import com.google.gson.annotations.SerializedName

data class FixedWidthDownsampled(
    @SerializedName("height")
    var height: String,
    @SerializedName("size")
    var size: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("webp")
    var webp: String,
    @SerializedName("webp_size")
    var webpSize: String,
    @SerializedName("width")
    var width: String
)