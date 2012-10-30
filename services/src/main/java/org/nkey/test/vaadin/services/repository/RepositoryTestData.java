package org.nkey.test.vaadin.services.repository;

import org.nkey.test.vaadin.domain.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * @author m.nikolaev Date: 31.10.12 Time: 1:43
 */
@Profile("dev")
@Configuration
public class RepositoryTestData {

    @Inject
    private UserRepository userRepository;


    private static final int USER_N = 20;

    @PostConstruct
    @Transactional
    public void init() {
        for (int q = 0; q < USER_N; q++) {
            User user = new User();
            user.setLogin("user_" + q);
            user.setPassword("password_" + q);
            userRepository.save(user);
        }

        userRepository.flush();
    }

}
