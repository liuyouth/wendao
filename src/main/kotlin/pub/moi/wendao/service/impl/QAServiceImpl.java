package pub.moi.wendao.service.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pub.moi.wendao.db.AnswerRepository;
import pub.moi.wendao.db.QuestionRepository;
import pub.moi.wendao.db.UserRepository;
import pub.moi.wendao.model.base.Answer;
import pub.moi.wendao.model.base.PageResult;
import pub.moi.wendao.model.base.Question;
import pub.moi.wendao.model.vo.AnswerVO;
import pub.moi.wendao.model.vo.QuestionVO;
import pub.moi.wendao.service.AnswerService;
import pub.moi.wendao.service.QAService;
import pub.moi.wendao.service.QuestionService;

import java.util.ArrayList;
import java.util.List;


@Service("QAService")
public class QAServiceImpl implements QAService {

    @Autowired
    @Qualifier("QuestionService")
    QuestionService questionService;
    @Autowired
    @Qualifier("AnswerService")
    AnswerService answerService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AnswerRepository answerRepository;



    @NotNull
    @Override
    public PageResult<QuestionVO> findQuestionList(@NotNull Pageable pageable, @NotNull String searchStr) {
        Page<Question> page = questionService.search(pageable,searchStr);
        PageResult<QuestionVO> result  = new  PageResult<>();
        result.setCode(200);
        result.setAllPage(page.getTotalPages());
        result.setTotal(page.getTotalElements());
        ArrayList<QuestionVO> vos = new ArrayList<>();
        for (int i = 0; i < page.getContent().size(); i++) {
            vos.add(findQuestionByNo(page.getContent().get(i).getNumber()));
        }
        result.setData(vos);
//        Page<QuestionVO> voPage = Page.empty(pageable);
//        BeanUtils.copyProperties(page,voPage);
//        System.out.println(page);
//        System.out.println(voPage);

//        page.getContent().forEach(question -> voPage.getContent().add(findQuestionByNo(question.getNumber())));
        return result;
    }

    @Nullable
    @Override
    public QuestionVO findQuestionByNo(long number) {
        Question qu =  questionRepository.findByNumber(number);
        if (qu==null)
            return null;
        QuestionVO vo = new QuestionVO();
        BeanUtils.copyProperties(qu,vo);
        vo.setPostUser(userRepository.findByNumber(qu.getPostUserNo()));
        List<Answer> as = answerRepository.findByQuestionNo(qu.getNumber());
        List<AnswerVO> asvo = new ArrayList<>();
        as.forEach(answer -> {
            AnswerVO vo1 = new AnswerVO();
            BeanUtils.copyProperties(answer,vo1);
            vo1.setPostUser(userRepository.findByNumber(answer.getPostUserNo()));
            asvo.add(vo1);
        });
        vo.setAnswers(asvo);
        return vo;
    }
}
