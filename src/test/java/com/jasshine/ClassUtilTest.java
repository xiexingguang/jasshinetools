package com.jasshine;

import com.jasshine.reflect.ClassUtil;

import java.util.List;

/**
 * Created by ecuser on 2016/4/21.
 */
public class ClassUtilTest {

    public static void main(String[] args) {
        List<Class<?>> clas = ClassUtil.getClassListByPkgName("com.jasshine");
        for (Class<?> c : clas) {
            System.out.println(c.getName());
        }
    }
}
