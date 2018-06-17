/**
 * @FileName: Dao.java
 * @Package: io.github.bincool.dao
 * @Copyright: Wchy-bingV All Rights Reserved.QQ:891946049.
 * @Description: Dao.java.
 * @Author: wchy.
 * @Date: 2017-07-02.22:38.
 * @Content: 新增.
 * @Version: V1.0.
 */
package io.github.bincool.dao;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Description: 基础dao接口类.
 * </p>
 * <p>
 * 详细描述.
 * </p>
 * Author: 王成义.
 * Since: 2017-07-02.22:38.
 */
public interface Dao<T>
{

	/**
	 * 保存对象
	 * @param str
	 * 		Mapper中namespace.id.
	 * @param obj
	 * 		参数.
	 * @return
	 * 		受影响行数.
	 */
	int save(String str, T obj) throws Exception;

	/**
	 * 保存多条.
	 * @param str
	 * 		Mapper中namespace.id.
	 * @param objs
	 * 		参数.
	 * @return
	 * 		受影响行数.
	 */
	int batchSave(String str, List<T> objs)throws Exception;

	/**
	 * 修改对象.
	 * @param str
	 * 		Mapper中namespace.id.
	 * @param obj
	 * 		参数.
	 * @return
	 * 		受影响行数.
	 */
	int update(String str, T obj) throws Exception;

	/**
	 * 批量更新.
	 * @param str
	 * 		Mapper中namespace.id.
	 * @param objs
	 * 		参数.
	 * @return
	 * 		受影响行数.
	 */
	int batchUpdate(String str, List<T> objs)throws Exception;

	/**
	 * 删除对象.
	 * @param str
	 * 		Mapper中namespace.id.
	 * @param obj
	 * 		参数.
	 * @return
	 * 		受影响行数.
	 */
	int delete(String str, T obj) throws Exception;

	/**
	 * 批量删除.
	 * @param str
	 * 		Mapper中namespace.id.
	 * @param objs
	 * 		参数.
	 * @return
	 * 		受影响行数.
	 */
	int batchDelete(String str, List<T> objs)throws Exception;

	/**
	 * 查找对象.主要用于通过主键查找对象，或者通过unique列的值查找.
	 * @param str
	 * 		Mapper中namespace.id.
	 * @param obj
	 * 		参数.
	 * @return
	 * 		结果对象.
	 */
	T selectOne(String str, T obj) throws Exception;

	/**
	 * 查找对象(多个).主要用于非主键列查询.
	 * @param str
	 * 		Mapper中namespace.id.
	 * @param obj
	 * 		参数.
	 * @return
	 * 		结果对象集.
	 */
	List<T> selectList(String str, T obj) throws Exception;

	/**
	 * 查找对象并封装成map.
	 * @param str
	 * 		Mapper中namespace.id.
	 * @param obj
	 * 		参数.
	 * @param key
	 * 		返回的Map中的Key.
	 * @return
	 * 		封装好的Map.
	 */
	Map<String, T> selectMap(String str, T obj, String key) throws Exception;

}
