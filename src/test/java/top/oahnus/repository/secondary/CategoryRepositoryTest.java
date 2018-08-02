package top.oahnus.repository.secondary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.oahnus.domain.secondary.Category;

import static org.junit.Assert.*;

/**
 * Created by oahnus on 2017/11/28
 * 12:05.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryRepositoryTest {

//    @Autowired
//    private CategoryRepository categoryRepository;

    @Test
    public void findByName() throws Exception {
//        Category category = categoryRepository.findByName("JAVA WEB");
//        System.out.println(category);
    }
}