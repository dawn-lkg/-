package com.dawn.dawn.common.core.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章表(Article)表实体类
 *
 * @author 陈黎明
 * @since 2022/4/14 21:08
 */
public class BeanCopyUtils {
    private BeanCopyUtils(){

    }
    public static <V> V copyBean(Object source,Class<V> clazz){
        V result=null;
        try {
            result=clazz.newInstance();
            BeanUtils.copyProperties(source,result);

        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
        return  result;
    }
    public static <O,V> List<V>  copyBeanList(List<O> list, Class<V> clazz){
        List<V> result = new ArrayList<>();
        for (O o : list) {
            V v = copyBean(o, clazz);
            result.add(v);
        }
        return result;
    }

}
