package cn.tarena.fh.service;

import cn.tarena.fh.pojo.MessageNew;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */
public interface MessageNewService {
    List<MessageNew> findMessageNew(String id);
}
