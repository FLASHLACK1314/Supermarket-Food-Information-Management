package org.flashlack.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.flashlack.entity.SalesDO;

import java.util.List;

/**
 * 食品销售表数据库语句
 * @author FLASHLACK
 */
public interface SalesMapper {
    @Insert("INSERT INTO food_sales(food_sales_number, food_number, sales_quantity, sales_price, staff_number) VALUES (#{foodSalesNumber},#{foodNumber},#{salesQuantity},#{salesPrice},#{staffNumber})")
    Boolean insertSales(SalesDO salesDO);
    @Delete("DELETE food_sales WHERE food_sales_number = #{foodSalesNumber}")
    Boolean deleteSales(SalesDO salesDO);
    @Select("SELECT * FROM food_sales WHERE food_sales_number =#{foodSalesNumber}")
    SalesDO selectSalesByNumber(SalesDO salesDO);
    @Update("UPDATE food_sales SET food_number = #{foodNumber},sales_quantity = #{salesQuantity},sales_price = #{salesPrice},staff_number = #{staffNumber} WHERE food_sales_number = #{foodSalesNumber}")
    Boolean updateSales(SalesDO salesDO);
    @Select("SELECT * FROM food_sales WHERE food_sales_number =#{foodSalesNumber}")
    List<SalesDO> selectSalesListByNumber(SalesDO salesDO);
    @Select("SELECT * FROM food_sales WHERE food_number = #{foodNumber}")
    List<SalesDO> selectSalesListByFood(SalesDO salesDO);
    @Select("SELECT * FROM food_sales WHERE staff_number = #{staffNumber}")
    List<SalesDO> selectSalesListByStaff(SalesDO salesDO);
    @Delete("DELETE food_sales ")
    Boolean deleteAllSales();
}
