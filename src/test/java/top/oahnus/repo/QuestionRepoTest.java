package top.oahnus.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.oahnus.domain.mongodb.Question;
import top.oahnus.repo.primary.QuestionRepo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by oahnus on 2018/8/8
 * 11:39.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class QuestionRepoTest {

    @Autowired
    private QuestionRepo questionRepo;

    @Test
    public void findByTitle() {
        Question question = new Question();
        question.setTitle("江苏省的省会是?");
        question.setContent("江苏省的省会是?");
        question.setCreDate(new Date());
        question.setTags(Arrays.asList("地理", "常识"));
        question.setUptDate(new Date());
        questionRepo.save(question);

        List<Question> questionList = questionRepo.findByTitleLike("江苏");
        System.out.println(questionList);
    }
}