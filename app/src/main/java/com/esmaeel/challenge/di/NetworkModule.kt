package com.esmaeel.challenge.di

import com.esmaeel.challenge.BuildConfig
import com.esmaeel.challenge.common.base.INJECT_API_KEY
import com.esmaeel.challenge.data.remote.MoviesService
import com.esmaeel.challenge.data.remote.models.adapters.DateJsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Invocation
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(DateJsonAdapter()).build()


    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit.Builder): MoviesService = retrofit
        .baseUrl(BuildConfig.BASE_URL)
        .build()
        .create(MoviesService::class.java)

    @Provides
    @Singleton
    fun provideRetrofitBuilder(client: OkHttpClient, moshi: Moshi) = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        )

    @Provides
    @Singleton
    fun provideAuthInjectionInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val method = chain.request().tag(Invocation::class.java)!!.method()
            val builder = original.newBuilder()

            // inject language key
            val newUrlBuilder = original.url.newBuilder()
                .addQueryParameter(MoviesService.Language, MoviesService.EN)

            // inject api key
            if (method.isAnnotationPresent(INJECT_API_KEY::class.java)) {
                builder.url(
                    newUrlBuilder.addQueryParameter(
                        MoviesService.AUTHORIZATION,
                        BuildConfig.API_KEY
                    ).build()
                )
            }
            // get system/app local depending of application business
            builder.header(MoviesService.Language, MoviesService.EN)
            chain.proceed(builder.method(original.method, original.body).build())
        }
    }


    @Provides
    @Singleton
    fun provideHttpClient(
        logger: HttpLoggingInterceptor,
        keysInjector: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(keysInjector)
            .addInterceptor(logger)
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()
    }

}
