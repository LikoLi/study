package cn.likoli.study.flowable.springboot.repository;

import cn.likoli.study.flowable.springboot.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PersonRepository
 *
 * @author liko
 * @date 2021/3/12
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByUsername(String username);
}
