package com.designmaster.sukar.util


interface ApiCallListener {
    fun onSuccess(response: String, requestCode: Int)
    fun onError(response: String, requestCode: Int)
}


