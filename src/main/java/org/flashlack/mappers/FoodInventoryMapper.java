package org.flashlack.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.flashlack.entity.FoodDO;

import java.util.List;

/**
 * @author FALSHLACK
 */
public interface FoodInventoryMapper {
    @Insert("Insert Into food_inventory(food_number, food_name, food_category, food_price, stock_quantity, supplier_name) " +
            "VALUES (#{foodNumber},#{foodName},#{foodCategory},#{foodPrice},#{stockQuantity},#{supplierName})")
    boolean foodInventoryUpdate(FoodDO foodDO);
    @Select("SELECT * FROM food_inventory WHERE food_number = #{foodNumber}")
    List<FoodDO> foodInventoryFind(FoodDO foodDO);
    @Select("SELECT * FROM food_inventory WHERE food_name = #{foodName}")
    List<FoodDO> foodInventoryFindByName(FoodDO foodDO);
    @Select("SELECT * from food_inventory WHERE food_category = #{foodCategory}")
    List<FoodDO> foodInventoryFindByCategory(FoodDO foodDO);
    @Select("SELECT * from food_inventory WHERE supplier_name = #{supplierName}")
    List<FoodDO> foodInventoryFindBySupplierName(FoodDO foodDO);
}
