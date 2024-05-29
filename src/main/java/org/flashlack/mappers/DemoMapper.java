package org.flashlack.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.flashlack.entity.DemoDO;

public interface DemoMapper {
    @Select("SELECT * FROM food")
    DemoDO demo();

    @Insert("INSERT INTO food (id, name, age) VALUES (#{demo}, #{demo}, #{demo})")
    void update(int demo);
}
