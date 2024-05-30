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
}
