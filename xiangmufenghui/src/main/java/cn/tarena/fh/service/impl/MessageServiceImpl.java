package cn.tarena.fh.service.impl;

import cn.tarena.fh.mapper.MessageMapper;
import cn.tarena.fh.pojo.Message;
import cn.tarena.fh.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by  on 2017/6/2.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;


    @Transactional(propagation = Propagation.REQUIRED)
    public void saveMessage(Message msg) {
        messageMapper.saveMessage(msg);
    }

    public List<Message> findMessageOne(String toId) {
        return messageMapper.findMessageOne(toId);
    }

    @Override
    public List<Message> findByFromAndTo(String fromId, String toId) {
        return messageMapper.findByFromAndTo(fromId,toId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMessageState(String fromId, String toId) {
        messageMapper.updateMessageState(fromId,toId);
    }




}
