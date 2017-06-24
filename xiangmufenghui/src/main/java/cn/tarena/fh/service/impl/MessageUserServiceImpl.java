package cn.tarena.fh.service.impl;

import cn.tarena.fh.mapper.MessageUserMapper;
import cn.tarena.fh.pojo.MessageUser;
import cn.tarena.fh.service.MessageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 王炉婷 on 2017/6/3.
 */
@Service
public class MessageUserServiceImpl implements MessageUserService {

    @Autowired
   private MessageUserMapper messageUserMapper;

    @Override
    public List<MessageUser> findAll() {
        return messageUserMapper.findAll();
    }

    @Override
    public MessageUser findOne(String name) {
        return messageUserMapper.findOne(name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateStateOne(String name,Integer state) {
        messageUserMapper.updateStateOne(name,state);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateStateOneUser(MessageUser msgUser) {
        messageUserMapper.updateStateOneUser(msgUser);
    }


}
