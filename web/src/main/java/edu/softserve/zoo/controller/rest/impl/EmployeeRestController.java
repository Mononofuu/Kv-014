package edu.softserve.zoo.controller.rest.impl;

import edu.softserve.zoo.controller.rest.AbstractRestController;
import edu.softserve.zoo.dto.EmployeeDto;
import edu.softserve.zoo.model.Employee;
import edu.softserve.zoo.service.EmployeeService;
import edu.softserve.zoo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static edu.softserve.zoo.controller.rest.Routes.EMPLOYEES;

/**
 * Employee REST controller
 *
 * @author Julia Siroshtan
 */
@RestController
@RequestMapping(EMPLOYEES)
public class EmployeeRestController extends AbstractRestController<EmployeeDto, Employee> {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeRestController() {
        super(Employee.class);
    }

    @Override
    protected Service<Employee> getService() {
        return employeeService;
    }
}
