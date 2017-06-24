package cn.tarena.fh.mapper;

import cn.tarena.fh.pojo.Onduty;

import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */
public interface OndutyMapper {

    public List<Onduty> findAll();

    public void saveOnduty(Onduty onduty);

    public Onduty findOndutyByOndutyId(String ondutyId);

    public void updateOnduty(Onduty onduty);

    public void deleteOnduty(String[] ondutyIds);
}
