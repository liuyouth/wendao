package pub.moi.wendao.db

import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pub.moi.wendao.model.base.Question


@Repository
interface QuestionRepository : JpaRepository<Question, Int>, JpaSpecificationExecutor<Question>{

}