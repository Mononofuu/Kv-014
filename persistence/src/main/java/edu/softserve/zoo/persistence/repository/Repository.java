package edu.softserve.zoo.persistence.repository;

import edu.softserve.zoo.exceptions.persistence.PersistenceException;
import edu.softserve.zoo.model.BaseEntity;
import edu.softserve.zoo.persistence.specification.Specification;

import java.util.List;

/**
 * <p>Object that represents Repository. Works with the domain objects of the given type.</p>
 * <p>Repository implements basic CRUD operations for domain objects called entities.
 * The find operation is implemented using {@link Specification}</p>
 *
 * @param <T> the type of the domain objects which are stored.
 * @author Bohdan Cherniakh
 */
public interface Repository<T extends BaseEntity> {


    /**
     * Retrieves entity by id from persistent storage.
     *
     * @param id identifier of required entity
     * @param type type of required entity
     * @return entity with specified identifier.
     * @throws edu.softserve.zoo.persistence.exception.NotFoundException if entity not found with given id
     */
    T findOne(Long id, Class<T> type);

    /**
     * Saves the entity into the repository.
     *
     * @param entity - an object that should be stored.
     * @return saved entity with generated identifier.
     * @throws PersistenceException given the information about the problem that occurred with the storage.
     */
    T save(T entity);

    /**
     * Deletes the entity from the repository
     *
     * @param id - identifier of object that should be deleted.
     * @param type - type object that should be deleted.
     * @throws PersistenceException given the information about the problem that occurred with the storage.
     */
    boolean delete(Long id, Class<T> type);

    /**
     * Updates the entity state in the repository.
     *
     * @param entity - object which state should be updated.
     * @return entity with updated state.
     * @throws PersistenceException given the information about the problem that occurred with the storage.
     */
    T update(T entity);

    /**
     * Finds entities in the repository according to given Specification.
     *
     * @param specification defines restrictions for performed search.
     * @return The {@link List} of domain objects or null if there are no objects in the database that match the query.
     * @throws PersistenceException given the information about the problem that occurred with the storage.
     * @see Specification
     */
    List<T> find(Specification<T> specification);

}
