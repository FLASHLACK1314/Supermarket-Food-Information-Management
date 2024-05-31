package org.flashlack.mappers.impl;

import org.apache.ibatis.session.SqlSession;
import org.flashlack.entity.UserDO;
import org.flashlack.mappers.UserMappper;
import org.flashlack.util.MybatisUtil;

/**
 * @author FLASHLACK
 */
public class UserImpl implements UserMappper {
    @Override
    public UserDO loginSelect(UserDO userDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            UserMappper mapper = sqlSession.getMapper(UserMappper.class);
            System.out.println("登录验证");
            return mapper.loginSelect(userDO);
        }
    }
}
