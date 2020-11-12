package pub.moi.wendao.utils

import com.google.gson.Gson
import org.apache.log4j.Logger


/**
 * Date工具类
 * @author spiderman
 */
object DateUtil {

    public val gson = Gson()
    val inLogger= Logger.getLogger("in")
    val outLogger= Logger.getLogger("out")
    //    public final  fun  <T,R> jsonTo(t:T) : R{
//        gson.fromJson<R>(gson.toJson(t),)
//    }
    private fun showTextToast(msg: String) {

    }

    fun <T,R> toJsonFor(user: T, jClass: Class<R>): R {
        return gson.fromJson(gson.toJson(user),jClass)
    }
    val TODO_STATUS_CODES :Array<String> = arrayOf("0","1","2","3","4");


}
