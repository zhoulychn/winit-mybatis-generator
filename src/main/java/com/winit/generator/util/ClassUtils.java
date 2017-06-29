package com.winit.generator.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

/**
 * ClassName:ClassUtils. <br/>
 * Java类反射的工具类.
 * <p/>
 * 
 * @author qiyongkang
 * @version 1.0.0
 * @since JDK 1.6
 */
public abstract class ClassUtils {

    /**
     * INNER_CLASS_NAME_DELIMITER: 内部类的类名称在全路径中的分隔符： <code>$</code>.
     * 
     * @since JDK 1.6
     */
    public final static String INNER_CLASS_NAME_DELIMITER = "$";

    public static <T> T readWithClassType(Object obj, Class<T> clazz) {
        if (isNormalJavaType(clazz)) {
            String s = obj + "";
            if (clazz == String.class) {
                return clazz.cast(s);
            } else if (clazz == int.class || clazz == Integer.class) {
                return clazz.cast(Integer.parseInt(s));
            } else if (clazz == long.class || clazz == Long.class) {
                return clazz.cast(Long.parseLong(s));
            } else if (clazz == boolean.class || clazz == Boolean.class) {
                return clazz.cast(Boolean.parseBoolean(s));
            } else if (clazz == float.class || clazz == Float.class) {
                return clazz.cast(Float.parseFloat(s));
            } else if (clazz == double.class || clazz == Double.class) {
                return clazz.cast(Double.parseDouble(s));
            } else if (clazz == char.class || clazz == Character.class) {
                return clazz.cast(s);
            } else if (clazz == short.class || clazz == Short.class) {
                return clazz.cast(Short.parseShort(s));
            } else if (clazz == byte.class || clazz == Byte.class) {
                return clazz.cast(Byte.parseByte(s));
            } else if (clazz == BigDecimal.class) {
                return clazz.cast(Long.parseLong(s));
            } else {
                throw new IllegalArgumentException("Unknown class type[" + clazz + "] to read!");
            }
        } else {
            return clazz.cast(obj);
        }
    }

    /**
     * 是否为普通的Java类型. <br/>
     *
     * @author qiyongkang
     * @param clazz 要判断的类型
     * @return True/False
     * @since JDK 1.6
     */
    public static boolean isNormalJavaType(Class<?> clazz) {
        boolean isNormalJavaType = clazz == Integer.class || clazz == String.class || clazz == Long.class
                                   || clazz == Boolean.class || clazz == Float.class || clazz == Double.class
                                   || clazz == Character.class || clazz == Short.class || clazz == Byte.class
                                   || clazz == BigDecimal.class;
        return clazz.isPrimitive() || isNormalJavaType;
    }

    /**
     * isInnerClass: 判断一个类是否为内部类. <br/>
     *
     * @author qiyongkang
     * @param clazz 要判断的类
     * @return True/False
     * @since JDK 1.6
     */
    public static boolean isInnerClass(Class<?> clazz) {
        return clazz.isMemberClass();
    }

    /**
     * isPublicInnerClass: 判断一个类是否为公共的内部类. <br/>
     * 如果类的修饰符不是<code>public</code>那么就返回False.
     * 
     * @author qiyongkang
     * @param clazz 要判断的类
     * @return True/False
     * @since JDK 1.6
     */
    public static boolean isPublicInnerClass(Class<?> clazz) {
        return isInnerClass(clazz) && Modifier.toString(clazz.getModifiers()).toLowerCase().contains("public");
    }

    /**
     * isStaticInnerClass: 判断一个类是否为静态内部类. <br/>
     *
     * @author qiyongkang
     * @param clazz 要判断的类
     * @return True/False
     * @since JDK 1.6
     */
    public static boolean isStaticInnerClass(Class<?> clazz) {
        return isInnerClass(clazz) && Modifier.toString(clazz.getModifiers()).toLowerCase().contains("static");
    }

    /**
     * isPublicStaticInnerClass: 判断一个类是否为公共的静态内部类. <br/>
     * 如果类的修饰符不是<code>public</code>那么就返回False.
     * 
     * @author qiyongkang
     * @param clazz 要判断的类
     * @return True/False
     * @since JDK 1.6
     */
    public static boolean isPublicStaticInnerClass(Class<?> clazz) {
        return isStaticInnerClass(clazz) && Modifier.toString(clazz.getModifiers()).toLowerCase().contains("public");
    }

    /**
     * isCanInstance: 判断一个类是否可以被实例化. <br/>
     * 要判断一个类是否能够被实例化，这个类的修饰符必须是公共类，并且不允许这个类是抽象类，并且不允许这个是匿名类，
     * 并且不允许这个类是接口类，并且不允许这个类是枚举类，并且不允许是注解类。
     * 在这之后，如果这个类不是内部类那么就可以被实例化，如果是内部类，那么只能是公共的内部类以及公共的静态内部类才能够被实例化.
     * 
     * @author qiyongkang
     * @param clazz 要判断的类
     * @return True/False
     * @since JDK 1.6
     */
    public static boolean isCanInstance(Class<?> clazz) {
        String model = Modifier.toString(clazz.getModifiers()).toLowerCase();
        return model.contains("public") && !model.contains("abstract") && !clazz.isAnonymousClass()
               && !clazz.isInterface() && !clazz.isEnum() && !clazz.isAnnotation()
               && (isInnerClass(clazz) ? (isPublicInnerClass(clazz) || isPublicStaticInnerClass(clazz)) : true);
    }

    /**
     * isCanInstance: 根据类名称的全路径判断一个类是否可以被实例化. <br/>
     *
     * @author qiyongkang
     * @param className 要判断的类名称的全路径
     * @return True/False
     * @since JDK 1.6
     */
    public static boolean isCanInstance(String className) {
        if (StringUtil.isEmpty(className) || !isAvailable(className)) {
            return false;
        }
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            return false;
        }
        return isCanInstance(clazz);
    }

    /**
     * newInstance: 根据类的完整路径，创建类对象. <br/>
     *
     * @author qiyongkang
     * @param className 类的完整路径
     * @return 创建的对象
     * @since JDK 1.6
     */
    public static Object newInstance(String className) {
        if (!isCanInstance(className)) {
            return null;
        }
        try {
            Class<?> clazz = Class.forName(className);
            if (isPublicStaticInnerClass(clazz)) {
                return clazz.newInstance();
            } else {
                if (isPublicInnerClass(clazz)) {
                    return newInnerClassInstance(className, clazz);
                } else {
                    return isInnerClass(clazz) ? null : clazz.newInstance();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * newInnerClassInstance: 实例化一个内部类. <br/>
     *
     * @author qiyongkang
     * @param className 类名称的全路径
     * @param clazz 要实例化的类
     * @return 实例化之后的对象
     * @throws Exception 实例化类遇到错误时抛出的异常
     * @since JDK 1.6
     */
    private static Object newInnerClassInstance(String className, Class<?> clazz) throws Exception {
        String parentClassName = className.substring(0, className.indexOf(INNER_CLASS_NAME_DELIMITER));
        Object parent = newInstance(parentClassName);
        Constructor<?> constructor = null;
        for (Constructor<?> c : clazz.getConstructors()) {
            if (1 == c.getParameterTypes().length) {
                constructor = c;
                break;
            }
        }
        if (null != constructor) {
            return constructor.newInstance(parent);
        }
        return null;
    }

    /**
     * newInstance: 创建一个对象实例. <br/>
     *
     * @author qiyongkang
     * @param className 创建对象的全路径
     * @param clazz 创建的对象类型
     * @return 对象实例
     * @since JDK 1.6
     */
    public static <T> T newInstance(String className, Class<T> clazz) {
        Object obj = newInstance(className);
        if (null == obj) {
            return null;
        }
        return clazz.cast(obj);
    }

    /**
     * getPropertyValues: 获取类中的私有属性的值. <br/>
     *
     * @author qiyongkang
     * @param obj 要获取的类
     * @return 获取的属性值数组
     * @since JDK 1.6
     */
    public static Object[] getPropertyValues(Object obj) {
        List<Object> l = new ArrayList<Object>();
        Class<?> clazz = obj.getClass();
        for (Field f : clazz.getDeclaredFields()) {
            String mod = Modifier.toString(f.getModifiers()).toLowerCase();
            if (mod.contains("public") || mod.contains("protected") || mod.contains("static")
                || mod.contains("final")) {
                continue;
            }
            if (isNormalJavaType(f)) {
                Method method = null;
                try {
                    method = clazz.getDeclaredMethod("get" + StringUtil.upperFirst(f.getName()));
                } catch (Exception e) {
                }

                if (null != method) {
                    Object returnObj = null;
                    try {
                        returnObj = method.invoke(obj);
                    } catch (Exception e) {
                    }

                    if (null != returnObj) {
                        if (returnObj instanceof String) {
                            if (!StringUtil.isEmpty(returnObj + "")) {
                                l.add(returnObj);
                            }
                        } else {
                            l.add(returnObj);
                        }
                    }
                }
            }
        }
        return l.toArray();
    }

    /**
     * isNormalJavaType: 是否为普通的Java类型. <br/>
     *
     * @author qiyongkang
     * @param f 要判断的字段
     * @return True/False
     * @since JDK 1.6
     */
    public static boolean isNormalJavaType(Field f) {
        Class<?> clazz = f.getType();
        boolean isNormalJavaType = clazz == Integer.class || clazz == String.class || clazz == Long.class
                                   || clazz == Boolean.class || clazz == Float.class || clazz == Double.class
                                   || clazz == Character.class || clazz == Short.class || clazz == Byte.class;
        return clazz.isPrimitive() || isNormalJavaType;
    }

    /**
     * isAvailable: 判断一个类是否可以用. <br/>
     *
     * @author qiyongkang
     * @param className 类的全路径
     * @return True/False
     * @since JDK 1.6
     */
    public static boolean isAvailable(String className) {
        try {
            Class<?> clazz = Class.forName(className, false, getDefaultClassLoader());
            return !(null == clazz);
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * getDefaultClassLoader: 获取默认的类加载器. <br/>
     *
     * @author qiyongkang
     * @return 类加载器
     * @since JDK 1.6
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable throwable) {
        }
        if (null == cl) {
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

    /**
     * getJarMainFunction: 获取jar中在Manifest中配置的Main-Class属性值，就是jar在运行的主函数类. <br/>
     *
     * @author qiyongkang
     * @param jarFilePath jar文件路径
     * @return jar运行时调用的主函数名称
     * @since JDK 1.6
     */
    @SuppressWarnings("resource")
    public static String getJarMainFunction(String jarFilePath) {
        try {
            JarFile jar = new JarFile(new File(jarFilePath));
            return jar.getManifest().getMainAttributes().getValue("Main-Class");
        } catch (IOException e) {
            return null;
        }
    }
}
