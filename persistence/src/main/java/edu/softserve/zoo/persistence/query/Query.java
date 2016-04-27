package edu.softserve.zoo.persistence.query;

import org.hibernate.Session;

import java.util.List;

/**
 * Interface presents a query that can be performed and retrieve the data from the persistent storage.
 *
 * @param <T> - the type of a domain object.
 * @author Bohdan Cherniakh
 */
public interface Query<T> {

    /**
     * Method performs query on the persistent storage and returns the queried data.
     * @param session opened session.
     * @return List of domain objects that were collected from the persistent storage.
     */
    List<T> performQuery(Session session);
}
