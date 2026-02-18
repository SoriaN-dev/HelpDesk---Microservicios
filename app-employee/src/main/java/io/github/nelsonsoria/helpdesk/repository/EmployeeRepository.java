package io.github.nelsonsoria.helpdesk.repository;

import io.github.nelsonsoria.helpdesk.db.Employee;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;


@ApplicationScoped
@Transactional
public class EmployeeRepository implements PanacheRepositoryBase<Employee,Long> {


}
