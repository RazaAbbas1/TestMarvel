package com.sample.app.network.models

class BaseResponse<T>  (
    var code   : Int?    = null,
    var status : String? = null,
    var data   : T?      = null
)