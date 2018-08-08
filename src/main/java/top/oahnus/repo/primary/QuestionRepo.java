package top.oahnus.repo.primary;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import top.oahnus.domain.mongodb.Question;

import java.util.List;

/**
 * Created by oahnus on 2018/8/8
 * 11:37.
 */
@Repository
public interface QuestionRepo extends MongoRepository<Question, Long> {
    List<Question> findByTitleLike(String title);
}
