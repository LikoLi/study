package org.liko.study.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.liko.study.mybatisplus.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * UserMapperTest
 *
 * @author liko
 * @date 2020/3/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println("----- Select all test -----");
        List<User> userList = userMapper.selectList(null);
//        assertEquals(5, userList.size());
        userList.forEach(System.out::println);

    }

    @Test
    public void testInsert() {

        userMapper.delete(null);
        User user = new User();
        user.setAge(18);
        user.setEmail("liko");
        user.setName("liko");
        userMapper.insert(user);

        testSelect();
    }

    @Test
    public void testLength() {
        String str = "1249962258131759105";
        System.out.println(str.length());
    }
}
