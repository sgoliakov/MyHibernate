package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.EmployeeDao;
import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.entity.Employee;
import MyProject.factory.FactoryDAO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Set;

public class ShowEmployeesCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = FactoryDAO.getFactory();
        EmployeeDao employeeDao = factory.getEmployeeDao();
        Set<Employee> employees = employeeDao.getAll();
        if (employees.isEmpty()) {
            request.setAttribute("notFound", "list employees is empty");
        }
        request.setAttribute("employees", employees);
        return "employees.jsp";
    }
}
