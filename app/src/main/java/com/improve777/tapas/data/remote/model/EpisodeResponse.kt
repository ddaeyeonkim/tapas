package com.improve777.tapas.data.remote.model


import com.google.gson.annotations.SerializedName

data class EpisodeResponse(
    @SerializedName("id")
    val id: Int?, // 2025061
    @SerializedName("title")
    val title: String?, // 0. Prologue
    @SerializedName("scene")
    val scene: Int?, // 1
    @SerializedName("free")
    val free: Boolean?, // true
    @SerializedName("downloadable")
    val downloadable: Boolean?, // true
    @SerializedName("thumb")
    val thumb: ThumbResponse?,
    @SerializedName("created_date")
    val createdDate: String?, // 2021-02-16T18:18:13Z
    @SerializedName("nsfw")
    val nsfw: Boolean?, // false
    @SerializedName("read")
    val read: Boolean?, // false
    @SerializedName("unlocked")
    val unlocked: Boolean?, // false
    @SerializedName("nu")
    val nu: Boolean?, // false
    @SerializedName("early_access")
    val earlyAccess: Boolean?, // false
    @SerializedName("support_supporting_ad")
    val supportSupportingAd: Boolean?, // false
    @SerializedName("view_cnt")
    val viewCnt: Int?, // 88005
    @SerializedName("scheduled_date")
    val scheduledDate: String?, // null
)