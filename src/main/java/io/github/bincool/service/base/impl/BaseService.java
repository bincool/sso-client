/**
 * Filename: BaseService.java
 * Package: io.github.bincool.service.base.impl
 * Copyright: Wchy-bingV All Rights Reserved.QQ:891946049.
 * Description: BaseService.java
 * Author: wchy.
 * Date: 2017/12/4 22:45.
 * Content: 新增.
 * Version: V1.0.
 */
package io.github.bincool.service.base.impl;

import io.github.bincool.dao.Dao;
import io.github.bincool.service.base.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Description: 基础的Service的接口类，包含增删改查,所有Service都应该继承此类.
 * </p>
 * <p>
 * 详细描述.
 * </p>
 * Author: wchy.
 * Since: 2017/12/4 22:45.
 */
public class BaseService<T extends Serializable> implements IBaseService<T>
{
	/**
	 * 当Dao的具体实现类有两个即以上时，则可以通过@Qualifier注解限定bean的名称,只有一个具体实现类时自动注入该实现类.
	 */
	@Autowired
	@Qualifier("daoSupport")
	protected Dao dao;

	/**
	 * 保存某一对象.
	 * @param obj
	 * 		参数.
	 * @return
	 * 		受影响行数.
	 */
	public int save(T obj) throws Exception
	{
		return dao.save(obj.getClass().getSimpleName().concat("Mapper.save"), obj);
	}

	/**
	 * 删除某一对象.
	 * @param obj
	 * 		参数.
	 * @return
	 * 		受影响行数.
	 */
	public int delete(T obj) throws Exception
	{
		return dao.delete(obj.getClass().getSimpleName().concat("Mapper.delete"), obj);
	}

	/**
	 * 修改某一对象.
	 * @param obj
	 * 		参数.
	 * @return
	 * 		受影响行数.
	 */
	public int edit(T obj) throws Exception
	{
		return dao.update(obj.getClass().getSimpleName().concat("Mapper.edit"), obj);
	}


	/**
	 * 查询结果集.
	 * @param obj
	 * 		参数.
	 * @return
	 * 		结果集.
	 */
	public List<T> listAll(T obj) throws Exception
	{
		return (List<T>) dao.selectList(obj.getClass().getSimpleName().concat("Mapper.listAll"), obj);
	}

	/**
	 * 查询单个对象.
	 * @param obj
	 * 		参数.
	 * @return
	 * 		结果集.
	 */
	public T selectOne(T obj) throws Exception
	{
		return (T) dao.selectOne(obj.getClass().getSimpleName().concat("Mapper.selectOne"), obj);
	}

}
