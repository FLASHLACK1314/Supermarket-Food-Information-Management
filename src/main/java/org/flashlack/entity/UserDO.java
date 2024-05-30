package org.flashlack.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author FLASHLACK
 */
@Data
@Accessors(chain = true)
public class UserDO {
    private String userId;
    private String password;
}
