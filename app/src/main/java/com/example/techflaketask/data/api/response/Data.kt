package com.example.techflaketask.data.api.response


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("analytics")
    var analytics: Analytics,
    @SerializedName("bitly_gif_url")
    var bitlyGifUrl: String,
    @SerializedName("bitly_url")
    var bitlyUrl: String,
    @SerializedName("content_url")
    var contentUrl: String,
    @SerializedName("embed_url")
    var embedUrl: String,
    @SerializedName("id")
    var id: String,
    @SerializedName("images")
    var images: Images,
    @SerializedName("import_datetime")
    var importDatetime: String,
    @SerializedName("is_sticker")
    var isSticker: Int,
    @SerializedName("rating")
    var rating: String,
    @SerializedName("slug")
    var slug: String,
    @SerializedName("source")
    var source: String,
    @SerializedName("source_post_url")
    var sourcePostUrl: String,
    @SerializedName("source_tld")
    var sourceTld: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("trending_datetime")
    var trendingDatetime: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("user")
    var user: User,
    @SerializedName("username")
    var username: String,
    var isUpvote: Int,
    var isDownVote: Int


)