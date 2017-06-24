package cn.tarena.fh.service;

import cn.tarena.fh.pojo.MessageUser;

import java.util.List;

/**
 * Created by 王炉婷 on 2017/6/3.
 */
public interface MessageUserService {
    List<MessageUser> findAll();

    MessageUser findOne(String userName);

    void updateStateOne(String userName, Integer state);

    void updateStateOneUser(MessageUser msgUser);
}
