package edu.softserve.zoo.persistence.provider;

import edu.softserve.zoo.persistence.specification.Specification;

import java.util.List;

/**
 * @author Bohdan Cherniakh
 */
public interface SpecificationProcessor<T> {
    List<T> process(Specification<T> specification);
}
