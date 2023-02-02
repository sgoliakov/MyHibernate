package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.EmployeeDao;
import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.entity.Employee;
import MyProject.factory.FactoryDAO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class LoginCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("firstName");
        String password = request.getParameter("password");

        MyDAOFactory factory = FactoryDAO.getFactory();
        EmployeeDao employeeDao= factory.getEmployeeDao();

       Optional<Employee> optional = employeeDao.getByLogin(name, password);

        if (optional.isEmpty()) {
            request.setAttribute("notExists", "This user not exists");
        } else {
            request.getSession().setAttribute("employee", optional.get());
        }
        String resultPage = (optional.isEmpty()) ? "login.jsp" : "controller?action=main";

        return resultPage;
    }
}
