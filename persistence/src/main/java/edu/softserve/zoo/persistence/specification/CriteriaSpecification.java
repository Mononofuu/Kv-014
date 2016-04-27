package edu.softserve.zoo.persistence.specification;

import org.hibernate.criterion.Criterion;

/**
 * @author Bohdan Cherniakh
 */
public interface CriteriaSpecification<T> extends Specification<T>{

    Class<T> getType();

    Criterion query();
}
