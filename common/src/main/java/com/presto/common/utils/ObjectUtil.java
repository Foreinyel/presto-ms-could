package com.presto.common.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.util.*;

/**
 * Created by shihao on 16/10/29.
 */
public class ObjectUtil {

    /**
     * 将Map转换成目标类的对象
     *
     * @param map
     * @param targetClazz
     *            要转换的目标类
     * @return
     */
    public static <T> T fromMap(Map<String, Object> map, Class<T> targetClazz) {
        if (null == map)
            return null;
        try {
            T t = targetClazz.newInstance();
            int length = targetClazz.getDeclaredFields().length;
            // 比较大小决定迭代谁。
            if (length > map.size()) {
                map.forEach((key, value) -> {
                    PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(targetClazz, key);
                    if (pd != null) {
                        try {
                            pd.getWriteMethod().invoke(t, value);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                for (PropertyDescriptor pd : BeanUtils.getPropertyDescriptors(targetClazz)) {
                    if (!pd.getName().equals("class"))
                        pd.getWriteMethod().invoke(t, map.get(pd.getName()));
                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对象转map
     *
     * @param o
     * @return
     */
    public static Map<String, Object> fromObject(Object o) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (PropertyDescriptor pd : BeanUtils.getPropertyDescriptors(o.getClass())) {
            try {
                map.put(pd.getName(), pd.getReadMethod().invoke(o));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 不同类对象之间的转化
     *
     * @param s
     *            源对象
     * @param d
     *            目标对象的类
     * @return
     */
    public static <S, D> D convertObject(S s, Class<D> d) {
        return convertObject(s, d, null);
    }

    /**
     * 不同类对象之间的转化
     *
     * @param s
     *            源对象
     * @param d
     *            目标对象的类
     * @param fieldMapping
     *            名称不同时的字段映射，key 代表源对象字段名称，value代表目标对象字段名称
     * @return
     */
    public static <S, D> D convertObject(S s, Class<D> d, Map<String, String> fieldMapping) {
        PropertyDescriptor[] spds = BeanUtils.getPropertyDescriptors(s.getClass());
        PropertyDescriptor[] dpds = BeanUtils.getPropertyDescriptors(d);
        try {
            D dest = d.newInstance();
            if (spds.length > dpds.length) {
                for (PropertyDescriptor dpd : dpds) {
                    // step1，查看source bean中是否存在该property
                    PropertyDescriptor spd = BeanUtils.getPropertyDescriptor(s.getClass(), dpd.getName());
                    // 未找到则看字段映射
                    if (spd == null) {
                        if (fieldMapping != null) {
                            String sField = null;
                            Set<Map.Entry<String, String>> entrys = fieldMapping.entrySet();
                            for (Map.Entry<String, String> entry : entrys) {
                                if (entry.getValue().equals(dpd.getName())) {
                                    sField = entry.getKey();
                                    break;
                                }
                            }
                            if (StringUtils.isBlank(sField)) {
                                continue;
                            }
                            PropertyDescriptor spDescriptor = BeanUtils.getPropertyDescriptor(s.getClass(), sField);
                            try {
                                if (spDescriptor != null) {
                                    Object returnObj = spDescriptor.getReadMethod().invoke(s);
                                    dpd.getWriteMethod().invoke(dest, convertClass(returnObj, dpd.getPropertyType()));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            continue;
                        }
                    } else {
                        try {
                            /*
                             * System.out.println(
                             * "properties:----------------------:"+dpd.getName(
                             * )); if (!exceptions.contains(spd.getName()) &&
                             * !dpd.getName().equals("class")) {
                             */
                            if (!dpd.getName().equals("class")) {
                                Object returnObj = spd.getReadMethod().invoke(s);
                                dpd.getWriteMethod().invoke(dest, convertClass(returnObj, dpd.getPropertyType()));
                            }
                        } catch (Exception e) {
                            /*
                             * if (fieldMapping != null &&
                             * fieldMapping.containsKey(spd.getName())) {
                             * PropertyDescriptor dpDescriptor =
                             * BeanUtils.getPropertyDescriptor(d,
                             * fieldMapping.get(spd.getName())); if
                             * (dpDescriptor != null) {
                             * dpDescriptor.getWriteMethod().invoke(dest,
                             * convertClass(spd.getReadMethod().invoke(s),
                             * dpDescriptor.getPropertyType()));
                             * exceptions.add(dpDescriptor.getName()); } }
                             */
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                for (PropertyDescriptor spd : spds) {
                    // step1，查看dest bean中是否存在该property
                    PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(d, spd.getName());
                    // 未找到则看字段映射
                    if (pd == null) {
                        if (fieldMapping != null) {
                            String dField = null;
                            Set<Map.Entry<String, String>> entrys = fieldMapping.entrySet();
                            for (Map.Entry<String, String> entry : entrys) {
                                if (entry.getKey().equals(spd.getName())) {
                                    dField = entry.getValue();
                                    break;
                                }
                            }
                            if (StringUtils.isBlank(dField)) {
                                continue;
                            }
                            // 字段映射中找到匹配字段
                            PropertyDescriptor dpDescriptor = BeanUtils.getPropertyDescriptor(d, dField);
                            try {
                                if (dpDescriptor != null) {
                                    Object returnObj = spd.getReadMethod().invoke(s);
                                    // 类型相同时设置
                                    dpDescriptor.getWriteMethod().invoke(dest,
                                            convertClass(returnObj, dpDescriptor.getPropertyType()));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            continue;
                        }
                    } else {
                        try {
                            // 排除class
                            if (!pd.getName().equals("class")) {
                                Object returnObj = spd.getReadMethod().invoke(s);
                                pd.getWriteMethod().invoke(dest, convertClass(returnObj, pd.getPropertyType()));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return dest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 弱弱哒类型转化帮助类
     *
     * @param o
     * @param targetClass
     * @return
     */
    @SuppressWarnings("unchecked")
    private static <D> D convertClass(Object o, Class<D> targetClass) {
        if (o == null)
            return null;
        if (o.getClass().equals(targetClass))
            return (D) o;
        if (String.class.equals(targetClass) && o instanceof Date) {
            return (D) DateFormatUtils.format((Date) o, "yyyy-MM-dd HH:mm:ss");
        }
        if (String.class.equals(targetClass)) {
            return (D) o.toString();
        }
        if (Long.class.equals(targetClass) && o instanceof String) {
            return (D) Long.valueOf((String) o);
        }
        if (Short.class.equals(targetClass) && o instanceof String) {
            return (D) Short.valueOf((String) o);
        }
        if (Integer.class.equals(targetClass) && o instanceof String) {
            return (D) Integer.valueOf((String) o);
        }
        return null;
    }

    /**
     * 集合类型转化
     *
     * @param ss
     * @param d
     * @param fieldMapping
     * @return
     */
    public static <S, D> List<D> convertList(List<S> ss, Class<D> d, Map<String, String> fieldMapping) {
        if (ss == null)
            return new ArrayList<D>();
        List<D> ds = new ArrayList<D>();
        ss.forEach(s -> {
            ds.add(convertObject(s, d, fieldMapping));
        });
        return ds;
    }

    /**
     * 集合类型转化
     *
     * @param ss
     * @param d
     * @return
     */
    public static <S, D> List<D> convertList(List<S> ss, Class<D> d) {
        if (ss == null)
            return new ArrayList<D>();
        List<D> ds = new ArrayList<D>();
        ss.forEach(s -> {
            ds.add(convertObject(s, d));
        });
        return ds;
    }

    public static <T> List<T> convertListFromMap(List<Map<String, Object>> maps, Class<T> targetClazz) {
        if (maps == null)
            return new ArrayList<T>();
        List<T> ds = new ArrayList<T>();
        maps.forEach(s -> {
            ds.add(fromMap(s, targetClazz));
        });
        return ds;
    }

    /**
     * 对象转数组
     *
     * @param obj
     * @return
     */
    public static byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    /**
     * 数组转对象
     *
     * @param bytes
     * @return
     */
    public static Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }
}
