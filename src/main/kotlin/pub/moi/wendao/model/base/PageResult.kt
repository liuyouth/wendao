package pub.moi.wendao.model.base

import java.util.ArrayList


class PageResult<T> {
    private var data: List<T>? = null
    private var allPage: Int = 0
    private var total: Long = 0
    private var code: Int = 0
    private var status: Int = 0
    private var msg: String? = null
    private var message: String? = null
    private var count: Long = 0

    fun getData(): List<T>? {
        return data
    }

    fun setData(data: List<T>): PageResult<T> {
        this.data = data
        return this
    }

    fun getAllPage(): Int {
        return allPage
    }

    fun setAllPage(allPage: Int): PageResult<T> {
        this.allPage = allPage
        return this
    }

    fun getTotal(): Long {
        return total
    }

    fun setTotal(total: Long): PageResult<T> {
        this.total = total
        this.count = total
        return this
    }

    fun getCode(): Int {
        return code
    }

    fun setCode(code: Int): PageResult<T> {
        this.code = code
        return this
    }
    fun setStatus(status: Int): PageResult<T> {
        this.status = status
        return this
    }

    fun getMsg(): String? {
        return msg
    }

    fun setMsg(msg: String): PageResult<T> {
        this.msg = msg
        this.message = msg
        return this
    }

}

