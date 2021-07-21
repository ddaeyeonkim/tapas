package com.improve777.tapas.data.remote.model


import com.google.gson.annotations.SerializedName

data class SeriesInfoResponse(
    @SerializedName("id")
    val id: Int?, // 202466
    @SerializedName("title")
    val title: String?, // The Goddess of Healing
    @SerializedName("description")
    val description: String?, // After losing her mother and her own life, Jiuqing gets a chance to make things right! Now she’ll stop at nothing to uncover the truth and to save her beloved mother. No longer playing the part of the dutiful daughter blindly following orders or the mild girl preparing to be an obedient wife, she’s learning medicine, the healing arts, and what it means to fall in love. As she discovers more in a few months than she had in her last lifetime, where will this newfound fire within her lead to? 
    @SerializedName("colophon")
    val colophon: String?, // The Goddess of HealingStory by JIANGWUWUArt by HUQIPublished by TAPAS MEDIA 2021Provided by Kuaikan Comics
    @SerializedName("type")
    val type: String?, // COMICS
    @SerializedName("sale_type")
    val saleType: String?, // PAID
    @SerializedName("episode_cnt")
    val episodeCnt: Int?, // 36
    @SerializedName("human_url")
    val humanUrl: String?, // the-goddess-of-healing
    @SerializedName("thumb")
    val thumb: ThumbResponse?,
    @SerializedName("book_cover_url")
    val bookCoverUrl: String?, // https://d30womf5coomej.cloudfront.net/sa/96/85c6a6d3-3004-487e-88a0-c525d50e64db.jpg
    @SerializedName("background_url")
    val backgroundUrl: String?, // https://d30womf5coomej.cloudfront.net/sa/96/85c6a6d3-3004-487e-88a0-c525d50e64db.jpg
    @SerializedName("creators")
    val creators: List<SeriesInfoCreatorResponse>?,
    @SerializedName("age_rating")
    val ageRating: String?, // null
    @SerializedName("rgb_hex")
    val rgbHex: String?, // #ebdedf
    @SerializedName("ad_campaign")
    val adCampaign: String?, // null
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
    @SerializedName("display_ad")
    val displayAd: Boolean?, // false
    @SerializedName("available_impression")
    val availableImpression: Boolean?, // false
    @SerializedName("private_reading")
    val privateReading: Boolean?, // false
    @SerializedName("bookmarked")
    val bookmarked: Boolean?, // false
    @SerializedName("last_read_episode_id")
    val lastReadEpisodeId: Int?, // 2095701
    @SerializedName("last_read_episode_scene")
    val lastReadEpisodeScene: Int?, // 1
    @SerializedName("last_read_episode_title")
    val lastReadEpisodeTitle: String?, // Prologue
    @SerializedName("last_read_episode_date")
    val lastReadEpisodeDate: String?, // null
    @SerializedName("last_read_episode_thumb_url")
    val lastReadEpisodeThumbUrl: String?, // https://d30womf5coomej.cloudfront.net/sa/1f/9a360011-e2dd-49f5-9b20-87df205cc0be.png
    @SerializedName("claimed")
    val claimed: Boolean?, // false
    @SerializedName("has_new_episode")
    val hasNewEpisode: Boolean?, // false
    @SerializedName("notification_on")
    val notificationOn: Boolean?, // false
    @SerializedName("related_series")
    val relatedSeries: List<RelatedSeriesInfoResponse>?,
    @SerializedName("subscribe_cnt")
    val subscribeCnt: Int?, // 16791
    @SerializedName("like_cnt")
    val likeCnt: Int?, // 41942
    @SerializedName("comment_cnt")
    val commentCnt: Int?, // 865
    @SerializedName("view_cnt")
    val viewCnt: Int?, // 458094
    @SerializedName("completed")
    val completed: Boolean?, // false
    @SerializedName("review_rating")
    val reviewRating: ReviewRatingResponse?,
    @SerializedName("key_timer")
    val keyTimer: String?, // null
    @SerializedName("wop_key_timer")
    val wopKeyTimer: String?, // null
    @SerializedName("updated_date")
    val updatedDate: String?, // 2021-05-19T16:00:00Z
    @SerializedName("last_episode_updated_date")
    val lastEpisodeUpdatedDate: String?, // 2021-05-19T16:00:00Z
    @SerializedName("last_episode_modified_date")
    val lastEpisodeModifiedDate: String?, // 2021-05-19T16:00:00Z
    @SerializedName("last_episode_scheduled_date")
    val lastEpisodeScheduledDate: String?, // null
    @SerializedName("merch_url")
    val merchUrl: String?, // null
    @SerializedName("sp_like_cnt")
    val spLikeCnt: Int?, // 0
    @SerializedName("original")
    val original: Boolean?, // false
    @SerializedName("up")
    val up: Boolean?, // true
    @SerializedName("blurb")
    val blurb: String?, // To stop a horrific fate, can she discover the skills and power to light the way?
    @SerializedName("must_pay_cnt")
    val mustPayCnt: Int?, // 0
    @SerializedName("wop_interval")
    val wopInterval: Int?, // 0
    @SerializedName("sub_title")
    val subTitle: String?, // Kuaikan Comics
    @SerializedName("unused_key_cnt")
    val unusedKeyCnt: Int?, // 0
    @SerializedName("new_episode_cnt")
    val newEpisodeCnt: Int?, // 0
    @SerializedName("early_access_ep_cnt")
    val earlyAccessEpCnt: Int?, // 0
    @SerializedName("genre")
    val genre: GenreResponse?,
    @SerializedName("supporting_ad")
    val supportingAd: Any?, // null
    @SerializedName("supporting_ad_link")
    val supportingAdLink: String?,
    @SerializedName("publish_days")
    val publishDays: List<String>?,
    @SerializedName("tags")
    val tags: List<String?>?,
    @SerializedName("desc_order")
    val descOrder: Boolean?, // false
    @SerializedName("rect_banner_url")
    val rectBannerUrl: String?, // null
    @SerializedName("master_key_banner")
    val masterKeyBanner: Boolean?, // false
    @SerializedName("selected_collection_id")
    val selectedCollectionId: String?, // null
    @SerializedName("announcement")
    val announcement: AnnouncementResponse?,
)

data class SeriesInfoCreatorResponse(
    @SerializedName("id")
    val id: Int?, // 6748001
    @SerializedName("uname")
    val uname: String?, // KuaikanComics
    @SerializedName("display_name")
    val displayName: String?, // Kuaikan Comics
    @SerializedName("profile_pic_url")
    val profilePicUrl: String?, // https://d30womf5coomej.cloudfront.net/ua/8a/93283837-bd00-4cdb-902a-c29cb9583b89.png
    @SerializedName("joined_creator_tip")
    val joinedCreatorTip: Boolean?, // false
    @SerializedName("support_banner")
    val supportBanner: String?, // null
    @SerializedName("staff")
    val staff: Boolean?, // false
)

data class ReviewRatingResponse(
    @SerializedName("id")
    val id: String?, // null
    @SerializedName("cnt")
    val cnt: Int?, // 0
    @SerializedName("rating")
    val rating: Int?, // 0
)

data class AnnouncementResponse(
    @SerializedName("title")
    val title: String?, // 5 unlocked episodes!
    @SerializedName("body")
    val body: String?, //  Enjoy 5 additional unlocked episodes until 5/25!
    @SerializedName("start_date")
    val startDate: String?, // 2021-05-19T07:00:00Z
    @SerializedName("end_date")
    val endDate: String?, // 2021-05-25T07:00:00Z
)

data class RelatedSeriesInfoResponse(
    @SerializedName("id")
    val id: Int?, // 199294
    @SerializedName("title")
    val title: String?, // Concubine Walkthrough
    @SerializedName("book_cover_url")
    val bookCoverUrl: String?, // https://d30womf5coomej.cloudfront.net/sa/fa/c6957371-6b79-4f91-8d9e-37adccbd493d.jpg
    @SerializedName("type")
    val type: String?, // COMICS
    @SerializedName("sale_type")
    val saleType: String?, // PAID
    @SerializedName("thumb")
    val thumb: ThumbResponse?,
    @SerializedName("creators")
    val creators: List<SeriesInfoCreatorResponse>?,
    @SerializedName("blurb")
    val blurb: String?, // The only way out is to become the Empress. But Yona isn’t the only one playing...
    @SerializedName("subscribe_cnt")
    val subscribeCnt: Int?, // 11905
    @SerializedName("like_cnt")
    val likeCnt: Int?, // 15112
    @SerializedName("view_cnt")
    val viewCnt: Int?, // 126675
    @SerializedName("genre")
    val genre: GenreResponse?,
    @SerializedName("bookmarked")
    val bookmarked: Boolean?, // false
    @SerializedName("first_ep_id")
    val firstEpId: String?, // null
    @SerializedName("up")
    val up: Boolean?, // true
)