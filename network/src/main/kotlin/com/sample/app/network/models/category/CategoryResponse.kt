package com.sample.app.network.models.category

import com.google.gson.annotations.SerializedName

class CategoryResponse (
    @SerializedName("code"            ) var code            : Int?    = null,
    @SerializedName("status"          ) var status          : String? = null,
    @SerializedName("copyright"       ) var copyright       : String? = null,
    @SerializedName("attributionText" ) var attributionText : String? = null,
    @SerializedName("attributionHTML" ) var attributionHTML : String? = null,
    @SerializedName("etag"            ) var etag            : String? = null,
    @SerializedName("data"            ) var data            : Data?   = Data()
)