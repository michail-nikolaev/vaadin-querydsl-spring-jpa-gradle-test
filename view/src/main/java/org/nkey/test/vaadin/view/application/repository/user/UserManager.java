package org.nkey.test.vaadin.view.application.repository.user;

import com.vaadin.data.Property;
import org.apache.commons.beanutils.PropertyUtils;
import org.nkey.test.vaadin.domain.User;
import org.nkey.test.vaadin.services.metadata.QueryMetaData;
import org.nkey.test.vaadin.services.repository.UserRepository;
import org.nkey.test.vaadin.view.application.repository.EntityItem;
import org.nkey.test.vaadin.view.application.repository.EntityItemManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author m.nikolaev Date: 31.10.12 Time: 2:33
 */
@Component
public class UserManager implements EntityItemManager<EntityItem<User>> {
    @SuppressWarnings("SpringJavaAutowiringInspection") @Inject
    private UserRepository repository;

    private EntityItem<User> fromUser(User user) {
        Map<String, Object> properties = new HashMap<>();
        try {
            for (PropertyDescriptor descriptor : PropertyUtils.getPropertyDescriptors(User.class)) {
                properties.put(descriptor.getName(), PropertyUtils.getProperty(user, descriptor.getName()));
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
        return new EntityItem<>(user.getId(), properties);
    }

    private User updateUserField(User user, EntityItem<User> entity) {
        try {
            for (Map.Entry<String, Property> entry : entity.getPropertyMap().entrySet()) {
                PropertyUtils.setProperty(user, entry.getKey(), entry.getValue().getValue());
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
        return user;
    }

    @Override
    public EntityItem<User> getItem(Long id) {
        return fromUser(repository.findOne(id));
    }

    @Override
    public List<EntityItem<User>> getEntities(QueryMetaData queryMetaData, Pageable pageable) {
        List<EntityItem<User>> result = new ArrayList<>();
        for (User user : repository.findByQueryMetaData(queryMetaData, pageable)) {
            result.add(fromUser(user));
        }
        return result;
    }

    @Override
    public EntityItem<User> saveItem(EntityItem<User> person) {
        return fromUser(repository.saveAndFlush(updateUserField(repository.findOne(person.getEntityId()), person)));
    }
}
