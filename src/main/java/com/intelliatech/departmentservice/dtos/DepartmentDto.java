package com.intelliatech.departmentservice.dtos;

import com.intelliatech.departmentservice.models.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private int departmentId;
    private String departmentName;
    private String departmentType;
    private List<EmployeeDto> employeesDto;
}
