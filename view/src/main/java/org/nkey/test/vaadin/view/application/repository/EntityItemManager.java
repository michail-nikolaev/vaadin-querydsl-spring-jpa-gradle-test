package org.nkey.test.vaadin.view.application.repository;

import org.nkey.test.vaadin.services.metadata.QueryMetaData;

import java.util.List;

/**
 * @author m.nikolaev Date: 31.10.12 Time: 2:37
 */
public interface EntityItemManager<T extends EntityItem> {
    List<T> getPersonReferences(QueryMetaData queryMetaData, String... propertyNames);

    EntityItem getItem(Long id);

    EntityItem saveItem(T entity);
}
