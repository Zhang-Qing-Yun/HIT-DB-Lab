package com.qingyun.db.service;

import com.qingyun.db.base.ParameterNotRightException;
import com.qingyun.db.bean.Class;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 張青云
 * @since 2022-04-10
 */
public interface ClassService extends IService<Class> {
    /**
     * 分页查询符合条件的班级
     */
    List<Class> getAllClasses(int offset, int limit, Class queryCondition);

    /**
     * 符合条件的班级的数量
     */
    int getTotalRows(Class queryCondition);

    /**
     * 根据班级id获取班级id
     */
    Class getClassById(Integer id);

    /**
     * 新增一个班级
     */
    void insertClass(Class c) throws ParameterNotRightException;
}
