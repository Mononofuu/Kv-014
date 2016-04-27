package edu.softserve.zoo.persistence.query.basic;

import edu.softserve.zoo.model.BaseEntity;
import edu.softserve.zoo.persistence.query.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Class presents the {@link DetachedCriteria} based query for Hibernate managed persistent storage.
 * @param <T> the type of a domain object.
 * @author Bohdan Cherniakh
 */
public abstract class CriteriaBasedQuery<T extends BaseEntity> implements Query<T> {

    /**
     * Performs {@link DetachedCriteria} based query on the Hibernate managed persistent storage.
     * @param session opened hibernate {@link Session}.
     * @return the collection of domain objects.
     */
    @Override
    public final List<T> performQuery(Session session) {
        return getCriteria().getExecutableCriteria(session).list();
    }

    /**
     * Method should return the proper <tt>DefinedCriteria</tt> in order to define the query criteria.
     * @return {@link DetachedCriteria} that defines the query criteria.
     * @see DetachedCriteria
     */
    protected abstract DetachedCriteria getCriteria();
}
