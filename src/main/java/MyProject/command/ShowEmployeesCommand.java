package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.EmployeeDao;
import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.entity.Employee;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ShowEmployeesCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        EmployeeDao employeeDao = factory.getEmployeeDao();
        List<Employee> employees = employeeDao.getAll();
        if (employees.isEmpty()) {
            request.setAttribute("notFound", "list employees is empty");
        }
        request.setAttribute("employees", employees);
        return "employees.jsp";
    }
}
