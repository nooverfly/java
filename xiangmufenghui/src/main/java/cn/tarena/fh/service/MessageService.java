package cn.tarena.fh.service;

import cn.tarena.fh.pojo.Message;

import java.util.List;

/**
 * Created by  on 2017/6/2.
 */
public interface MessageService {
    void saveMessage(Message msg);

    List<Message> findMessageOne(String toId);

    //from and toId是和当前用户名点开的页面的
    List<Message> findByFromAndTo(String from, String toId);


    //from and toId是和当前用户名点开的页面的
    void updateMessageState(String fromId, String toId);


}
