package com.setnameinc.etude.app.api.di

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiInjectionModule {

    private const val BASE_URL = "EMPTY"

    val module = Kodein.Module(ApiInjectionModule.javaClass.name) {

        bind<Retrofit>() with singleton {
            val okHttpClient = instance<OkHttpClient>()

            val gson = GsonBuilder()
                .setLenient()
                .create()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        bind<OkHttpClient>() with provider {
            OkHttpClient.Builder()
                .apply {
                    connectTimeout(80, TimeUnit.SECONDS)
                    readTimeout(60, TimeUnit.SECONDS)
                    retryOnConnectionFailure(true)
                }
                .build()
        }

    }
}