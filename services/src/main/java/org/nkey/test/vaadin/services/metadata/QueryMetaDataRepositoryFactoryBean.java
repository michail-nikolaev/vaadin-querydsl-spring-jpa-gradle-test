package org.nkey.test.vaadin.services.metadata;

import org.nkey.test.vaadin.services.metadata.QueryMetaDataRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author m.nikolaev Date: 31.10.12 Time: 3:31
 */
public class QueryMetaDataRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, I> {

    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {

        return new QueryMetaDataRepositoryFactory(entityManager);
    }

    private static class QueryMetaDataRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

        private EntityManager entityManager;

        public QueryMetaDataRepositoryFactory(EntityManager entityManager) {
            super(entityManager);

            this.entityManager = entityManager;
        }

        protected Object getTargetRepository(RepositoryMetadata metadata) {
            return new QueryMetaDataRepositoryImpl<>(
                    new JpaMetamodelEntityInformation<T, I>((Class<T>) metadata.getDomainType(),
                            entityManager.getMetamodel()), entityManager);
        }

        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return metadata.getRepositoryInterface();
        }
    }
}
