package org.flashlack.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.flashlack.entity.StaffDO;

import java.util.List;


/**
 * 员工数据库语句
 * @author FALSHALCK
 */
public interface StaffMapper {
    @Insert("INSERT INTO staff(staff_number, staff_name, staff_sex, staff_phone) VALUES (#{staffNumber},#{staffName},#{staffSex},#{staffPhone})")
    Boolean insertStaff(StaffDO staffDO);
    @Select("SELECT * FROM staff WHERE staff_number = #{staffNumber}")
    StaffDO selectStaffByNumber(StaffDO staffDO);
    @Delete("DELETE staff WHERE staff_number = #{staffNumber}")
    Boolean deleteStaffByNumber(StaffDO staffDO);
    @Update("UPDATE staff SET staff_name = #{staffName},staff_sex = #{staffSex},staff_phone = #{staffPhone} WHERE staff_number = #{staffNumber}")
    Boolean updateStaff(StaffDO staffDO);
    @Select("SELECT * FROM staff WHERE staff_number = #{staffNumber}")
    List<StaffDO> selectStaffListByNumber(StaffDO staffDO);
    @Select("SELECT * FROM staff WHERE staff_name = #{staffName}")
    List<StaffDO> selectStaffListByName(StaffDO staffDO);
    @Select("SELECT * FROM staff WHERE staff_sex = #{staffSex}")
    List<StaffDO> selectStaffListBySex(StaffDO staffDO);
}
