package org.flashlack.entity;

import lombok.Data;

/**
 * @author FALSHLACK
 */
@Data
public class StaffDO {
    private Integer staffNumber;
    private String staffName;
    private String staffSex;
    private String staffPhone;

    @Override
    public String toString() {
        return "StaffDO{" +
                "staffNumber=" + staffNumber +
                ", staffName='" + staffName + '\'' +
                ", staffSex='" + staffSex + '\'' +
                ", staffPhone='" + staffPhone + '\'' +
                '}';
    }

    public String toChineseString() {
        return "员工编号: " + staffNumber + "\n" +
                "员工姓名: " + staffName + "\n" +
                "员工性别: " + staffSex + "\n" +
                "员工联系方式: " + staffPhone;
    }
}
