package net.zzong.jdbcex;

import lombok.Data;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 김종인 on 2017-02-21.
 */
@Data
public class ObjectInformation<T> {
    Class<T> clazz;
    String nativeTableName;
    BeanInfo beanInfo;
    List<PropertyDescriptor> propertyDescriptor;
    Field keyField;

    public ObjectInformation(Class<T> clazz) {
        this.clazz = clazz;
        try {
            //nativeTableName = entityClass.getAnnotation(javax.persistence.Table.class).name();
            beanInfo = Introspector.getBeanInfo(clazz);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        propertyDescriptor = Arrays.stream(beanInfo.getPropertyDescriptors())
                .filter(propertyDescriptor1 -> !propertyDescriptor1.getPropertyType().getName().equals("java.lang.Class"))
                .collect(Collectors.toList());
        /*
        keyPropertyDescriptor = propertyDescriptor.stream()
                .filter(propertyDescriptor1 -> propertyDescriptor1.getPropertyType().getAnnotation(Id.class) instanceof Id)
                .findFirst()
                .orElse(null);
        */
        /*
        keyField = Arrays.stream(entityClass.getDeclaredFields())
                .filter(field -> field.getAnnotation(Id.class) instanceof Id)
                .findFirst()
                .orElse(null);
        keyField.setAccessible(true);

        System.out.println("keyField = "+keyField);
        */
    }

    public T newInstance (){
        T t=null;
        try {
            t = clazz.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.err.println("기본생성자가 없어서 Object형식으로 생성 합니다.");
            t = (T) new Object();
        }
        return t;
    }

}
