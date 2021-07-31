package com.mainul35.cdi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class ContextHolder {

    private static Context context =  new Context();

    private ContextHolder() {
        if (context == null) {
            context = new Context();
        }
    }

    public static class Context {

        private Map<String, Object> beans = new HashMap<>();

        Context () {
            String packageName = "com.mainul35.application";
            Set<Class> classes = findAllClassesUsingClassLoader(packageName);
            classes.stream().forEach(clazz -> {
                try {
                    var instance = clazz.getConstructor(new Class[]{}).newInstance(new Object[]{});
                    createBean(clazz, instance);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }

        private void createBean(Class clazz, Object instance) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
            if (clazz.getAnnotation(Component.class) != null) {
                for (Field field : clazz.getDeclaredFields()) {
                    checkFieldsAnnotatedWithAutowired(instance, field);
                }
                beans.put(clazz.getName(), instance);
            }
        }

        private void checkFieldsAnnotatedWithAutowired(Object instance, Field field) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
            if (field.getAnnotation(Autowired.class) != null) {
                var className = field.getType().getName();
                var bean = beans.get(className);
                if (bean == null) {
                    var clazz = Class.forName(className);
                    createBean(clazz, clazz.getConstructor(new Class[]{}).newInstance(new Object[]{}));
                    injectBean(instance, field, beans.get(className), className);
                } else {
                    injectBean(instance, field, bean, className);
                }
            }
        }

        private void injectBean(Object instance, Field field, Object bean, String fullyQualifiedClassName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
            if (beans.get(fullyQualifiedClassName) != null) {
                field.setAccessible(true);
                try {
                    field.set(instance, bean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                var clazz =Class.forName(fullyQualifiedClassName);
                createBean(clazz, clazz.getConstructor(new Class[]{}).newInstance(new Object[]{}));
            }
        }

        public Set<Class> findAllClassesUsingClassLoader(String packageName) {
            InputStream stream = ClassLoader.getSystemClassLoader()
                    .getResourceAsStream(packageName.replaceAll("[.]", "/"));
            assert stream != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            return reader.lines()
                    .filter(line -> line.endsWith(".class"))
                    .map(line -> getClass(line, packageName))
                    .collect(Collectors.toSet());
        }

        private Class getClass(String className, String packageName) {
            try {
                return Class.forName(packageName + "."
                        + className.substring(0, className.lastIndexOf('.')));
            } catch (ClassNotFoundException e) {
                // handle the exception
            }
            return null;
        }

        public Object getBean(String fullyQualifiedClassName) {
            return beans.get(fullyQualifiedClassName);
        }

        public <T> T getBean(Class<T> clazz) {
           return (T) beans.get(clazz.getName());
        }
    }

    public static Context getContext() {
        return context;
    }

}
