package MyProject.Intefaces.intefacesDAO;

import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.CommonDAO;
import MyProject.entity.Employee;

import java.util.Optional;

public interface EmployeeDao extends CommonDAO<Employee> {
    void add(Employee emp);

    void deleteByID(int id);

    void updateByID(int id, String[] params);

    Long amountEmp();

    Optional<Employee> getByLogin(String name, String password);
}
