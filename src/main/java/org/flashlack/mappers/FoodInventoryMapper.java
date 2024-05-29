package org.flashlack.mappers;

import org.apache.ibatis.annotations.Insert;
import org.flashlack.entity.FoodDO;

/**
 * @author FALSHLACK
 */
public interface FoodInventoryMapper {
    @Insert("Insert Into food_inventory(food_number, food_name, food_category, food_price, stock_quantity, supplier_name) " +
            "VALUES (#{foodNumber},#{foodName},#{foodCategory},#{foodPrice},#{stockQuantity},#{supplierName})")
    boolean foodInventoryUpdate(FoodDO foodDO);
}
