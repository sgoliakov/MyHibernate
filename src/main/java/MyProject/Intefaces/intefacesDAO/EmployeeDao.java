package MyProject.Intefaces.intefacesDAO;

import MyProject.entity.Employee;

import java.util.Optional;
import java.util.Set;

public interface EmployeeDao {
    Optional<Employee> getById(int id);

    Set<Employee> getAll();

    void add(Employee emp);

    void updateByID(int id, String[] params);

    void deleteByID(int id);

    void deleteByName(String nickName, String lastName);

    Long amountEmp();

    Optional<Employee> getByLogin(String name, String password);
}
