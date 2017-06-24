package cn.tarena.fh.service.impl;

import cn.tarena.fh.mapper.OndutyMapper;
import cn.tarena.fh.pojo.Onduty;
import cn.tarena.fh.service.OndutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */
@Service
public class OndutyServiceImpl implements OndutyService {

    @Autowired
    private OndutyMapper ondutyMapper;

    @Override
    public List<Onduty> findAll() {

        return ondutyMapper.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOnduty(Onduty onduty) {

        ondutyMapper.saveOnduty(onduty);
    }

    @Override
    public Onduty findOndutyByOndutyId(String ondutyId) {

        return ondutyMapper.findOndutyByOndutyId(ondutyId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateOnduty(Onduty onduty) {
        ondutyMapper.updateOnduty(onduty);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteOnduty(String[] ondutyIds) {
        ondutyMapper.deleteOnduty(ondutyIds);
    }
}
