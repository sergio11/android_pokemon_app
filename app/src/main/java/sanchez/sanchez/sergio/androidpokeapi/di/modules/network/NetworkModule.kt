package sanchez.sanchez.sergio.androidpokeapi.di.modules.network

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import okhttp3.Cache
import okhttp3.Interceptor
import sanchez.sanchez.sergio.androidpokeapi.di.scopes.PerApplication
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.interceptors.ConnectivityInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Named

/**
 * App Network Module
 */
@Module
class NetworkModule {

    /**
     * Provide Network Interceptors
     */
    @Provides
    @ElementsIntoSet
    @PerApplication
    @Named("networkInterceptors")
    fun provideNetworkInterceptors(): Set<Interceptor> =
        setOf<Interceptor>(StethoInterceptor())

    /**
     * Provide Request Interceptors
     */
    @Provides
    @ElementsIntoSet
    @PerApplication
    @Named("requestInterceptors")
    fun provideRequestInterceptors(
        context: Context): Set<Interceptor> =
        setOf(
            ConnectivityInterceptor(context)
        )


    /**
     * Provide Ktor Client
     */
    @Provides
    @PerApplication
    fun provideKtor(
        context: Context,
        @Named("networkInterceptors") networkInterceptors: Set<@JvmSuppressWildcards Interceptor>,
        @Named("requestInterceptors") requestInterceptors: Set<@JvmSuppressWildcards Interceptor>) =
        HttpClient(OkHttp) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
            // Logging
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Timber.d("KTOR -> %s", message)
                    }
                }
                level = LogLevel.ALL
            }
            // Configure OkHTTP Interceptors
            engine {
                config {
                    connectTimeout(2, TimeUnit.MINUTES)
                    readTimeout(2, TimeUnit.MINUTES)
                    writeTimeout(2, TimeUnit.MINUTES)
                    retryOnConnectionFailure(true)
                    cache(Cache(context.cacheDir, DEFAULT_CACHE_SIZE))
                }
                networkInterceptors.forEach {
                    addNetworkInterceptor(it)
                }
                requestInterceptors.forEach {
                    addInterceptor(it)
                }
            }
    }

    companion object {
        const val DEFAULT_CACHE_SIZE: Long =  10 * 1024 * 1024 // 10 MB
    }
}