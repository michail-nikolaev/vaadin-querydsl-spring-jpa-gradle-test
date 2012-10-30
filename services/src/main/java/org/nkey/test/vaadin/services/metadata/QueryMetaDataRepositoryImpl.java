package org.nkey.test.vaadin.services.metadata;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * @author m.nikolaev Date: 31.10.12 Time: 3:20
 */
@NoRepositoryBean
public class QueryMetaDataRepositoryImpl<T, ID extends Serializable> extends QueryDslJpaRepository<T, ID>
        implements QueryMetaDataRepository<T, ID> {

    private EntityManager entityManager;
    private Class<T> clazz;

    public QueryMetaDataRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.clazz = entityInformation.getJavaType();
    }

    public QueryMetaDataRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager,
                                       EntityPathResolver resolver) {
        super(entityInformation, entityManager, resolver);
        this.entityManager = entityManager;
        this.clazz = entityInformation.getJavaType();
    }

    @Override
    public List<T> findByQueryMetaData(QueryMetaData queryMetaData, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);
        if (queryMetaData.getPropertyName() != null) {
            criteriaQuery = criteriaQuery.where(builder.like(root.<String>get(queryMetaData.getPropertyName()),
                    builder.literal(queryMetaData.getSearchTerm())));
        }

        for (Sort.Order order : pageable.getSort()) {
            if (order.isAscending()) {
                criteriaQuery = criteriaQuery.orderBy(builder.asc(root.get(order.getProperty())));
            } else {
                criteriaQuery = criteriaQuery.orderBy(builder.desc(root.get(order.getProperty())));
            }
        }

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        return query.getResultList();
    }
}
