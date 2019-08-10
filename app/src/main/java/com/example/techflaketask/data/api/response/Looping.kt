package com.example.techflaketask.data.api.response


import com.google.gson.annotations.SerializedName

data class Looping(
    @SerializedName("mp4")
    var mp4: String,
    @SerializedName("mp4_size")
    var mp4Size: String
)