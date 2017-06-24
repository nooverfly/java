package cn.tarena.fh.service.impl;

import cn.tarena.fh.mapper.MessageNewMapper;
import cn.tarena.fh.pojo.MessageNew;
import cn.tarena.fh.service.MessageNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */
@Service
public class MessageNewServiceImpl implements MessageNewService {

    @Autowired
    private MessageNewMapper messageNewMapper;


    @Override
    public List<MessageNew> findMessageNew(String toId) {
        return messageNewMapper.findMessageNew(toId);
    }
}
