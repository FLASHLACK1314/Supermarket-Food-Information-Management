package org.flashlack.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.flashlack.entity.SupplierDO;

/**
 * @author FLASHLACK
 */
public interface SupplierMapper {
    @Insert("INSERT INTO supplier(supplier_number, supplier_name, supplier_phone) VALUES (#{supplierNumber},#{supplierName},#{supplierPhone})")
    boolean insertSupplier(SupplierDO supplierDO);

    @Select("SELECT * FROM supplier WHERE supplier_number = #{supplierNumber}")
    SupplierDO selectSupplier(SupplierDO supplierDO);

    @Delete("DELETE supplier WHERE supplier_number = #{supplierNumber}")
    boolean deleteSupplier(SupplierDO supplierDO);
}
