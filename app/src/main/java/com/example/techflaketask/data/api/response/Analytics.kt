package com.example.techflaketask.data.api.response


import com.google.gson.annotations.SerializedName

data class Analytics(
    @SerializedName("onclick")
    var onclick: Onclick,
    @SerializedName("onload")
    var onload: Onload,
    @SerializedName("onsent")
    var onsent: Onsent
)