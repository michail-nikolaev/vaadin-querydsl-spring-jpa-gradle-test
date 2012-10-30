package org.nkey.test.vaadin.services.dao;

import org.nkey.test.vaadin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author m.nikolaev Date: 31.10.12 Time: 0:05
 */
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

}
