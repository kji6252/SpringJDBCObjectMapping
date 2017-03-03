package net.zzong.jdbcex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.object.SqlQuery;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

/**
 * Created by 김종인 on 2017-02-27.
 */
public class JongSqlQueryExample {
    @Autowired
    private DataSource dataSource;
    private SqlQuery<User> userSqlQuery;

    @PostConstruct
    private void postConstruct() {
        JongSqlQuery<User> jongSqlQuery = new JongSqlQuery<>();
        jongSqlQuery.setClazz(User.class);
        jongSqlQuery.setDataSource(dataSource);
        jongSqlQuery.setSql("select * from users where id = ?");
        jongSqlQuery.declareParameter(new SqlParameterValue(Types.BIGINT, "id"));
        this.userSqlQuery = jongSqlQuery;
    }

    public void runExample() {
        User user = loadUserById(1);
        System.out.printf("Person loaded: %s%n", user);
        user = loadUserById(2);
        System.out.printf("Person loaded: %s%n", user);
    }

    public User loadUserById(long id) {
        List<User> users = userSqlQuery.execute(id);
        if (users.size() == 1) {
            return users.get(0);
        }
        return null;
    }
}
