/**
 * @FileName: Dao.java
 * @Package: io.github.bincool.dao.impl
 * @Copyright: Wchy-bingV All Rights Reserved.QQ:891946049.
 * @Description: DaoSupport.java.
 * @Author: wchy.
 * @Date: 2017-07-02.22:38.
 * @Content: 新增.
 * @Version: V1.0.
 */
package io.github.bincool.dao.impl;

import io.github.bincool.dao.Dao;
import io.github.bincool.dao.constants.DaoConstant;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Description: 基础dao接口类的具体实现类.
 * </p>
 * <p>
 * 详细描述.
 * </p>
 * Author: 王成义.
 * Since: 2017-07-02.22:38.
 */
@Repository
public class DaoSupport<T> implements Dao<T>
{

	/**
	 * 负责管理MyBatis的SqlSession,调用MyBatis的SQL方法，翻译异常,SqlSessionTemplate是线程安全的，可以被多个DAO所共享使用.
	 */
	private final SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public DaoSupport(SqlSessionTemplate sqlSessionTemplate)
	{
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	/**
	 * 保存对象
	 * @param str
	 * 		Mapper中namespace.id.
	 * @param obj
	 * 		参数.
	 * @return
	 * 		受影响行数.
	 */
	public int save(String str, T obj) throws Exception
	{
		return sqlSessionTemplate.insert(str, obj);
	}

	/**
	 * 保存多条.
	 * @param str
	 * 		Mapper中namespace.id.
	 * @param objs
	 * 		参数.
	 * @return
	 * 		受影响行数.
	 */
	public int batchSave(String str, List<T> objs ) throws Exception
	{
		return batch(str, objs, DaoConstant.BatchOperType.INSERT);
	}

	/**
	 * 修改对象.
	 * @param str
	 * 		Mapper中namespace.id.
	 * @param obj
	 * 		参数.
	 * @return
	 * 		受影响行数.
	 */
	public int update(String str, T obj) throws Exception
	{
		return sqlSessionTemplate.update(str, obj);
	}

	/**
	 * 批量更新.
	 * @param str
	 * 		Mapper中namespace.id.
	 * @param objs
	 * 		参数.
	 * @return
	 * 		受影响行数.
	 */
	public int batchUpdate(String str, List<T> objs ) throws Exception
	{
		return batch(str, objs, DaoConstant.BatchOperType.UPDATE);
	}

	/**
	 * 删除对象.
	 * @param str
	 * 		Mapper中namespace.id.
	 * @param obj
	 * 		参数.
	 * @return
	 * 		受影响行数.
	 */
	public int delete(String str, T obj) throws Exception
	{
		return sqlSessionTemplate.delete(str, obj);
	}

	/**
	 * 批量删除.
	 * @param str
	 * 		Mapper中namespace.id.
	 * @param objs
	 * 		参数.
	 * @return
	 * 		受影响行数.
	 */
	public int batchDelete(String str, List<T> objs)throws Exception
	{
		return batch(str, objs, DaoConstant.BatchOperType.DELETE);
	}

	/**
	 * 查找对象.主要用于通过主键查找对象，或者通过unique列的值查找.
	 * @param str
	 * 		Mapper中namespace.id.
	 * @param obj
	 * 		参数.
	 * @return
	 * 		结果对象.
	 */
	public T selectOne(String str, T obj) throws Exception
	{
		return sqlSessionTemplate.selectOne(str, obj);
	}

	/**
	 * 查找对象(多个).主要用于非主键列查询.
	 * @param str
	 * 		Mapper中namespace.id.
	 * @param obj
	 * 		参数.
	 * @return
	 * 		结果对象集.
	 */
	public List<T> selectList(String str, T obj) throws Exception
	{
		return sqlSessionTemplate.selectList(str, obj);
	}


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
	public Map<String, T> selectMap(String str, T obj, String key) throws Exception
	{
		return sqlSessionTemplate.selectMap(str, obj, key);
	}

	/**
	 * 批量操作.用于批量新增，批量更新，批量删除.
	 * @param str
	 * 		Mapper中namespace.id.
	 * @param objs
	 * 		参数.
	 * @param type
	 * 		批量操作类型："insert"、"update"、"delete".
	 * @return
	 * 		受影响行数.
	 * @throws Exception
	 * 		数据库操作异常.
	 */
	private int batch(String str, List<T> objs, String type) throws Exception
	{
		int count = 0;
		SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
		// 批量执行器.
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,false);
		try
		{
			// 增强for循环也需要进行空判断的.
			if(null != objs)
			{
				for(T obj : objs)
				{
					int ret = batchOperByType(str, obj, sqlSession, type);
					count += ret;
				}
				sqlSession.flushStatements();
				sqlSession.commit();
				// 清理缓存防止溢出，可以分批次提交，1000个一提交，然后清理缓存.可以在catch中session.rollback();进行事务回滚.
				sqlSession.clearCache();
			}
		}
		finally
		{
			sqlSession.close();
		}
		return count;
	}

	/**
	 * 批量操作.用于批量新增，批量更新，批量删除.
	 * @param str
	 * 		Mapper中namespace.id.
	 * @param obj
	 * 		参数.
	 * @param type
	 * 		批量操作类型："insert"、"update"、"delete".
	 * @param sqlSession
	 * 		批量执行器.
	 * @return
	 * 		受影响行数.
	 * @throws Exception
	 * 		数据库操作异常.
	 */
	private int batchOperByType(String str, T obj, SqlSession sqlSession, String type) throws Exception
	{
		// switch-case语句执行效率比if-else要高.switch在jdk1.7后支持string了.
		switch (type)
		{
			case DaoConstant.BatchOperType.INSERT:
				return sqlSession.insert(str, obj);
			case DaoConstant.BatchOperType.DELETE:
				return sqlSession.delete(str, obj);
			case DaoConstant.BatchOperType.UPDATE:
				return sqlSession.update(str, obj);
			default:
				return 0;
		}
	}

}


