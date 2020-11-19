package pub.moi.wendao.service

import pub.moi.wendao.model.base.Question

interface IQuestionJPA{
    fun findByNumber(num:Long): Question?
}