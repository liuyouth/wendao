package pub.moi.wendao.model.base

import java.util.ArrayList
import java.util.Date

class SearchResult<T> {

    private var data: T? = null
    private var code: Int = 0
    private var msg: String? = null
    private var status:Int = 0
    private var desc:String ?= null

    fun getDesc(): String? {
        return desc
    }

    fun setDesc(desc: String): SearchResult<T> {
        this.desc = desc
        return this
    }

    fun getData(): T? {
        return data
    }

    fun setData(data: T): SearchResult<T> {
        this.data = data
        return this
    }

    fun getCode(): Int {
        return code
    }

    fun setCode(code: Int): SearchResult<T> {
        this.code = code
        return this
    }

    fun getMsg(): String? {
        return msg
    }

    fun setMsg(msg: String): SearchResult<T> {
        this.msg = msg
        return this
    }
    fun setStatus(status:Int):SearchResult<T> {
        this.status = status;
        return this;
    }
    fun getStatus(): Int? {
        return status
    }
}
