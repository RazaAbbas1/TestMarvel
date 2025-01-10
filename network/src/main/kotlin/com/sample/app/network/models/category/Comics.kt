package com.sample.app.network.models.category

import com.google.gson.annotations.SerializedName


data class Comics (

  @SerializedName("available"     ) var available     : Int?             = null,
  @SerializedName("collectionURI" ) var collectionURI : String?          = null,
  @SerializedName("items"         ) var items         : ArrayList<Items> = arrayListOf(),
  @SerializedName("returned"      ) var returned      : Int?             = null

)