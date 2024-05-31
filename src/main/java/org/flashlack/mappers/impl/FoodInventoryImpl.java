package org.flashlack.mappers.impl;

import org.apache.ibatis.session.SqlSession;
import org.flashlack.entity.FoodDO;
import org.flashlack.mappers.FoodInventoryMapper;
import org.flashlack.util.MybatisUtil;

import java.util.List;

/**
 * @author FALSHLACK
 */
public class FoodInventoryImpl implements FoodInventoryMapper {
    /**
     * 物品库存添加
     *
     * @param foodDO -物品库存类
     * @return 是否完成添加
     */
    @Override
    public boolean foodInventoryInsert(FoodDO foodDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            FoodInventoryMapper mapper = sqlSession.getMapper(FoodInventoryMapper.class);
            return mapper.foodInventoryInsert(foodDO);
        }
    }

    /**
     * 通过编号查询食品库存
     *
     * @param foodDO 食品类
     * @return 食品链表
     */
    @Override
    public List<FoodDO> foodInventoryFind(FoodDO foodDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            FoodInventoryMapper mapper = sqlSession.getMapper(FoodInventoryMapper.class);
            return mapper.foodInventoryFind(foodDO);
        }
    }

    /**
     * 通过食品名字查询
     * @param foodDO 食品类
     * @return 食品链表
     */
    @Override
    public List<FoodDO> foodInventoryFindByName(FoodDO foodDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            FoodInventoryMapper mapper = sqlSession.getMapper(FoodInventoryMapper.class);
            return mapper.foodInventoryFindByName(foodDO);
        }
    }

    /**
     * 通过类别查查询食品库存
     * @param foodDO 食品类
     * @return 食品库存链表
     */
    @Override
    public List<FoodDO> foodInventoryFindByCategory(FoodDO foodDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            FoodInventoryMapper mapper = sqlSession.getMapper(FoodInventoryMapper.class);
            return mapper.foodInventoryFindByCategory(foodDO);
        }
    }

    /**
     * 通过供应商名称查询食品库存
     * @param foodDO 食品类
     * @return 食品库存链表
     */
    @Override
    public List<FoodDO> foodInventoryFindBySupplierName(FoodDO foodDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            FoodInventoryMapper mapper = sqlSession.getMapper(FoodInventoryMapper.class);
            return mapper.foodInventoryFindBySupplierName(foodDO);
        }
    }

    /**
     * 获取食品信息
     * @param foodDO 食品类
     * @return 单个食品信息
     */
    @Override
    public FoodDO getFoodDO(FoodDO foodDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            FoodInventoryMapper mapper = sqlSession.getMapper(FoodInventoryMapper.class);
            return mapper.getFoodDO(foodDO);
        }
    }

    /**
     * 更新食品信息
     * @param foodDO 食品类
     * @return 是否更新成功
     */
    @Override
    public boolean updateFood(FoodDO foodDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            FoodInventoryMapper mapper = sqlSession.getMapper(FoodInventoryMapper.class);
            return mapper.updateFood(foodDO);
        }
    }

    /**
     * 删除食品信息
     * @param foodDO 食品类
     * @return 是否删除成功
     */
    @Override
    public boolean deleteFood(FoodDO foodDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            FoodInventoryMapper mapper = sqlSession.getMapper(FoodInventoryMapper.class);
            return mapper.deleteFood(foodDO);
        }
    }

    /**
     * 通过供应商编号查询
     * @param foodDO 食品类
     * @return 链表
     */
    @Override
    public List<FoodDO> foodInventoryFindBySupplierNumber(FoodDO foodDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            FoodInventoryMapper mapper = sqlSession.getMapper(FoodInventoryMapper.class);
            return mapper.foodInventoryFindBySupplierNumber(foodDO);
        }
    }

    @Override
    public boolean deleteAllFoodInventory() {
        try(SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            FoodInventoryMapper mapper = sqlSession.getMapper(FoodInventoryMapper.class);
            return mapper.deleteAllFoodInventory();
        }
    }
}
