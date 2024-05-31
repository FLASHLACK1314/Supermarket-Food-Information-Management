package org.flashlack.mappers.impl;

import org.apache.ibatis.session.SqlSession;
import org.flashlack.entity.StaffDO;
import org.flashlack.mappers.StaffMapper;
import org.flashlack.util.MybatisUtil;

import java.util.List;

/**
 * 员工数据库方法实现
 *
 * @author FLASHLACK
 */
public class StaffImpl implements StaffMapper {
    /**
     * 添加员工
     *
     * @param staffDO 员工
     * @return 成功
     */
    @Override
    public Boolean insertStaff(StaffDO staffDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            StaffMapper mapper = sqlSession.getMapper(StaffMapper.class);
            return mapper.insertStaff(staffDO);
        }
    }

    /**
     * 通过编号搜索员工类
     *
     * @param staffDO 员工类
     * @return 单个员工类
     */
    @Override
    public StaffDO selectStaffByNumber(StaffDO staffDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            StaffMapper mapper = sqlSession.getMapper(StaffMapper.class);
            return mapper.selectStaffByNumber(staffDO);
        }
    }

    /**
     * 通过编号删除员工类
     *
     * @param staffDO 员工类
     * @return 成功
     */
    @Override
    public Boolean deleteStaffByNumber(StaffDO staffDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            StaffMapper mapper = sqlSession.getMapper(StaffMapper.class);
            return mapper.deleteStaffByNumber(staffDO);
        }
    }

    /**
     * 更新员工
     *
     * @param staffDO 员工类
     * @return 成功
     */
    @Override
    public Boolean updateStaff(StaffDO staffDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            StaffMapper mapper = sqlSession.getMapper(StaffMapper.class);
            return mapper.updateStaff(staffDO);
        }
    }

    /**
     * 编号查询返回链表
     *
     * @param staffDO 实例
     * @return 链表
     */
    @Override
    public List<StaffDO> selectStaffListByNumber(StaffDO staffDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            StaffMapper mapper = sqlSession.getMapper(StaffMapper.class);
            return mapper.selectStaffListByNumber(staffDO);
        }
    }

    /**
     * 姓名查询返回链表
     *
     * @param staffDO 实例
     * @return 链表
     */
    @Override
    public List<StaffDO> selectStaffListByName(StaffDO staffDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            StaffMapper mapper = sqlSession.getMapper(StaffMapper.class);
            return mapper.selectStaffListByName(staffDO);
        }
    }

    /**
     * 性别查询返回链表
     *
     * @param staffDO 实例
     * @return 链表
     */
    @Override
    public List<StaffDO> selectStaffListBySex(StaffDO staffDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            StaffMapper mapper = sqlSession.getMapper(StaffMapper.class);
            return mapper.selectStaffListBySex(staffDO);
        }
    }

    @Override
    public boolean deleteStaff() {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            StaffMapper mapper = sqlSession.getMapper(StaffMapper.class);
            return mapper.deleteStaff();
        }
    }
}
