package org.flashlack.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.flashlack.entity.UserDO;

/**
 * @author FLASHLACK
 */
public interface UserMappper {
    @Select("SELECT * FROM [user] WHERE user_id =#{userId}")
    UserDO loginSelect(UserDO userDO);
    @Insert("INSERT INTO [user](user_id, password) VALUES (#{userId},#{password})")
    void insert(UserDO userDO);
}
