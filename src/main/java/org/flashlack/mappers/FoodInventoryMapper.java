package org.flashlack.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.flashlack.entity.FoodDO;

import java.util.List;

/**
 * @author FALSHLACK
 */
public interface FoodInventoryMapper {
    @Insert("Insert Into food_inventory(food_number, food_name, food_category, food_price, stock_quantity, supplier_number) " +
            "VALUES (#{foodNumber},#{foodName},#{foodCategory},#{foodPrice},#{stockQuantity},#{supplierNumber})")
    boolean foodInventoryInsert(FoodDO foodDO);
    @Select("SELECT * FROM food_inventory WHERE food_number = #{foodNumber}")
    List<FoodDO> foodInventoryFind(FoodDO foodDO);
    @Select("SELECT * FROM food_inventory WHERE food_name = #{foodName}")
    List<FoodDO> foodInventoryFindByName(FoodDO foodDO);
    @Select("SELECT * from food_inventory WHERE food_category = #{foodCategory}")
    List<FoodDO> foodInventoryFindByCategory(FoodDO foodDO);
    @Select("SELECT * from food_inventory WHERE supplier_number = #{supplierNumber}")
    List<FoodDO> foodInventoryFindBySupplierName(FoodDO foodDO);
    @Select("SELECT * FROM food_inventory WHERE food_number = #{foodNumber}")
    FoodDO getFoodDO(FoodDO foodDO);
    @Update("UPDATE food_inventory SET food_name = #{foodName},food_category = #{foodCategory},food_price = #{foodPrice},stock_quantity = #{stockQuantity},supplier_number = #{supplierNumber} WHERE food_number = #{foodNumber}")
    boolean updateFood(FoodDO foodDO);
    @Delete("DELETE food_inventory WHERE food_number = #{foodNumber}")
    boolean deleteFood(FoodDO foodDO);
    @Select("SELECT * FROM food_inventory WHERE supplier_number = #{supplierNumber}")
    List<FoodDO> foodInventoryFindBySupplierNumber(FoodDO foodDO);
}
