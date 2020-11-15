package pub.moi.wendao.net


import io.reactivex.Observable
import pub.moi.wendao.model.base.Result
import pub.moi.wendao.model.base.User


import retrofit2.http.*

interface NetService {

//    @GET("http://www.app-Echo.com/api/search/sound")
//    fun search(@Query("keyword") name: String, @Query("page") page: Int, @Query("limit") limit: Int, @Query("src") src: Int): Observable<SearchResult<ArrayList<EchoSong>>>

//    @Headers("X-Requested-With:XMLHttpRequest")
//    @GET("http://www.app-echo.com/sound/api-infos")
//    fun getId(@Query("ids") id: String): Observable<Result<ArrayList<EchoSong>>>

    @POST("/token/check")
    fun checkToken(@Header("authorization") authorization: String): Observable<Result<User>>

    @POST("/user/register")
    fun register(@Body user: User): Observable<Result<User>>

    @FormUrlEncoded
    @POST("/user/login")
    fun login(@Field("userName") userName: String, @Field("passWord") passWord: String): Observable<Result<User>>
    @GET("https://api.weixin.qq.com/sns/jscode2session")
    fun wxLogin(@Query("appid") appid: String, @Query("secret") secret: String, @Query("js_code") js_code: String, @Query("grant_type") srgrant_typec: String): Observable<WxResult>

//    appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code

    companion object {
        //        val BASE_URL = "http://192.168.1.139:6069/"
// val BASE_URL = "http://192.168.1.121:6069/"
//        val BASE_URL = "http://192.168.1.121:6070/"
//        val BASE_URL = "http://127.0.0.1:6070/"
        val BASE_URL = "http://140.143.242.232:1112/"

    }
}

class WxResult{
    var openid:String ?=""
    var session_key:String ?=""
    var unionid:String ?=""
    var errcode:String ?=""
    var errmsg:String ?=""
    override fun toString(): String {
        return "WxResult(openid=$openid, session_key=$session_key, unionid=$unionid, errcode=$errcode, errmsg=$errmsg)"
    }


}
