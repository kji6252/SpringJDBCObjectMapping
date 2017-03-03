package net.zzong.jdbcex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

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
	public JongSqlQueryExample genericSqlQueryExample(){
		return new JongSqlQueryExample();
	}

	@Bean
	CommandLineRunner run (JongSqlQueryExample jongSqlQueryExample){
		return args -> {
			System.out.println("헬로월드");

			jongSqlQueryExample.runExample();

		};
	}
}
