package org.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class UserDaoTest {

	// 테스트 코드가 실행되기 전에, 미리 실행함
	@BeforeEach
	void setUp() {
		// db_schema.sql 파일을 ClassPath에서 읽어와서 script에 추가하기
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("db_schema.sql"));

		// 위의 스크립트 실행하기
		DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
	}

	@Test
	void createTest() throws SQLException {
		UserDao userDao = new UserDao();

		userDao.create(new User("imhope", "password", "name", "email"));

		User user = userDao.findByUserId("imhope");

		assertThat(user).isEqualTo(new User("imhope", "password", "name", "email"));
	}
}
