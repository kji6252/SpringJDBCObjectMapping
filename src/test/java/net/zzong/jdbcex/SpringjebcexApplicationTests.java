package net.zzong.jdbcex;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringjebcexApplicationTests {

	@Autowired
	OMSqlQuery<User> omSqlQuery;

	@Test
	public void 예제실행() {
		System.out.println(loadUserById(1));
		System.out.println(loadUserById(2));
		System.out.println(loadUserById(3));
	}

	public User loadUserById(long id) {
		List<User> users = omSqlQuery.execute(id);
		if (users.size() == 1) {
			return users.get(0);
		}
		return null;
	}

}
