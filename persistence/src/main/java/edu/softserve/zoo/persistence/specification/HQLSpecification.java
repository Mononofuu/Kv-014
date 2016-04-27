package edu.softserve.zoo.persistence.specification;

/**
 * @author Bohdan Cherniakh
 */
public interface HQLSpecification<T> extends Specification<T> {

//    String from();
    String queryIdentifier();
//    String query();
}
