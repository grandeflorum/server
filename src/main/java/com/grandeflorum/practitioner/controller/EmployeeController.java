package com.grandeflorum.practitioner.controller;


import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.practitioner.domain.Employee;
import com.grandeflorum.practitioner.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("Employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getEmployeeById")
    public ResponseBo getEmployeeById(String id){
        try{
            Employee employee = employeeService.getEmployeeById(id);
            return ResponseBo.ok(employee);
        }catch (Exception e){
            return ResponseBo.error("获取从业人员失败，请联系网站管理员！");
        }
    }

    @RequestMapping("addEmployee")
    public ResponseBo addEmployee(@RequestBody Employee employee){

        if(employeeService.validationCode(employee)){
            return ResponseBo.error("存在证件号相同的从业人员!");
        }

        int result = employeeService.addEmployee(employee);

        if(result ==1){
            return ResponseBo.ok("新增从业人员成功！");
        }else{
            return ResponseBo.error("新增从业人员失败，请联系网站管理员！");
        }
    }

    @RequestMapping("modifyEmployee")
    public ResponseBo modifyEmployee(@RequestBody Employee employee){

        if(employeeService.validationCode(employee)){
            return ResponseBo.error("存在证件号相同的从业人员!");
        }

        int result = employeeService.modifyEmployee(employee);

        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    @RequestMapping("deleteEmployeeByIds")
    public ResponseBo deleteEmployeeByIds(@RequestBody List<String> ids){
        if ((ids != null) && (ids.size() > 0)) {
            for (String id : ids) {
                employeeService.deleteEmployeeById(id);
            }
            return ResponseBo.ok("删除成功!");
        }
        return ResponseBo.error("删除失败，请重试!");
    }

    @RequestMapping("getEmployeeList")
    public ResponseBo getEmployeeList(@RequestBody Page page){
        return employeeService.getEmployeeList(page);
    }

}