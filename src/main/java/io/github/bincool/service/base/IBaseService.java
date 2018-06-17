/**
 * Filename: IBaseService.java
 * Package: io.github.bincool.service.base
 * Copyright: Wchy-bingV All Rights Reserved.QQ:891946049.
 * Description: IBaseService.java
 * Author: wchy.
 * Date: 2017/12/4 22:45.
 * Content: 新增.
 * Version: V1.0.
 */
package io.github.bincool.service.base;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Description: 基础的Service的接口类，包含增删改查.
 * </p>
 * <p>
 * 详细描述.
 * </p>
 * Author: wchy.
 * Since: 2017/12/4 22:45.
 */
public interface IBaseService<T extends Serializable>
{

	/**
	 * 保存某一对象.
	 * @param obj
	 * 		参数.
	 * @return
	 * 		受影响行数.
	 */
	int save(T obj) throws Exception;

	/**
	 * 删除某一对象.
	 * @param obj
	 * 		参数.
	 * @return
	 * 		受影响行数.
	 */
	int delete(T obj) throws Exception;

	/**
	 * 修改某一对象.
	 * @param obj
	 * 		参数.
	 * @return
	 * 		受影响行数.
	 */
	int edit(T obj) throws Exception;


	/**
	 * 查询结果集.
	 * @param obj
	 * 		参数.
	 * @return
	 * 		结果集.
	 */
	List<T> listAll(T obj) throws Exception;

	/**
	 * 查询单个对象.
	 * @param obj
	 * 		参数.
	 * @return
	 * 		结果集.
	 */
	T selectOne(T obj)throws Exception;

}
