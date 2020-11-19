package pub.moi.wendao.db


import javafx.concurrent.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import pub.moi.wendao.model.base.Answer
import pub.moi.wendao.model.base.Question
import pub.moi.wendao.model.base.User
import pub.moi.wendao.service.IQuestionJPA
import java.util.ArrayList
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root


@Repository
interface QuestionRepository : JpaRepository<Question, Int>, JpaSpecificationExecutor<Question>,IQuestionJPA{

}

@Repository
interface AnswerRepository : JpaRepository<Answer, Int>, JpaSpecificationExecutor<Answer>{
    fun findByNumber(num:Long): Answer?
    fun findByQuestionNo(num:Long): List<Answer>
}

