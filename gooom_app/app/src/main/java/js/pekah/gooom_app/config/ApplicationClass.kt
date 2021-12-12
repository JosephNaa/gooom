package js.pekah.gooom_app.config

import android.app.Application
import js.pekah.gooom_app.util.SharedPreferencesUtil
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TAG = "ApplicationClass_gooom"
class ApplicationClass : Application() {
    companion object {
        const val SERVER_URL = "http://10.0.2.2:8080/"

        lateinit var sharedPreferencesUtil: SharedPreferencesUtil
        lateinit var retrofit: Retrofit
    }

    override fun onCreate() {
        super.onCreate()

        sharedPreferencesUtil = SharedPreferencesUtil(applicationContext)

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS).build()

        retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    }
}