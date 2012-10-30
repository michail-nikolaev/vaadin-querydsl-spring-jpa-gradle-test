package org.nkey.test.vaadin;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author m.nikolaev Date: 31.10.12 Time: 0:24
 */
@Configuration
@ComponentScan("org.nkey.test.vaadin")
@ImportResource("classpath*:application-context.xml")
public class SpringConfig {

}
