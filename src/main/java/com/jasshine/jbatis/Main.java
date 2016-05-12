package com.jasshine.jbatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ecuser on 2016/2/25.
 */
public class Main {

    public static void main(String[] args) throws  Exception{
        String res = "mybatis-cfg.xml";
        Reader reader = Resources.getResourceAsReader(res);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Map map = new HashMap();
        map.put("id", 5);

        System.out.println(sqlSession.selectOne("userRole.queryUsers", map));
        sqlSession.commit();
        sqlSession.selectOne("userRole.queryUsers", map);
        System.out.println(sqlSession);
    }

}
