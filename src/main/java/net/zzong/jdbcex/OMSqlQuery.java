package net.zzong.jdbcex;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.SqlQuery;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by 김종인 on 2017-03-02.
 */
public class OMSqlQuery<T> extends SqlQuery {

    private Class<T> clazz;

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    protected RowMapper<T> newRowMapper(Object[] parameters, Map context) {
        return (ResultSet rs, int rowNum) -> {
            ObjectInformation<T> objectInformation = new ObjectInformation<T>(clazz);
            T tObject = objectInformation.newInstance();

            ResultSetMetaData metaData = rs.getMetaData();
            for (int i=1;i<=metaData.getColumnCount();i++){
                final String columnName = metaData.getColumnName(i).toLowerCase();
                T finalTObject = tObject;
                Arrays.stream(objectInformation.getBeanInfo().getPropertyDescriptors())
                        .filter(propertyDescriptor -> propertyDescriptor.getName().equals(columnName))
                        .forEach(propertyDescriptor -> {
                            try {
                                propertyDescriptor.getWriteMethod()
                                        .invoke(finalTObject, rs.getObject(columnName));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
            }
            return tObject;
        };
    }
}
