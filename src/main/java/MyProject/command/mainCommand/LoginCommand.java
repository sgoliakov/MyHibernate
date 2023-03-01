package MyProject.command.mainCommand;

import MyProject.interfaces.intefacesCommand.CommandInfo;
import MyProject.interfaces.intefacesDAO.IEmployeeDao;
import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.entity.Employee;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class LoginCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("nickName");
        String password = request.getParameter("password");
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        IEmployeeDao employeeDao = factory.getEmployeeDao();
        Optional<Employee> optional = employeeDao.getByLogin(name, password);
        if (optional.isEmpty()) {
            request.setAttribute("notUser", "This user not exists");
        } else {
            request.getSession().setAttribute("employee", optional.get());
        }
        return (optional.isEmpty()) ? "login.jsp" : "controller?action=main";
    }
}
