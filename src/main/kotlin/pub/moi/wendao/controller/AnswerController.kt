package pub.moi.wendao.controller

import org.apache.commons.beanutils.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import pub.moi.wendao.db.AnswerRepository
import pub.moi.wendao.db.QuestionRepository

import pub.moi.wendao.java.authorization.annotation.Authorization
import pub.moi.wendao.java.authorization.annotation.CurrentUser
import pub.moi.wendao.model.base.*
import pub.moi.wendao.service.AnswerService
import pub.moi.wendao.service.QuestionService
import pub.moi.wendao.utils.Utils
import javax.websocket.server.PathParam


@RestController
@RequestMapping("/ask")
public class AnswerController{

    @Autowired
    lateinit var repository: AnswerRepository

    @Autowired
    @Qualifier("AnswerService")
    lateinit var service : AnswerService


    /**
     * 根据用户编号获取当前用户的问题列表
     * 暂无隐秘数据之后再说
     */
    @GetMapping("/quNo")
    fun  get(@PathParam("quNo")  quNo:Long): List<Answer> {
        return repository.findByQuestionNo(quNo)
    }
    class AskQu(var answer: Answer)
    /**
     * 回答问题
     */
    @PostMapping("/")
    @Authorization
    @ResponseBody
    fun add(@CurrentUser user: User, @RequestBody body: AskQu): Result<Answer> {
        body.answer.postUserNo = user.number
        body.answer.number = Utils.getOnlyNumber(fun(no:Long): Boolean {
            return repository.findByNumber(no)==null
        })
        val ask = repository.save(body.answer)
        return RBuilder.seccess(ask)
    }

    /**
     * 删除
     */
    @DeleteMapping("/{no}")
    @Authorization
    @ResponseBody
    fun get(@CurrentUser user: User, @PathVariable("no") no: Long): Result<Answer> {
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