package MyProject.Intefaces.intefacesDAO;

import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.IAddition;
import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.ICommon;
import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.IDelete;
import MyProject.entity.Employee;

import java.util.Optional;

public interface IEmployeeDao extends
        ICommon<Employee>, IAddition<Employee>, IDelete {
    void updateByID(int id, String[] params);

    Long amountEmp();

    Optional<Employee> getByLogin(String name, String password);
}