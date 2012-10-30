package org.nkey.test.vaadin.view.application.repository.user;

import com.vaadin.data.Property;
import org.apache.commons.beanutils.PropertyUtils;
import org.nkey.test.vaadin.domain.User;
import org.nkey.test.vaadin.services.repository.UserRepository;
import org.nkey.test.vaadin.view.application.repository.EntityItem;
import org.nkey.test.vaadin.view.application.repository.EntityItemManager;
import org.nkey.test.vaadin.services.metadata.QueryMetaData;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author m.nikolaev Date: 31.10.12 Time: 2:33
 */
@Component
public class UserManager implements EntityItemManager<EntityItem<User>> {
    @Inject
    private UserRepository repository;

    private EntityItem<User> fromUser(User user, String... propertyNames) {
        Map<String, Object> properties = new HashMap<>();
        try {
            for (String descriptor : propertyNames) {
                properties.put(descriptor, PropertyUtils.getProperty(user, descriptor));
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
    public EntityItem getItem(Long id) {
        return fromUser(repository.findOne(id));
    }

    @Override
    public List<EntityItem<User>> getPersonReferences(QueryMetaData queryMetaData, String... propertyNames) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public EntityItem<User> saveItem(EntityItem<User> person) {
        return fromUser(repository.saveAndFlush(updateUserField(repository.findOne(person.getEntityId()), person)));
    }
}
