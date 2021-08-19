package com.billo.quickjoin;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		/*User user = new User();

		user.setUsername("홍길동");
		user.setNickname("닉네임");
		user.setPassword("pwd");
		user.setActivated(true);

		Session session = entityManager.unwrap(Session.class);

		session.save(user);*/
	}

}
