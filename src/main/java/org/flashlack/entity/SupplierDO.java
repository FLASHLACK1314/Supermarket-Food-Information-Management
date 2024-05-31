package org.flashlack.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author FLASHLACK
 */
@Data
@Accessors(chain = true)
public class SupplierDO {
    private Integer supplierNumber;
    private String supplierName;
    private String supplierPhone;
    @Override
    public String toString() {
        return "SupplierDO{" +
                "supplierNumber=" + supplierNumber +
                ", supplierName='" + supplierName + '\'' +
                ", supplierPhone='" + supplierPhone + '\'' +
                '}';
    }

    public String toChineseString() {
        return "供应商编号: " + supplierNumber + "\n" +
                "供应商名称: " + supplierName + "\n" +
                "供应商电话: " + supplierPhone;
    }
}
