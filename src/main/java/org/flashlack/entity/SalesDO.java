package org.flashlack.entity;

import lombok.Data;

/**
 * 销售记录数据类
 *
 * @author FALSHLACK
 */
@Data
public class SalesDO {
    private Integer foodSalesNumber;
    private Integer foodNumber;
    private String salesQuantity;
    private String salesPrice;
    private Integer staffNumber;

    @Override
    public String toString() {
        return "SalesDO{" +
                "foodSalesNumber='" + foodSalesNumber + '\'' +
                ", foodNumber='" + foodNumber + '\'' +
                ", salesQuantity='" + salesQuantity + '\'' +
                ", salesPrice='" + salesPrice + '\'' +
                ", staffNumber='" + staffNumber + '\'' +
                '}';
    }

    public String toChineseString() {
        return "食品销售编号: " + foodSalesNumber + "\n" +
                "食品编号: " + foodNumber + "\n" +
                "销售数量: " + salesQuantity + "\n" +
                "销售价格: " + salesPrice + "\n" +
                "人员编号: " + staffNumber;
    }
}

