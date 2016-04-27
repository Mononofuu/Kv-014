package edu.softserve.zoo.persistence.query;

import edu.softserve.zoo.model.Employee;
import edu.softserve.zoo.persistence.query.basic.CriteriaBasedQuery;
import org.hibernate.criterion.DetachedCriteria;

/**
 * @author Bohdan Cherniakh
 */
public class GetAllEmployeesCriteriaQuery extends CriteriaBasedQuery<Employee> {
    @Override
    protected DetachedCriteria getCriteria() {
        return DetachedCriteria.forClass(Employee.class);
    }
}
