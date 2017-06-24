package cn.tarena.fh.mapper;

import cn.tarena.fh.pojo.MessageUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 王炉婷 on 2017/6/3.
 */
public interface MessageUserMapper {

    List<MessageUser> findAll();

    MessageUser findOne(String name);



    void updateStateOne(@Param("name") String name, @Param("state") int state);

    void updateStateOneUser(MessageUser msgUser);
}
