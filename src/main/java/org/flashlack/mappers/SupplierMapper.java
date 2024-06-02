package org.flashlack.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.flashlack.entity.SupplierDO;

import java.util.List;

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
    @Update("UPDATE supplier SET supplier_name = #{supplierName},supplier_phone = #{supplierPhone} WHERE supplier_number = #{supplierNumber}")
    boolean updateSupplier(SupplierDO supplierDO);
    @Select("SELECT * FROM supplier WHERE supplier_number = #{supplierNumber}")
    List<SupplierDO> selectSupplierList(SupplierDO supplierDO);
    @Select("SELECT * FROM supplier WHERE supplier_name = #{supplierName}")
    List<SupplierDO> selectSupplierByName(SupplierDO supplierDO);
    @Select("SELECT * FROM supplier")
    List<SupplierDO> selectAll();
}
