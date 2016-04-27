package edu.softserve.zoo.persistence.query.basic;

import edu.softserve.zoo.persistence.query.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Class presents the plain SQL based query for Hibernate-managed persistent storage.
 * @param <T> the type of a domain object.
 * @author Bohdan Cherniakh
 */
public abstract class SQLBasedQuery<T> implements Query{

    /**
     * Performs query based on plain SQL {@link org.hibernate.SQLQuery} on the Hibernate-managed persistent storage.
     * @param session opened hibernate {@link Session}.
     * @return the collection of domain objects.
     */
    @Override
    public List performQuery(Session session) {
        return session.createSQLQuery(getSql()).addEntity(getEntityType()).list();
    }

    /**
     * Should return proper <tt>SQL statement</tt> in order to define query criteria.
     * @return string that presents SQL statement.
     */
    public abstract String getSql();

    /**
     * Shoud return the proper entity type to help Hibernate provide typed query.
     * @return type of a domain object.
     */
    public abstract Class<T> getEntityType();
}
