package org.nkey.test.vaadin.test;

import org.junit.runner.RunWith;
import org.nkey.test.vaadin.SpringConfig;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author m.nikolaev Date: 31.10.12 Time: 0:22
 */
@ContextConfiguration(classes = SpringConfig.class)
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTestBase {
}
