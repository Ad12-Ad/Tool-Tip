package com.example.tooltip.core.network

import org.json.JSONObject
import retrofit2.Response
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
): NetworkResult<T> {
    return try {
        val result = apiCall()

        if (result.isSuccessful){
            result.body()?.let {
                NetworkResult.Success(it)
            }?: NetworkResult.UnknownError
        } else{
            var parsedMessage = result.message()

            val errorBodyString = result.errorBody()?.string()

            if (!errorBodyString.isNullOrEmpty()) {
                try {
                    val jsonObject = JSONObject(errorBodyString)
                    if (jsonObject.has("message")) {
                        parsedMessage = jsonObject.getString("message")
                    }
                } catch (e: Exception) {
                }
            }
            NetworkResult.ApiError(
                code = result.code(),
                errorMessage = parsedMessage
            )
        }
    } catch (e: IOException){
        NetworkResult.NetworkError("Internet Connection Issue")
    } catch (e: Exception){
        NetworkResult.UnknownError
    }
}