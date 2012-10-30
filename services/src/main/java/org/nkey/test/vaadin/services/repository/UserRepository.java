package org.nkey.test.vaadin.services.repository;

import org.nkey.test.vaadin.domain.User;
import org.nkey.test.vaadin.services.metadata.QueryMetaDataRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author m.nikolaev Date: 31.10.12 Time: 0:05
 */
@Transactional
public interface UserRepository extends QueryMetaDataRepository<User, Long> {

}
