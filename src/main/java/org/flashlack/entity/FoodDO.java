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
}
