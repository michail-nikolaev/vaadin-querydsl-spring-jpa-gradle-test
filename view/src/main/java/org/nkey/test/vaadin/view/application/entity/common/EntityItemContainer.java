package org.nkey.test.vaadin.view.application.entity.common;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import org.nkey.test.vaadin.domain.AbstractEntity;
import org.nkey.test.vaadin.services.metadata.QueryMetaData;
import org.springframework.data.domain.PageRequest;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author m.nikolaev Date: 31.10.12 Time: 18:48
 */
public class EntityItemContainer<T extends AbstractEntity<T>> implements Container, Container.ItemSetChangeNotifier {
    private EntityItemManager<T> itemManager;
    private List<EntityItem<T>> entityItems;
    private Map<Long, EntityItem<T>> idToEntity;
    private List<String> fieldToUse;
    private Class<?> clazz;
    private List<ItemSetChangeListener> listeners = new ArrayList<>();

    public EntityItemContainer(EntityItemManager<T> itemManager, List<String> fieldToUse, Class<?> clazz) {
        this.clazz = clazz;
        this.itemManager = itemManager;
        this.fieldToUse = fieldToUse;
    }

    public static final QueryMetaData DEFAULT_QUERY_META_DATA = new QueryMetaData(null, null);
    protected QueryMetaData queryMetaData = DEFAULT_QUERY_META_DATA;

    public void refresh() {
        refresh(queryMetaData);
    }

    public void refresh(QueryMetaData queryMetaData) {
        this.queryMetaData = queryMetaData;
        entityItems = itemManager.getEntities(queryMetaData, new PageRequest(0, Integer.MAX_VALUE));
        idToEntity = new HashMap<>();
        for (EntityItem<T> entityItem : entityItems) {
            idToEntity.put(entityItem.getEntityId(), entityItem);
        }
        notifyListeners();
    }

    public QueryMetaData getQueryMetaData() {
        return queryMetaData;
    }

    public void entityItems() {
        if (entityItems != null) {
            entityItems.clear();
            entityItems = null;
        }
    }

    public boolean isOpen() {
        return entityItems != null;
    }

    public int size() {
        return entityItems == null ? 0 : entityItems.size();
    }

    @SuppressWarnings("unchecked")
    public Item getItem(Object itemId) {
        return idToEntity.get((Long) itemId);
    }

    public Collection<?> getContainerPropertyIds() {
        return fieldToUse;
    }

    public Collection<?> getItemIds() {
        return Collections.unmodifiableSet(idToEntity.keySet());
    }


    public List<EntityItem<T>> getItems() {
        return Collections.unmodifiableList(entityItems);
    }

    public Property idToEntity(Object itemId, Object propertyId) {
        //noinspection SuspiciousMethodCalls
        Item item = idToEntity.get(itemId);
        if (item != null) {
            return item.getItemProperty(propertyId);
        }
        return null;
    }

    public Class<?> getType(Object propertyId) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor((String) propertyId, clazz);
            return pd.getPropertyType();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean containsId(Object itemId) {
        //noinspection SuspiciousMethodCalls
        return idToEntity.containsKey(itemId);
    }

    @Override
    public boolean addContainerProperty(Object propertyId, Class<?> type, Object defaultValue)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Item addItem(Object itemId) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object addItem() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeItem(Object itemId) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeContainerProperty(Object propertyId) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAllItems() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Property getContainerProperty(Object itemId, Object propertyId) {
        //noinspection SuspiciousMethodCalls
        return idToEntity.get(itemId).getItemProperty(propertyId);
    }

    @Override
    synchronized public void removeListener(ItemSetChangeListener listener) {
        listeners.add(listener);
    }

    @Override
    synchronized public void addListener(ItemSetChangeListener listener) {
        listeners.remove(listener);
    }

    protected void notifyListeners() {
        List<ItemSetChangeListener> listenersSafe;
        synchronized (this) {
            listenersSafe = new ArrayList<>(listeners);
        }
        ItemSetChangeEvent event = new ItemSetChangeEvent() {
            public Container getContainer() {
                return EntityItemContainer.this;
            }
        };

        for (ItemSetChangeListener listener : listenersSafe) {
            listener.containerItemSetChange(event);
        }
    }


}
