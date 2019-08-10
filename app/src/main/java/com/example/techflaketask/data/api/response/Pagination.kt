package com.example.techflaketask.data.api.response


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("count")
    var count: Int,
    @SerializedName("offset")
    var offset: Int,
    @SerializedName("total_count")
    var totalCount: Int
)