package pub.moi.wendao.db


import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pub.moi.wendao.model.base.Answer
import pub.moi.wendao.model.base.Question
import pub.moi.wendao.service.IQuestionJPA


@Repository
interface QuestionRepository : JpaRepository<Question, Int>, JpaSpecificationExecutor<Question>,
        IQuestionJPA {

}

@Repository
interface AnswerRepository : JpaRepository<Answer, Int>, JpaSpecificationExecutor<Answer>{
    fun findByNumber(num:Long): Answer?
    fun findByQuestionNo(num:Long): List<Answer>
}

