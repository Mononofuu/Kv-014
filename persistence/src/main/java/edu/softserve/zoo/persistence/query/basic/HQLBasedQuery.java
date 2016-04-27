package edu.softserve.zoo.persistence.query.basic;

import edu.softserve.zoo.model.BaseEntity;
import edu.softserve.zoo.persistence.query.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Class presents the HQL based {@link org.hibernate.Query} for Hibernate-managed persistent storage.
 * @param <T> the type of a domain object.
 * @author Bohdan Cherniakh
 */
public abstract class HQLBasedQuery<T extends BaseEntity> implements Query<T> {

    /**
     * Performs query based on HQL {@link org.hibernate.Query} on the Hibernate managed persistent storage.
     * @param session opened hibernate {@link Session}.
     * @return the collection of domain objects.
     */
    @Override
    public final List<T> performQuery(Session session) {
        return session.createQuery(getHql()).list();
    }

    /**
     * Method should return the proper <tt>HQL statement</tt> in order to define the query criteria.
     * @return String that presents HQL query.
     */
    protected abstract String getHql();
}
