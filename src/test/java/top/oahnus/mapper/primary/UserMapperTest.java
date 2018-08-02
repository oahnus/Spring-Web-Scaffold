package top.oahnus.mapper.primary;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.oahnus.domain.primary.User;
import top.oahnus.mapper.secondary.CategoryMapper;

import java.util.List;


/**
 * Created by oahnus on 2018/8/2
 * 9:52.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void test() {
        PageHelper.startPage(0, 10);
        List<User> userList = userMapper.selectAll();
        System.out.println(userList);
        PageInfo<User> page = new PageInfo<>(userList);
        System.out.println(page.getList());
        System.out.println(page.getTotal());
//        userList.forEach(System.out::println);
//        PageHelper.clearPage();
        System.out.println(categoryMapper.selectAll());
    }
}