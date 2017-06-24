package cn.tarena.fh.mapper;

import cn.tarena.fh.pojo.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 王炉婷 on 2017/6/2.
 */
public interface MessageMapper {
    void saveMessage(Message msg);

    List<Message> findMessageOne(String toId);

    List<Message> findByFromAndTo(@Param("fromId") String fromId, @Param("toId") String toId);



    void updateMessageState(@Param("fromId") String fromId, @Param("toId") String toId);



}
