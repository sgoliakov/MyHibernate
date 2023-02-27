package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.EmployeeDao;
import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteEmployeesCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        EmployeeDao employeeDao = factory.getEmployeeDao();
        employeeDao.deleteAll();
        return "controller?action=main";
    }
}
