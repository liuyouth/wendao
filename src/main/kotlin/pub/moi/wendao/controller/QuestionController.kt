package pub.moi.wendao.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import pub.moi.wendao.db.QuestionRepository

import pub.moi.wendao.java.authorization.annotation.Authorization
import pub.moi.wendao.java.authorization.annotation.CurrentUser
import pub.moi.wendao.model.base.Question
import pub.moi.wendao.model.base.RBuilder
import pub.moi.wendao.model.base.Result
import pub.moi.wendao.model.base.User
import pub.moi.wendao.service.QuestionService
import pub.moi.wendao.utils.Utils
import javax.websocket.server.PathParam


@RestController
@RequestMapping("/qu")
public class QuestionController{

    @Autowired
    lateinit var repository: QuestionRepository

    @Autowired
    @Qualifier("QuestionService")
    lateinit var service : QuestionService

    /**
     * 列表接口（附带模糊搜索功能）
     */
    @GetMapping("/")
    fun  get(
             @RequestParam(name = "searchStr", defaultValue = "")  searchStr:String,
             @PathParam("currentPage") currentPage:Int,
             @PathParam("pageSize") pageSize:Int): Page<Question> {
        val pageable: Pageable = PageRequest.of(currentPage, pageSize)
        return  service.search(pageable,searchStr)
    }
    /**
     * 根据用户编号获取当前用户的问题列表
     * 暂无隐秘数据之后再说
     */
    @GetMapping("/userNo")
    fun  get(@PathParam("userNo")  userNo:Long,
             @PathParam("currentPage") currentPage:Int,
             @PathParam("pageSize") pageSize:Int): Page<Question> {
        val pageable: Pageable = PageRequest.of(currentPage, pageSize)
        return  service.getList(pageable,userNo)
    }

    /**
     * 添加
     */
    @PostMapping("/")
    @Authorization
    @ResponseBody
    fun get(@CurrentUser user: User, @RequestBody body: Question): Result<Question> {
        body.postUserNo = user.number
        body.number = Utils.getOnlyNumber(fun(no:Long): Boolean {
            return repository.findByNumber(no)==null
        })
        val qu = repository.save(body)
        return RBuilder.seccess(qu)
    }

    /**
     * 删除
     */
    @DeleteMapping("/{no}")
    @Authorization
    @ResponseBody
    fun get(@CurrentUser user: User, @PathVariable("no") no: Long): Result<Question> {
        var qu = repository.findByNumber(no)
        if (qu==null)
            return RBuilder.failed("编号不存在")
        if (qu.postUserNo != user.number)
            return RBuilder.failed("权限错误")
        qu.isDel =true
        qu = repository.save(qu)
        return RBuilder.seccess(qu)
    }


}