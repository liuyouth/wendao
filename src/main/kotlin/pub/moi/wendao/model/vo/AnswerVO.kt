package pub.moi.wendao.model.vo

import pub.moi.wendao.model.base.User

class AnswerVO (  var number: Long = 0L// 编号
                 , var questionNo: Long = 0L// 问题编号
                 , var title: String? = ""// 答案标题
                 , var info: String? = ""// 答案详细
                 , var avatar: String? = ""//附图
                 , var postUser: User?// 提交用户编号
                 , var postTime: Long = 0L // 提交时间
                 , var lastUpDateTime: Long = 0L // 最后修改时间

 ) {
    constructor() : this(0,0,"","","",null,0,0) {

    }
}