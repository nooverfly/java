package cn.tarena.fh.mapper;

import cn.tarena.fh.pojo.LeaveBill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/6/4.
 */
public interface LeaveBillMapper extends BaseMapper<LeaveBill> {
    List<LeaveBill> findLBListByUID(String userId);

    void updateState(@Param("id") Integer id, @Param("state") Integer state);
}
