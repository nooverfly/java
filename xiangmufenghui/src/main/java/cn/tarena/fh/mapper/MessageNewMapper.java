package cn.tarena.fh.mapper;

import cn.tarena.fh.pojo.MessageNew;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */
public interface MessageNewMapper {


    List<MessageNew> findMessageNew(String toId);
}
