package com.grandeflorum.practitioner.service;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.IService;
import com.grandeflorum.practitioner.domain.Employee;

import java.util.Map;

public interface EmployeeService extends IService<Employee> {

    int addEmployee(Employee employee);

    int modifyEmployee(Employee employee);

    int deleteEmployeeById(String id);

    Employee getEmployeeById(String id);

    ResponseBo getEmployeeList(Page page);

    boolean validationCode(Employee employee);
}
