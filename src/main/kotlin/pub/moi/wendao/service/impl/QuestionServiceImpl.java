package pub.moi.wendao.service.impl;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pub.moi.wendao.db.QuestionRepository;
import pub.moi.wendao.model.base.Question;
import pub.moi.wendao.service.QuestionService;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@Service("QuestionService")
public class QuestionServiceImpl implements QuestionService {
    final
    QuestionRepository repository;

    public QuestionServiceImpl( QuestionRepository repository) {
        this.repository = repository;
    }

    @NotNull
    @Override
    public Page<Question> search(@NotNull Pageable pageable, String searchStr) {
        Specification<Question> specification = (Specification<Question>) (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            // 第一个userId为CloudServerDao中的字段，第二个userId为参数
            if (!searchStr.isEmpty()) {
                // 搜索文本为不为空 同时查询 标题和详情中是否有符合的
                Predicate p1 = criteriaBuilder.like(root.get("title"), "%"+searchStr+"%");
//                list.add(p1);

                Predicate p2 = criteriaBuilder.like(root.get("info"),"%"+searchStr+"%" );
//                list.add(p2);
                list.add(criteriaBuilder.or(p1,p2));
            }
//                if (!key.equals(null)) {
//                    // 此处为查询serverName中含有key的数据
//                    Predicate p2 = criteriaBuilder.like(root.get("serverName"),"%"+key+"%" );
//                    list.add(p2);
//                }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
        return repository.findAll(specification, pageable);
    }
    @NotNull
    @Override
    public Page<Question> getList(@NotNull Pageable pageable, long userNo) {
        Specification<Question> specification = (Specification<Question>) (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            // 第一个userId为CloudServerDao中的字段，第二个userId为参数
            if (userNo!=0) {
                Predicate p1 = criteriaBuilder.equal(root.get("postUserNo"), userNo);
                list.add(p1);
            }
//                if (!key.equals(null)) {
//                    // 此处为查询serverName中含有key的数据
//                    Predicate p2 = criteriaBuilder.like(root.get("serverName"),"%"+key+"%" );
//                    list.add(p2);
//                }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
        return repository.findAll(specification, pageable);
    }
}
