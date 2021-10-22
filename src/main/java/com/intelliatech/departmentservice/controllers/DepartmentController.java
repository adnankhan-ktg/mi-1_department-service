package com.intelliatech.departmentservice.controllers;

import com.intelliatech.departmentservice.dtos.DepartmentDto;
import com.intelliatech.departmentservice.dtos.EmployeeDto;
import com.intelliatech.departmentservice.models.Department;
import com.intelliatech.departmentservice.services.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

     private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);

     @Autowired
     private DepartmentService departmentService;

     @Autowired
     private RestTemplate restTemplate;



     @PostMapping("/add")
       public ResponseEntity<?> addDepartment(@RequestBody DepartmentDto departmentDto)
       {
                   log.info("inside the addDepartment method of Department Controller....");

                     log.info("Object send to the DepartmentService -->service");
                    DepartmentDto departmentDto1 = this.departmentService.addDepartment(departmentDto);
                    if(departmentDto1 != null)
                    {
                         log.info("Employee object successfully saved..");
                         return ResponseEntity.status(HttpStatus.OK).body(departmentDto1);
                    }else{
                         log.info("Employee object not  persists in the database due to some issue.");
                         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }

       }

       @GetMapping("/employees/{departmentId}")
       public ResponseEntity<?> getDepartment(@PathVariable("departmentId") int departmentId)
       {
           log.info("inside getDepartment method -->Department Controller.");
           DepartmentDto departmentDto = this.departmentService.getDepartment(departmentId);

           log.info("call Employee service.....");
           List<EmployeeDto> listOfEmployee = this.restTemplate.getForObject("http://EMPLOYEE-SERVICE/employee/"+departmentId,List.class);

           //set ListOfEmployee to the departmentId corresponding.
           departmentDto.setEmployeesDto(listOfEmployee);

           log.info("return from the getDepartment method -->Department Controller ");
           return ResponseEntity.status(HttpStatus.OK).body(departmentDto);
       }
}
