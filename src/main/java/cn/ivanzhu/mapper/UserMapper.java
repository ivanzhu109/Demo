package cn.ivanzhu.mapper;

import cn.ivanzhu.model.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {


    int insertUser(UserPO userPO);

    void updateNameById(@Param("id") Integer id,@Param("name") String name);
}
