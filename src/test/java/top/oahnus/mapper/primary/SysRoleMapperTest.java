package top.oahnus.mapper.primary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.oahnus.domain.primary.SysRole;

import static org.junit.Assert.*;

/**
 * Created by oahnus on 2018/8/8
 * 16:18.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class SysRoleMapperTest {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Test
    public void test() {
        SysRole role = sysRoleMapper.findById(1L);
        System.out.println(role);
    }

}