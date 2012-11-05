package org.nkey.test.vaadin.view.application.entity.common;

import org.nkey.test.vaadin.domain.AbstractEntity;
import org.nkey.test.vaadin.services.metadata.QueryMetaData;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author m.nikolaev Date: 31.10.12 Time: 2:37
 */
public interface EntityItemManager<T extends AbstractEntity<T>> {
    List<EntityItem<T>> getEntities(QueryMetaData queryMetaData, Pageable pageable);

    EntityItem<T> getItem(Long id);

    EntityItem<T> saveItem(EntityItem<T> entity);
}
