package com.cts.galaxy.api

import com.cts.galaxy.api.models.GalaxyDTO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Praveen Kumar Sugumaran
 */
interface ApiService {

    @GET("search")
    suspend fun getGalaxyCollection(
        @Query("q") baseQuery: String,
        @Query("media_type") mediaType: String,
        @Query("year_start") yearStart: String,
        @Query("year_end") yearEnd: String
    ): GalaxyDTO

    companion object {
        private const val BASE_URL = "https://images-api.nasa.gov/"
        var retrofitService: ApiService? = null
        fun getInstance(): ApiService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getLoggerClient())
                    .build()
                retrofitService = retrofit.create(ApiService::class.java)
            }
            return retrofitService!!
        }

        private fun getLoggerClient(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }

    }
}