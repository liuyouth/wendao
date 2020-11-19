package pub.moi.wendao.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import pub.moi.wendao.model.base.Question

interface QuestionService{
    fun getList(pageable: Pageable, userNo: Long): Page<Question>
    fun search(pageable: Pageable, searchStr: String): Page<Question>

}
interface QService<T,ID>:IQuestionJPA{
    fun getList(pageable: Pageable, userNo: Long): Page<Question>
    fun search(pageable: Pageable, searchStr: String): Page<Question>

}
interface AnswerService{
//    fun getList(pageable: Pageable, userNo: Long): Page<Answer>
//    fun search(pageable: Pageable, searchStr: String): Page<Answer>

}