package edu.softserve.zoo.persistence.repository.impl;

import edu.softserve.zoo.model.BaseEntity;
import edu.softserve.zoo.persistence.provider.PersistenceProvider;
import edu.softserve.zoo.persistence.repository.Repository;
import edu.softserve.zoo.persistence.specification.Specification;
import edu.softserve.zoo.persistence.specification.impl.CountSpecification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>Abstract implementation of the <tt>Repository</tt>. Help to implement concrete repositories for every
 * domain object. Implements routine CRUD operations.</p>
 *
 * @author Bohdan Cherniakh
 * @param <T> type of the domain object.
 */
public abstract class AbstractRepository<T extends BaseEntity> implements Repository<T> {

    /**
     * {@inheritDoc}
     */
    @Autowired
    private PersistenceProvider persistenceProvider;

    /**
     * {@inheritDoc}
     */
    @Override
    public T findOne(Long id, Class<T> type) {
        return (T) persistenceProvider.findOne(id, type);
    }

    @Override
    public Long count(Class<T> type) {
        return (Long) persistenceProvider.find(new CountSpecification<>(type)).get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T save(T entity) {
        return (T) persistenceProvider.save(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Long id , Class<T> type) {
        return persistenceProvider.delete(id, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T update(T entity) {
        return (T) persistenceProvider.update(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> find(Specification<T> specification) {
        return persistenceProvider.find(specification);
    }
}
