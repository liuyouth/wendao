package pub.moi.wendao.net


import pub.moi.wendao.model.base.Result
import pub.moi.wendao.model.base.SearchResult
import pub.moi.wendao.model.base.User
import io.reactivex.Observable

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Field

import java.util.ArrayList

/**
 * 网络请求模块
 */
class DataManager() {

    val retrofit = Retrofit.Builder()
            .baseUrl(NetService.BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    val netService = retrofit.create(NetService::class.java)
    internal fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.level = if (true)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return builder.addInterceptor(logging).build()
    }

    /**
     * 校验token
     */
    fun checkToken(authorization: String): Observable<Result<User>> {
        return netService.checkToken(authorization)
    }

    /**
     * 注册用户
     */
    fun register(user: User):  Observable<Result<User>>  {
        return netService.register(user)
    }

    /**
     * 登陆 获取token
     */
    fun login( userName: String,  passWord:String):  Observable<Result<User>>  {
        return netService.login(userName,passWord)
    }

    /**
     * 微信登陆
     */
    fun wxLogin(code:String): Observable<WxResult> {
        return netService.wxLogin("","",code,"authorization_code")
    }
}