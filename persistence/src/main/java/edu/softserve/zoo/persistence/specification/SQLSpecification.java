package edu.softserve.zoo.persistence.specification;

/**
 * @author Bohdan Cherniakh
 */
public interface SQLSpecification<T> extends Specification<T> {
    Class<T> getType();

    @Override
    String query();
}
