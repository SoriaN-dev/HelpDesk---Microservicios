package io.github.nelsonsoria.helpdesk.dto.employee;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDTO {

    private String name;

    private String email;



}
