//package pub.moi.wendao.service.impl
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.data.domain.Page
//import org.springframework.data.domain.Pageable
//import org.springframework.data.jpa.domain.Specification
//import org.springframework.stereotype.Service
//import pub.moi.wendao.db.QuestionRepository
//import pub.moi.wendao.model.base.Question
//import pub.moi.wendao.service.QuestionService
//import java.util.ArrayList
//import javax.persistence.criteria.CriteriaBuilder
//import javax.persistence.criteria.CriteriaQuery
//import javax.persistence.criteria.Predicate
//import javax.persistence.criteria.Root
//
//
//@Service("QuestionService")
//class QuestionServiceImpl : QuestionService {
//    @Autowired
//    lateinit var repository: QuestionRepository
//
//    override fun getList(pageable: Pageable, userNo: Long): Page<Question> {
//        return repository.findAll( { root: Root<Question?>, query: CriteriaQuery<*>?, criteriaBuilder: CriteriaBuilder ->
//            val list: MutableList<Predicate> = ArrayList()
//            // 第一个userId为CloudServerDao中的字段，第二个userId为参数
//            val p1: Predicate = criteriaBuilder.equal(root.get("postUserNo") as Predicate, userNo)
//            list.add(p1)
////            if (!key.equals(null)) {
////                // 此处为查询serverName中含有key的数据
////                val p2: Predicate = criteriaBuilder.like(root["serverName"], "%" + key.toString() + "%")
////                list.add(p2)
////            }
//            criteriaBuilder.and(list.toTypedArray())
//        }, pageable)
//    }
//
//
//}