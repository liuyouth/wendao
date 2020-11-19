package pub.moi.wendao.service.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pub.moi.wendao.db.QuestionRepository;
import pub.moi.wendao.model.base.Question;
import pub.moi.wendao.service.base.BaseServiceImpl;
import pub.moi.wendao.service.QService;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service("QuService")
public class QServiceImpl extends BaseServiceImpl<Question,Integer, QuestionRepository>
        implements QService<Question,Integer> {
    public QServiceImpl(@NotNull QuestionRepository repository) {
        super(repository);
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
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
        return getRepository().findAll(specification, pageable);
    }

    @NotNull
    @Override
    public Page<Question> search(@NotNull Pageable pageable, @NotNull String searchStr) {
        Specification<Question> specification = (Specification<Question>) (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            // 第一个userId为CloudServerDao中的字段，第二个userId为参数
            if (!searchStr.isEmpty()) {
                // 搜索文本为不为空 同时查询 标题和详情中是否有符合的
                Predicate p1 = criteriaBuilder.like(root.get("title"), "%"+searchStr+"%");
                Predicate p2 = criteriaBuilder.like(root.get("info"),"%"+searchStr+"%" );
                list.add(criteriaBuilder.or(p1,p2));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
        return getRepository().findAll(specification, pageable);
    }

    @Nullable
    @Override
    public Question findByNumber(long num) {
        return getRepository().findByNumber(num);
    }



//
//    @NotNull
//    @Override
//    public Page<Question> getList(@NotNull Pageable pageable, long userNo) {
//        Specification<Question> specification = (Specification<Question>) (root, query, criteriaBuilder) -> {
//            List<Predicate> list = new ArrayList<>();
//            // 第一个userId为CloudServerDao中的字段，第二个userId为参数
//            if (userNo!=0) {
//                Predicate p1 = criteriaBuilder.equal(root.get("postUserNo"), userNo);
//                list.add(p1);
//            }
////                if (!key.equals(null)) {
////                    // 此处为查询serverName中含有key的数据
////                    Predicate p2 = criteriaBuilder.like(root.get("serverName"),"%"+key+"%" );
////                    list.add(p2);
////                }
//            return criteriaBuilder.and(list.toArray(new Predicate[0]));
//        };
//        return repository.findAll(specification, pageable);
//    }
//
//    @NotNull
//    @Override
//    public Page<Question> search(@NotNull Pageable pageable, @NotNull String searchStr) {
//        return null;
//    }
}
