package org.nkey.test.vaadin.services.dao;

import org.junit.Assert;
import org.junit.Test;
import org.nkey.test.vaadin.domain.User;
import org.nkey.test.vaadin.test.SpringTestBase;

import javax.inject.Inject;

/**
 * @author m.nikolaev Date: 31.10.12 Time: 0:29
 */
public class UserRepositoryTest extends SpringTestBase {
    @Inject
    private UserRepository userRepository;

    @Test
    public void testInject() {
        Assert.assertNotNull(userRepository);
    }

    @Test
    public void testCrud() {
        User user = new User();
        user.setLogin("nkey");
        user.setPassword("nkey");
        user = userRepository.saveAndFlush(user);
        Assert.assertNotNull(user.getId());
    }
}
