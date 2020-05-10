package cn.ivanzhu.service;

import cn.ivanzhu.controller.param.UserSaveParam;

public interface UserService {

    /**
     * 新增用户
     *
     * @param param 用户参数
     */
    void addUser(UserSaveParam param);
}
