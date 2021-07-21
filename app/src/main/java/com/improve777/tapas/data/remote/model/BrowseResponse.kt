package com.improve777.tapas.data.remote.model


import com.google.gson.annotations.SerializedName

data class BrowseResponse(
    @SerializedName("pagination")
    val pagination: PaginationResponse?,
    @SerializedName("series")
    val series: List<SeriesResponse>
)

data class PaginationResponse(
    @SerializedName("page")
    val page: Int?, // 2
    @SerializedName("has_next")
    val hasNext: Boolean? // true
)

data class SeriesResponse(
    @SerializedName("id")
    val id: Int?, // 202466
    @SerializedName("title")
    val title: String?, // The Goddess of Healing
    @SerializedName("type")
    val type: String?, // COMICS
    @SerializedName("sale_type")
    val saleType: String?, // PAID
    @SerializedName("thumb")
    val thumb: ThumbResponse?,
    @SerializedName("book_cover_url")
    val bookCoverUrl: String?, // https://d30womf5coomej.cloudfront.net/sa/96/85c6a6d3-3004-487e-88a0-c525d50e64db.jpg
    @SerializedName("creators")
    val creators: List<CreatorResponse?>?,
    @SerializedName("age_rating")
    val ageRating: String?, // null
    @SerializedName("rgb_hex")
    val rgbHex: String?, // #ebdedf
    @SerializedName("restricted")
    val restricted: Boolean?, // false
    @SerializedName("restricted_msg")
    val restrictedMsg: String?, // null
    @SerializedName("on_sale")
    val onSale: Boolean?, // false
    @SerializedName("discount_rate")
    val discountRate: Int?, // 0
    @SerializedName("sale_start_date")
    val saleStartDate: String?, // null
    @SerializedName("sale_end_date")
    val saleEndDate: String?, // null
    @SerializedName("subscribe_cnt")
    val subscribeCnt: Int?, // 16764
    @SerializedName("like_cnt")
    val likeCnt: Int?, // 41876
    @SerializedName("view_cnt")
    val viewCnt: Int?, // 457157
    @SerializedName("up")
    val up: Boolean?, // true
    @SerializedName("blurb")
    val blurb: String?, // To stop a horrific fate, can she discover the skills and power to light the way?
    @SerializedName("sub_title")
    val subTitle: String?, // 14 hours ago
    @SerializedName("genre")
    val genre: GenreResponse?,
    @SerializedName("rect_banner_url")
    val rectBannerUrl: String? // null
)

data class CreatorResponse(
    @SerializedName("id")
    val id: Int?, // 6748001
    @SerializedName("uname")
    val uname: String?, // KuaikanComics
    @SerializedName("display_name")
    val displayName: String?, // Kuaikan Comics
    @SerializedName("profile_pic_url")
    val profilePicUrl: String?, // https://d30womf5coomej.cloudfront.net/ua/8a/93283837-bd00-4cdb-902a-c29cb9583b89.png
    @SerializedName("joined_creator_tip")
    val joinedCreatorTip: Boolean? // false
)

