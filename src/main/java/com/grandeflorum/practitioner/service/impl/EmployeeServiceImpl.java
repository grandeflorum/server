package com.grandeflorum.practitioner.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.attachment.service.FileInfoService;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.common.util.StrUtil;
import com.grandeflorum.practitioner.dao.EmployeeMapper;
import com.grandeflorum.practitioner.domain.Employee;
import com.grandeflorum.practitioner.domain.EmployeeList;
import com.grandeflorum.practitioner.service.EmployeeService;
import com.grandeflorum.system.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("employeeService")
public class EmployeeServiceImpl extends BaseService<Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Autowired
    SystemUserService systemUserService;

    @Override
    public int addEmployee(Employee employee) {
        employee.setId(GuidHelper.getGuid());
        employee.setSysDate(new Date());
        employee.setSysUpdDate(new Date());

        try{
            fileInfoService.updateFileInfoByIds(employee.getFileInfoList(),employee.getId());
            employeeMapper.insert(employee);
            return 1;
        }catch (Exception e){
            return 0;
        }

    }

    @Override
    public int modifyEmployee(Employee employee) {
        employee.setSysUpdDate(new Date());

        try{
            employeeMapper.updateByPrimaryKey(employee);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public int deleteEmployeeById(String id) {
        try{
            employeeMapper.deleteByPrimaryKey(id);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public Employee getEmployeeById(String id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResponseBo getEmployeeList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();

        //获取过滤条件
        systemUserService.getSelectInfo(map);

        List<EmployeeList> list = employeeMapper.getEmployeeList(map);

        PageInfo<EmployeeList> pageInfo = new PageInfo<EmployeeList>(list);

        PagingEntity<EmployeeList> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public boolean validationCode(Employee employee) {
        List<Employee> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();

        if(!StrUtil.isNullOrEmpty(employee.getZjh())){
            map.put("zjlb",employee.getZjlb());
            map.put("zjh",employee.getZjh());
            map.put("id",employee.getId());
            list = employeeMapper.validationCode(map);
        }
        return list != null && list.size() > 0;
    }
}
