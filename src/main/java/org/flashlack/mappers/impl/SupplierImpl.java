package org.flashlack.mappers.impl;

import org.apache.ibatis.session.SqlSession;
import org.flashlack.entity.SupplierDO;
import org.flashlack.mappers.SupplierMapper;
import org.flashlack.util.MybatisUtil;

import java.util.List;

/**
 * @author FLASHLACK
 */
public class SupplierImpl implements SupplierMapper {
    /**
     * 添加供应商
     *
     * @param supplierDO 供应商类
     * @return 成功
     */
    @Override
    public boolean insertSupplier(SupplierDO supplierDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            SupplierMapper mapper = sqlSession.getMapper(SupplierMapper.class);
            return mapper.insertSupplier(supplierDO);
        }
    }

    /**
     * 查询供应商
     *
     * @param supplierDO 供应商类
     * @return 供应商类
     */
    @Override
    public SupplierDO selectSupplier(SupplierDO supplierDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            SupplierMapper mapper = sqlSession.getMapper(SupplierMapper.class);
            return mapper.selectSupplier(supplierDO);
        }
    }

    /**
     * 删除供应商
     *
     * @param supplierDO 供应商
     * @return 成功
     */
    @Override
    public boolean deleteSupplier(SupplierDO supplierDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            SupplierMapper mapper = sqlSession.getMapper(SupplierMapper.class);
            return mapper.deleteSupplier(supplierDO);
        }
    }

    /**
     * 更新供应商信息
     * @param supplierDO 供应商类
     * @return 成功
     */
    @Override
    public boolean updateSupplier(SupplierDO supplierDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            SupplierMapper mapper = sqlSession.getMapper(SupplierMapper.class);
            return mapper.updateSupplier(supplierDO);
        }
    }

    /**
     * 通过编号获取链表
     * @param supplierDO 供应商
     * @return 链表
     */
    @Override
    public List<SupplierDO> selectSupplierList(SupplierDO supplierDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            SupplierMapper mapper = sqlSession.getMapper(SupplierMapper.class);
            return mapper.selectSupplierList(supplierDO);
        }
    }

    /**
     * 通过名称获取链表
     * @param supplierDO 供应商类
     * @return 链表
     */
    @Override
    public List<SupplierDO> selectSupplierByName(SupplierDO supplierDO) {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            SupplierMapper mapper = sqlSession.getMapper(SupplierMapper.class);
            return mapper.selectSupplierByName(supplierDO);
        }
    }
}
