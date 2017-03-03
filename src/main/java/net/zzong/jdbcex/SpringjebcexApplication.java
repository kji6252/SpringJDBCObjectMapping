package net.zzong.jdbcex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.Types;

@SpringBootApplication
public class SpringjebcexApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringjebcexApplication.class, args);
	}

	@Bean
	public DataSource h2DataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("import.sql")
				.build();
	}

	@Bean
	public OMSqlQuery<User> omSqlQuery(DataSource dataSource){
		OMSqlQuery<User> omSqlQuery = new OMSqlQuery<>();
		omSqlQuery.setClazz(User.class);
		omSqlQuery.setDataSource(dataSource);
		omSqlQuery.setSql("select * from users where id = ?");
		omSqlQuery.declareParameter(new SqlParameterValue(Types.BIGINT, "id"));
		return omSqlQuery;
	}
}
