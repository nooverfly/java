package cn.tarena.fh.service;

import cn.tarena.fh.pojo.LeaveBill;
import cn.tarena.fh.pojo.User;

/**
 * Created by Administrator on 2017/6/4.
 */
public interface LeaveBillService {

    public void saveLeaveBill(LeaveBill leaveBill);

    LeaveBill findLeaveBillById(Integer id);

    void deleteLeaveBillById(Integer id);

    void updateLeaveBill(LeaveBill leaveBill);
}
