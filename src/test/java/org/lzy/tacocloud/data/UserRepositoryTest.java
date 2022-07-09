package org.lzy.tacocloud.data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Test
    public void testFindAllUsers() {
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void testFindByUsername() {
        System.out.println(userRepository.findByUsername("lzy").get());
    }
}
