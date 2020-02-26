package top.codingdong.imustbbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import top.codingdong.imustbbs.entity.User;

/**
 * 用户Repository接口
 *
 * @author Dong
 * @date 2020/2/26 15:30
 */

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    @Query(value = "select * from user where user_name = ?1", nativeQuery = true)
    User findUserByUserName(String userName);

    @Query(value = "select * from user where email = ?1", nativeQuery = true)
    User findUserByEmail(String email);
}
