package edu.softserve.zoo.persistence.query;

import edu.softserve.zoo.model.Employee;
import edu.softserve.zoo.persistence.query.basic.HQLBasedQuery;

/**
 * @author Bohdan Cherniakh
 */
public class GetAllEmployeesHQLQuery extends HQLBasedQuery<Employee> {

    @Override
    protected String getHql() {
        return "from Employee";
    }
}
