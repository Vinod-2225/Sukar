package com.designmaster.sukar.util

import android.content.Context
import android.net.ConnectivityManager
import com.designmaster.sukar.R
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.lang.reflect.Type

class RetrofitApiCall {
    companion object {
        fun hitApi(
            apiCall: Call<ResponseBody>,
            apiCallListener: ApiCallListener,
            apiRequestCode: Int
        ) {
            val connectivity =
                MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivity.activeNetworkInfo
            if (networkInfo == null || !networkInfo.isAvailable || !networkInfo.isConnected) {
                apiCallListener.onError(
                    MyApplication.getContext().getString(R.string.msg_no_internet),
                    apiRequestCode
                )
                return
            }
            apiCall.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    AppLogger.e("Api Url: ", call.request().url.toString())
                    try {
                        val responseBody = response.body()!!.string().trim { it <= ' ' }
                        AppLogger.e("onResponse", responseBody)
                        apiCallListener.onSuccess(responseBody, apiRequestCode)
                    } catch (e: Exception) {
                        AppLogger.e("onResponse Exception", e.message.toString())
                        apiCallListener.onError(e.message.toString(), apiRequestCode)
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    AppLogger.e("onFailure Exception", t.message.toString())
                    apiCallListener.onError(t.message.toString(), apiRequestCode)
                }
            })
        }

        fun <T> getPayloadAsList(listType: Type, jsonData: String): List<T>? {
            return Gson().fromJson<List<T>>(jsonData, listType)
        }

        fun <T> getPayload(payloadClass: Class<T>, jsonData: String): T {
            return Gson().fromJson(jsonData, payloadClass)
        }
    }
}


