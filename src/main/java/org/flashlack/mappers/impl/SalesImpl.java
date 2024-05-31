package org.flashlack.mappers.impl;

import org.apache.ibatis.session.SqlSession;
import org.flashlack.entity.SalesDO;
import org.flashlack.mappers.SalesMapper;
import org.flashlack.util.MybatisUtil;

import java.util.List;

/**
 * 销售管理数据库实现
 *
 * @author FLASHLACK
 */
public class SalesImpl implements SalesMapper {
    /**
     * 添加销售记录
     *
     * @param salesDO 记录
     * @return 成功
     */
    @Override
    public Boolean insertSales(SalesDO salesDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            SalesMapper mapper = sqlSession.getMapper(SalesMapper.class);
            return mapper.insertSales(salesDO);
        }
    }

    /**
     * 删除销售记录
     *
     * @param salesDO 记录类
     * @return 成功
     */
    @Override
    public Boolean deleteSales(SalesDO salesDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            SalesMapper mapper = sqlSession.getMapper(SalesMapper.class);
            return mapper.deleteSales(salesDO);
        }
    }

    /**
     * 获取单个销售记录
     *
     * @param salesDO 记录类
     * @return 单条销售记录
     */
    @Override
    public SalesDO selectSalesByNumber(SalesDO salesDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            SalesMapper mapper = sqlSession.getMapper(SalesMapper.class);
            return mapper.selectSalesByNumber(salesDO);
        }
    }

    /**
     * 更新销售记录
     *
     * @param salesDO 记录类
     * @return 成功
     */
    @Override
    public Boolean updateSales(SalesDO salesDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            SalesMapper mapper = sqlSession.getMapper(SalesMapper.class);
            return mapper.updateSales(salesDO);
        }
    }

    /**
     * 通过销售编号进行查询
     *
     * @param salesDO 实例
     * @return 链表
     */
    @Override
    public List<SalesDO> selectSalesListByNumber(SalesDO salesDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            SalesMapper mapper = sqlSession.getMapper(SalesMapper.class);
            return mapper.selectSalesListByNumber(salesDO);
        }
    }

    /**
     * 通过食品编号进行查询
     *
     * @param salesDO 实例
     * @return 链表
     */

    @Override
    public List<SalesDO> selectSalesListByFood(SalesDO salesDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            SalesMapper mapper = sqlSession.getMapper(SalesMapper.class);
            return mapper.selectSalesListByFood(salesDO);
        }
    }

    /**
     * 通过供应商编号进行查询
     *
     * @param salesDO 实例
     * @return 链表
     */
    @Override
    public List<SalesDO> selectSalesListByStaff(SalesDO salesDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            SalesMapper mapper = sqlSession.getMapper(SalesMapper.class);
            return mapper.selectSalesListByStaff(salesDO);
        }
    }

    @Override
    public Boolean deleteAllSales() {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            SalesMapper mapper = sqlSession.getMapper(SalesMapper.class);
            return mapper.deleteAllSales();
        }
    }
}
