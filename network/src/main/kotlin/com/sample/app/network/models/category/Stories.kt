package com.sample.app.network.models.category

import com.google.gson.annotations.SerializedName
import com.sample.app.network.models.category.Items


data class Stories (

    @SerializedName("available"     ) var available     : Int?             = null,
    @SerializedName("collectionURI" ) var collectionURI : String?          = null,
    @SerializedName("items"         ) var items         : ArrayList<Items> = arrayListOf(),
    @SerializedName("returned"      ) var returned      : Int?             = null

)