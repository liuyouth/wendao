package pub.moi.wendao.voservice

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import pub.moi.wendao.model.vo.QuestionVO

interface QAService {
    fun findQuestionByNo(number:Long) : QuestionVO?
    fun findQuestionList(pageable: Pageable, searchStr: String): Page<QuestionVO>
}