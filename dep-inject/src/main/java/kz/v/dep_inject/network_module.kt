package kz.v.dep_inject

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.dsl.module

private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
private const val BASE_URL = "https://testdriver.iwayex.com"

val networkModule = module {
    single { TokenInterceptor() }
    single { createOkHttpClient(tokenInterceptor = get()) }
    single { provideGson() }
    single { createRetrofit(okHttpClient = get(), gson = get()) }
}

fun createOkHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(tokenInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient,  gson: Gson): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()
}

fun provideGson(): Gson {
    return GsonBuilder()
        .setDateFormat(DATE_FORMAT)
        .create()
}
