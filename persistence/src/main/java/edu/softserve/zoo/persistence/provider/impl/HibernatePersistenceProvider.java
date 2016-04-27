package edu.softserve.zoo.persistence.provider.impl;

import edu.softserve.zoo.persistence.exception.PersistenceException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.softserve.zoo.persistence.provider.SpecificationProcessor;
import edu.softserve.zoo.persistence.specification.CriteriaSpecification;
import edu.softserve.zoo.persistence.specification.HQLSpecification;
import edu.softserve.zoo.persistence.specification.SQLSpecification;

import edu.softserve.zoo.persistence.provider.PersistenceProvider;
import edu.softserve.zoo.persistence.specification.Specification;
import org.apache.commons.lang3.ClassUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Hibernate based implementation of the {@link PersistenceProvider}.</p>
 * <p>Implements CRUD operations with relational database</p>
 *
 * @author Bohdan Cherniakh
 * @param <T> the type of the domain objects which are stored. Should be properly mapped.
 */
@Component
public class HibernatePersistenceProvider<T> implements PersistenceProvider<T> {

    private Map<Class<Specification<T>>, SpecificationProcessor<T>> decisionMap = new HashMap<>();

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernatePersistenceProvider.class);
    public HibernatePersistenceProvider() {
        decisionMap.put((Class<Specification<T>>)(Class<?>) CriteriaSpecification.class, new CriteriaSpecificationProcessor());
        decisionMap.put((Class<Specification<T>>)(Class<?>) SQLSpecification.class, new SQLSpecificationProcessor());
        decisionMap.put((Class<Specification<T>>)(Class<?>) HQLSpecification.class, new HQLSpecificationProcessor());
    }

    /**
     * Saves the domain object into the relational database.
     * @param entity - an object that should be saved.
     * @return saved entity with generated identifier.
     */


    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public T save(T entity) {
        try {
            Session session = getSession();
            session.save(entity);
            return entity;
        } catch (HibernateException ex) {
            throw new PersistenceException(ex.getMessage(), ex.getCause());
        }
    }

    /**
     * Updates the tables connected with domain object in the relational database.
     * @param entity - the domain object that should be updated.
     * @return updated entity.
     */

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public T update(T entity) {
        try {
            Session session = getSession();
            session.update(entity);
            return entity;
        } catch (HibernateException ex) {
            throw new PersistenceException(ex.getMessage(), ex.getCause());
            //TODO add logging properly (after issue #42)
        }
    }

    /**
     * Deletes the given entity from the persistent storage.
     * @param entity domain object that should be deleted.
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(T entity) {
        try {
            Session session = getSession();
            session.delete(entity);
        } catch (HibernateException ex) {
            throw new PersistenceException(ex.getMessage(), ex.getCause());
        }
    }

    /**
     * Finds the collection of domain objects in the relational database. The search criteria is defined by the
     * Specification object.
     * @param specification the {@link Specification} object that describes the specification that should be performed.
     * @return The collection of domain objects or null if there are no objects in the database that match the specification.
     * @see Specification
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true )
    public Collection<T> find(Specification<T> specification) {
        List<T> data = null;
        try {
            List<Class<?>> interfacesList =ClassUtils.getAllInterfaces(specification.getClass());
            interfacesList.forEach(System.out::println);
            SpecificationProcessor processor = decisionMap.get(interfacesList.get(0));

            data = processor.process(specification);
            Session session = getSession();



        } catch (HibernateException ex) {
            throw new PersistenceException(ex.getMessage(), ex.getCause());
        }
        return data;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private class CriteriaSpecificationProcessor implements SpecificationProcessor<T> {
        @Override
        public List<T> process(Specification<T> specification) {
            CriteriaSpecification<T> speca = (CriteriaSpecification<T>) specification;
            return getSession().createCriteria(speca.getType()).add(speca.query()).list();
        }
    }

    private class SQLSpecificationProcessor implements SpecificationProcessor<T> {
        @Override
        public List<T> process(Specification<T> specification) {
            SQLSpecification<T> speca = (SQLSpecification<T>) specification;
            return getSession().createSQLQuery(speca.query()).addEntity(speca.getType()).list();
        }
    }

    private class HQLSpecificationProcessor implements SpecificationProcessor<T> {
        @Override
        public List<T> process(Specification<T> specification) {
            HQLSpecification<T> hqlSpecification = (HQLSpecification<T>) specification;
            String hqlQuery = messageSource.getMessage(hqlSpecification.queryIdentifier(),
                    null,null);
            return getSession().createQuery(hqlQuery).list();

        }
    }
}
