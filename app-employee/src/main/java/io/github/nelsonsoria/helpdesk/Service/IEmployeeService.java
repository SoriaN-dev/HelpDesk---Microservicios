package io.github.nelsonsoria.helpdesk.Service;

import io.github.nelsonsoria.helpdesk.dto.employee.EmployeeDTO;
import io.github.nelsonsoria.helpdesk.dto.employee.EmployeeSummaryDto;

import java.util.List;

public interface IEmployeeService {
    List<EmployeeDTO> getAll ();
    EmployeeDTO getEmployeeById(Long employeeId);
    EmployeeSummaryDto getEmployeeSummary(Long employeeId);
    EmployeeDTO saveEmployee(EmployeeDTO dto);

}
