package org.nkey.test.vaadin.view.application.repository;

import org.nkey.test.vaadin.services.metadata.QueryMetaData;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author m.nikolaev Date: 31.10.12 Time: 2:37
 */
public interface EntityItemManager<T extends EntityItem> {
    List<T> getEntities(QueryMetaData queryMetaData, Pageable pageable);

    T getItem(Long id);

    T saveItem(T entity);
}
