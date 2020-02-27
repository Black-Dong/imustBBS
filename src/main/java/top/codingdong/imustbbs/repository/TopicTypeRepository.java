package top.codingdong.imustbbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.codingdong.imustbbs.entity.TopicType;

/**
 * @author Dong
 * @date 2020/2/27 14:34
 */
public interface TopicTypeRepository extends JpaRepository<TopicType,Integer>, JpaSpecificationExecutor<TopicType> {
}
