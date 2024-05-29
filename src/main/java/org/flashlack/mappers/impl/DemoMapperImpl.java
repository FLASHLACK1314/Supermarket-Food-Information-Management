package org.flashlack.mappers.impl;

import org.apache.ibatis.session.SqlSession;
import org.flashlack.entity.DemoDO;
import org.flashlack.mappers.DemoMapper;
import org.flashlack.util.MybatisUtil;

public class DemoMapperImpl implements DemoMapper {

    @Override
    public DemoDO demo() {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            DemoMapper mapper = sqlSession.getMapper(DemoMapper.class);
            return mapper.demo();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(int demo) {
        try (SqlSession sqlSession = MybatisUtil.getSqlSession()) {
            DemoMapper mapper = sqlSession.getMapper(DemoMapper.class);
            mapper.update(demo);
        }
    }
}
