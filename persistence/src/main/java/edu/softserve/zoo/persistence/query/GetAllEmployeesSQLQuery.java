package edu.softserve.zoo.persistence.query;

import edu.softserve.zoo.model.Employee;
import edu.softserve.zoo.persistence.query.basic.SQLBasedQuery;

/**
 * @author Bohdan Cherniakh
 */
public class GetAllEmployeesSQLQuery extends SQLBasedQuery<Employee> {
    @Override
    public String getSql() {
        return "SELECT * FROM employees";
    }

    @Override
    public Class<Employee> getEntityType() {
        return Employee.class;
    }
}
