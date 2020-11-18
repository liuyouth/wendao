package pub.moi.wendao.model.vo

import pub.moi.wendao.model.base.Answer
import pub.moi.wendao.model.base.User

class QuestionVO (     var number: Long = 0L// 编号
                       , var title: String? = ""// 问题标题
                       , var info: String? = ""// 问题详细
                       , var avatar: String? = ""//附图
                       , var postUser: User? // 提交用户编号
                       , var postTime: Long = 0L // 提交时间
                       , var lastUpDateTime: Long = 0L // 最后修改时间
                        ,var answers : List<AnswerVO>

){
    constructor() : this(0,"","","",null,0,0, emptyList()) {

    }
}