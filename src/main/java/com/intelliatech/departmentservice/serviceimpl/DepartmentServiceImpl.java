package com.intelliatech.departmentservice.serviceimpl;

import com.intelliatech.departmentservice.dtos.DepartmentDto;
import com.intelliatech.departmentservice.models.Department;
import com.intelliatech.departmentservice.repositories.DepartmentRepository;
import com.intelliatech.departmentservice.services.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        log.info("inside addDepartment method-->Department Service");
        Department department = new Department();
        DepartmentDto departmentDto1 = new DepartmentDto();

        //For copy the object left to right...
        BeanUtils.copyProperties(departmentDto,department);

        log.info("object sent for the save from -->Department service");
        BeanUtils.copyProperties(this.departmentRepository.save(department),departmentDto1);
        log.info("return from || addDepartment method -->Department service");
        return departmentDto1;

    }

    @Override
    public DepartmentDto getDepartment(int departmentId) {
        log.info("inside the getDepartment method which is in the DepartmentService.");

        //make new object of departDto..
        DepartmentDto departmentDto = new DepartmentDto();

        log.info("Department object send to the dao layer.");
         BeanUtils.copyProperties(this.departmentRepository.findByDepartmentId(departmentId),departmentDto);
         log.info("return from the getDepartment method --> Department Service..");
         return departmentDto;
    }
}
