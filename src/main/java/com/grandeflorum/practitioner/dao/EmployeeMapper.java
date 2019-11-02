package com.grandeflorum.practitioner.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.practitioner.domain.Employee;
import com.grandeflorum.practitioner.domain.EmployeeList;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper extends MyMapper<Employee> {

    List<EmployeeList> getEmployeeList(Map<String,Object> map);

    List<Employee> validationCode(Map<String,Object> map);
}