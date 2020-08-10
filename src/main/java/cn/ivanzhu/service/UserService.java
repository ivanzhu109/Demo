package cn.ivanzhu.service;

import cn.ivanzhu.controller.param.UserSaveParam;
import cn.ivanzhu.model.UserPO;

import java.util.List;

public interface UserService {

    /**
     * 新增用户
     *
     * @param param 用户参数
     */
    void addUser(UserSaveParam param);

    /**
     * 查询所有用户列表
     *
     * @return 用户列表
     */
    List<UserPO> listAllUser();
}
