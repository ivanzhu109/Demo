package cn.ivanzhu.service.impl;

import cn.ivanzhu.controller.param.UserSaveParam;
import cn.ivanzhu.mapper.UserMapper;
import cn.ivanzhu.model.UserPO;
import cn.ivanzhu.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author ivanzhu
 * @date 2020/1/7
 * @time 15:53
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 新增用户
     *
     * @param param 用户参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserSaveParam param) {
        UserPO userPO = param.getUserPO();
        int result = userMapper.insertUser(userPO);
        if (result < 1){
            throw new RuntimeException("插入用户信息失败");
        }
        this.updateNameById(userPO.getId());
    }

    private void updateNameById(Integer id) {
        String name = "被修改过的名称";
        userMapper.updateNameById(id, name);
//        int i = 1/0;
    }
}
