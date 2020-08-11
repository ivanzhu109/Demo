package cn.ivanzhu.mapper;

import cn.ivanzhu.model.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ivanzhu
 */
@Mapper
public interface UserMapper {

    /**
     * 插入用户
     *
     * @param userPO 用户po
     * @return 影响行数
     */
    int insertUser(UserPO userPO);

    /**
     * 更新用户名称
     *
     * @param id   id
     * @param name 名称
     * @return 影响行数
     */
    int updateNameById(@Param("id") Integer id, @Param("name") String name);

    /**
     * 查询所有用户
     *
     * @return 所有用户
     */
    List<UserPO> listAll();
}
