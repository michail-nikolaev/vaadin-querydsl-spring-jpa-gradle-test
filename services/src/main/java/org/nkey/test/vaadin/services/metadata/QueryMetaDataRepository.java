package org.nkey.test.vaadin.services.metadata;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.io.Serializable;
import java.util.List;

/**
 * @author m.nikolaev Date: 31.10.12 Time: 3:19
 */
public interface QueryMetaDataRepository<T, ID extends Serializable>
        extends JpaRepository<T, ID>, QueryDslPredicateExecutor<T> {
    List<T> findByQueryMetaData(QueryMetaData queryMetaData, Pageable pageable);
}
