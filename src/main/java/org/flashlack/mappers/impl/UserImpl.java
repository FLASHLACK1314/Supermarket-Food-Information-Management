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
            return mapper.loginSelect(userDO);
        }
    }

    @Override
    public void insert(UserDO userDO) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            UserMappper mapper = sqlSession.getMapper(UserMappper.class);
            mapper.insert(userDO);
        }
    }
}
