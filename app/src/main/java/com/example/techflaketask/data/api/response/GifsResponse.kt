package com.example.techflaketask.data.api.response


import com.google.gson.annotations.SerializedName

data class GifsResponse(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("meta")
    var meta: Meta,
    @SerializedName("pagination")
    var pagination: Pagination
)