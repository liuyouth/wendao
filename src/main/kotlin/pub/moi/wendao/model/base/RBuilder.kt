package pub.moi.wendao.model.base

import pub.moi.wendao.utils.DateUtil.gson
import pub.moi.wendao.utils.DateUtil.outLogger
import java.util.ArrayList

object RBuilder {
    fun <T> seccess(): Result<T> {
        return Result<T>().setCode(200).setStatus(200).setMsg("请求成功！")
    }
    fun <T> seccess(data: T?): Result<T> {
        val result = Result<T>().setCode(200).setStatus(200).setMsg("请求成功！").setData(data)
        outLogger.info("return "+gson.toJson(data))
        return result
    }
    fun <T> failed(data: T): Result<T> {
        return Result<T>().setCode(201).setStatus(201).setMsg("请求失败！").setData(data)
    }
    fun <T> failed(msg: String): Result<T> {
        return Result<T>().setCode(201).setStatus(201).setMsg(msg)
    }
    fun <T> seccess(data: List<T>, total: Long, allPage: Int): PageResult<T> {
        outLogger.info("return "+gson.toJson(data))
        return PageResult<T>().setCode(200).setStatus(200).setMsg("请求成功！").setData(data).setTotal(total).setAllPage(allPage)
    }

}

