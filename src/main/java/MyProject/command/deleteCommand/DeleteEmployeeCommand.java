package MyProject.command.deleteCommand;

import MyProject.interfaces.intefacesCommand.CommandInfo;
import MyProject.interfaces.intefacesDAO.IEmployeeDao;
import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.entity.Employee;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class DeleteEmployeeCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        IEmployeeDao employeeDao = factory.getEmployeeDao();
        int id = Integer.parseInt(request.getParameter("id"));
        Optional<Employee> optional = employeeDao.getById(id);
        if (optional.isPresent()) {
            Employee employee = optional.get();
            employeeDao.deleteByID(employee.getId());
            request.setAttribute("delete", "delete is OK");
        } else {
            request.setAttribute("delete", "delete is failed");
        }
        return "controller?action=show_employees";
    }
}