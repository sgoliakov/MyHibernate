package MyProject.command.showCommand;

import MyProject.interfaces.intefacesCommand.CommandInfo;
import MyProject.interfaces.intefacesDAO.IEmployeeDao;
import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.entity.Employee;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ShowEmployeesCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        IEmployeeDao employeeDao = factory.getEmployeeDao();
        List<Employee> employees = employeeDao.getAll();
        if (employees.isEmpty()) {
            request.setAttribute("notFound", "list employees is empty");
        }
        request.setAttribute("employees", employees);
        return "employees.jsp";
    }
}
