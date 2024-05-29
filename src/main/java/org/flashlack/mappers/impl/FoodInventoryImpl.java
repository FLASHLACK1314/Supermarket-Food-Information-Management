package org.flashlack.mappers.impl;

import org.apache.ibatis.session.SqlSession;
import org.flashlack.entity.FoodDO;
import org.flashlack.mappers.FoodInventoryMapper;
import org.flashlack.util.MybatisUtil;

/**
 * @author FALSHLACK
 */
public class FoodInventoryImpl implements FoodInventoryMapper {
    /**
     * 物品库存添加
     * @param foodDO -物品库存类
     * @return 是否完成添加
     */
    @Override
    public boolean foodInventoryUpdate(FoodDO foodDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            FoodInventoryMapper mapper = sqlSession.getMapper(FoodInventoryMapper.class);
            return mapper.foodInventoryUpdate(foodDO);
        }
    }
}
