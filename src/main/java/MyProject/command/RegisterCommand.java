package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.EmployeeDao;
import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.entity.Employee;
import MyProject.factory.FactoryDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Set;

public class RegisterCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String mail = request.getParameter("mail");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        boolean admin = Boolean.parseBoolean(request.getParameter("admin"));
        Employee employee = Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .mail(mail)
                .phone(phone)
                .isAdmin(admin)
                .password(password)
                .build();
        MyDAOFactory myDAOFactory = FactoryDAO.getFactory();
        EmployeeDao employeeDao = myDAOFactory.getEmployeeDao();
        Set<Employee> employees = employeeDao.getAll();
        String page;
        if (!employees.contains(employee)) {
            employeeDao.add(employee);
            HttpSession session = request.getSession();
            session.setAttribute("employee", employee);
            page = "controller?action=main";
        } else {
            request.setAttribute("notAdd", "This employee exists");
            page = "register.jsp";
        }
        return page;
    }
}