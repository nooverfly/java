package cn.tarena.fh.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/3.
 */
public interface BaseMapper<T> {

    public List<T> findAll();
    public List<T> findAll(Map paraMap);				//带条件查询，条件可以为null，既没有条件；返回list对象集合
    public T findOne(Serializable id);					//只查询一个，常用于修改
    public void insert(T entity);					//插入，用实体作为参数
    public void update(T entity);					//修改，用实体作为参数
    public void deleteById(Serializable id);		//按id删除，删除一条；支持整数型和字符串类型ID
    public void delete(Serializable[] ids);			//批量删除；支持整数型和字符串类型ID


}
