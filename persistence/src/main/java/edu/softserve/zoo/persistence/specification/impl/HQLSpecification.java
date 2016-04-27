package edu.softserve.zoo.persistence.specification.impl;

import edu.softserve.zoo.persistence.specification.Specification;


public class HQLSpecification <T> implements edu.softserve.zoo.persistence.specification.HQLSpecification<T> {

    public HQLSpecification(Class type) {
        this.type = type;
    }

    private Class type;


    @Override
    public String queryIdentifier() {
        return "hql.selectAllGeographicalZone";
    }

    @Override
    public boolean equals(Object o) {
       return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
