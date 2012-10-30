package org.nkey.test.vaadin.view.application.repository;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.ObjectProperty;
import org.nkey.test.vaadin.domain.AbstractEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author m.nikolaev Date: 31.10.12 Time: 2:27
 */
public class EntityItem<T extends AbstractEntity<T>> implements Serializable, Item {
    private Long entityId;
    private Map<String, Property> propertyMap = new HashMap<>();

    public EntityItem(Long entityId, Map<String, Object> propertyMap) {
        this.entityId = entityId;

        for (Map.Entry<String, Object> entry : propertyMap.entrySet()) {
            this.propertyMap.put(entry.getKey(), new ObjectProperty<>(entry.getValue()));
        }
    }

    public Long getEntityId() {
        return entityId;
    }

    @Override
    public Property getItemProperty(Object id) {
        return propertyMap.get(id.toString());
    }

    @Override
    public Collection<?> getItemPropertyIds() {
        return Collections.unmodifiableSet(propertyMap.keySet());
    }

    @Override
    public boolean addItemProperty(Object id, Property property) {
        throw new UnsupportedOperationException("Item is read-only.");
    }

    @Override
    public boolean removeItemProperty(Object id) {
        throw new UnsupportedOperationException("Item is read-only.");
    }

    public Map<String, Property> getPropertyMap() {
        return propertyMap;
    }
}
