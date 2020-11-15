package pub.moi.wendao.model.base

import java.util.ArrayList
import java.util.Date

class Result<T> {

    private var data: T? = null
    private var code: Int = 0
    private var msg: String? = null
    private var status:Int = 0
    private var desc:T? = null

    fun getDesc(): T? {
        return desc
    }

    fun setDesc(desc: T): Result<T> {
        this.desc = desc
        return this
    }

    fun getData(): T? {
        return data
    }

    fun setData(data: T?): Result<T> {
        this.data = data
        return this
    }

    fun getCode(): Int {
        return code
    }

    fun setCode(code: Int): Result<T> {
        this.code = code
        return this
    }

    fun getMsg(): String? {
        return msg
    }

    fun setMsg(msg: String): Result<T> {
        this.msg = msg
        return this
    }
    fun setStatus(status:Int):Result<T> {
        this.status = status;
        return this;
    }
    fun getStatus(): Int? {
        return status
    }
}
