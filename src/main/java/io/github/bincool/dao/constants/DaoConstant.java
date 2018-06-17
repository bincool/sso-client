/**
 * Filename: DaoConstant.java
 * Package: io.github.bincool.dao.constants
 * Copyright: Wchy-bingV All Rights Reserved.QQ:891946049.
 * Description: DaoConstant.java
 * Author: wchy.
 * Date: 2017/7/3 0:40.
 * Content: 新增.
 * Version: V1.0.
 */
package io.github.bincool.dao.constants;

/**
 * <p>
 * Description: Dao层常量类，魔鬼字符物理意义化.
 * </p>
 * <p>
 * 详细描述.
 * </p>
 * Author: wchy.
 * Since: 2017/7/3.0:40.
 */
public interface DaoConstant
{

    /**
     * 批量操作类型.
     */
    interface BatchOperType
    {
        // 批量新增类型.
        String INSERT = "insert";

        // 批量删除类型.
        String DELETE = "delete";

        // 批量更新类型，几乎不使用.
        String UPDATE = "update";
    }

}
