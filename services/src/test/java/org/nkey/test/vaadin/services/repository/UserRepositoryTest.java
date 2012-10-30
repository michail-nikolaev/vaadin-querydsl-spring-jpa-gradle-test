package org.nkey.test.vaadin.services.repository;

import org.junit.Assert;
import org.junit.Test;
import org.nkey.test.vaadin.domain.User;
import org.nkey.test.vaadin.services.metadata.QueryMetaData;
import org.nkey.test.vaadin.test.SpringTestBase;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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

        Assert.assertEquals(1, userRepository.findByQueryMetaData(new QueryMetaData("login", "nk%"),
                new PageRequest(0, 10, Sort.Direction.ASC, "password")).size());
    }
}
