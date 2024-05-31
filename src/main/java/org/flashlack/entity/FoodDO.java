package org.flashlack.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 26473
 */
@Data
@Accessors(chain = true)
public class FoodDO {
    private Integer foodNumber;
    private String foodName;
    private String foodCategory;
    private String foodPrice;
    private String stockQuantity;
    private Integer supplierNumber;
    @Override
    public String toString() {
        return "FoodDO{" +
                "foodNumber='" + foodNumber + '\'' +
                ", foodName='" + foodName + '\'' +
                ", foodCategory='" + foodCategory + '\'' +
                ", foodPrice='" + foodPrice + '\'' +
                ", stockQuantity='" + stockQuantity + '\'' +
                ", supplierName='" + supplierNumber + '\'' +
                '}';
    }

    public String toChineseString() {
        return "食品编号: " + foodNumber + "\n" +
                "食品名称: " + foodName + "\n" +
                "食品类别: " + foodCategory + "\n" +
                "食品进价: " + foodPrice + "\n" +
                "库存数量: " + stockQuantity + "\n" +
                "供应商编号: " + supplierNumber;
    }
}
