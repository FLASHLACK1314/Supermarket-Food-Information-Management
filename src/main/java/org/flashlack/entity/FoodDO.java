package org.flashlack.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 26473
 */
@Data
@Accessors(chain = true)
public class FoodDO {
    private String foodNumber;
    private String foodName;
    private String foodCategory;
    private String foodPrice;
    private String stockQuantity;
    private String supplierName;
    @Override
    public String toString() {
        return "FoodDO{" +
                "foodNumber='" + foodNumber + '\'' +
                ", foodName='" + foodName + '\'' +
                ", foodCategory='" + foodCategory + '\'' +
                ", foodPrice='" + foodPrice + '\'' +
                ", stockQuantity='" + stockQuantity + '\'' +
                ", supplierName='" + supplierName + '\'' +
                '}';
    }

    public String toChineseString() {
        return "食品编号: " + foodNumber + "\n" +
                "食品名称: " + foodName + "\n" +
                "食品类别: " + foodCategory + "\n" +
                "食品进价: " + foodPrice + "\n" +
                "库存数量: " + stockQuantity + "\n" +
                "供应商名称: " + supplierName;
    }
}
