package org.flashlack.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DemoDO {
    private Integer id;
    private Integer name;
    private Integer age;
}
