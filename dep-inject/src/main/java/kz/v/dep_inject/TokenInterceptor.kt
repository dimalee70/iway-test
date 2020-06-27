package kz.v.dep_inject

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException

class TokenInterceptor : Interceptor {

    var token: String = ""

    companion object {
        private const val AUTH_HEADER_KEY = "Authorization"
        private const val AUTH_HEADER_VALUE = "Bearer"

        private const val LOCALE_HEADER_KEY = "Accept-Language"
        private const val CONTENT_TYPE_HEADER_KEY = "Content-Type"
        private const val APPLICATION_JSON_HEADER_VALUE = "application/json"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val original = chain.request()

            val originalHttpUrl = original.url.toString()
            val requestBuilder = original.newBuilder()
                .addHeader(CONTENT_TYPE_HEADER_KEY, APPLICATION_JSON_HEADER_VALUE)
                .addHeader("platform", "android")
                .addHeader("appVersion", BuildConfig.VERSION_NAME)
                .addHeader("theme", "light")
                .addHeader("User-Agent", "${System.getProperty("http.agent")}")
                .addHeader(AUTH_HEADER_KEY, "$AUTH_HEADER_VALUE $token")
                .url(originalHttpUrl)
            return chain.proceed(requestBuilder.build())
        } catch (ex: IOException) {
            ex.printStackTrace()
            return Response.Builder()
                .code(500)
                .body(
                    "{\"key\":[\"500\"]}"
                        .toResponseBody("application/json".toMediaTypeOrNull())
                )
                .protocol(Protocol.HTTP_2)
                .message("Timeout")
                .request(chain.request())
                .build()
        }
    }
}