package edu.softserve.zoo.persistence.specification.impl;

import edu.softserve.zoo.persistence.specification.Specification;


public class HQLSpecification <T> implements Specification<T> {

    public HQLSpecification(Class type) {
        this.type = type;
    }

    private Class type;

    @Override
    public String from() {
        return type.getSimpleName();
    }
}
