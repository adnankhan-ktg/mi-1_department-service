package com.intelliatech.departmentservice.services;

import com.intelliatech.departmentservice.dtos.DepartmentDto;

public interface DepartmentService {
    DepartmentDto addDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartment(int departmentId);
}
