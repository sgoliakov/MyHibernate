package MyProject.command.mainCommand;

import MyProject.util.validators.MyValidator;
import MyProject.interfaces.intefacesCommand.CommandInfo;
import MyProject.interfaces.intefacesDAO.IEmployeeDao;
import MyProject.interfaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.entity.Employee;
import MyProject.factory.MyDAOFactoryImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RegisterCommand implements CommandInfo {
    @Override
    public String execute(HttpServletRequest request) {
        String nickName = request.getParameter("nickName");
        String lastName = request.getParameter("lastName");
        String mail = request.getParameter("mail");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        boolean isAdmin = Boolean.parseBoolean(request.getParameter("admin"));
        Employee employee = Employee.builder()
                .nickName(nickName)
                .lastName(lastName)
                .mail(mail)
                .phone(phone)
                .isAdmin(isAdmin)
                .password(password)
                .build();
        List<String> validate = MyValidator.validate(employee);
        if (!validate.isEmpty()) {
            request.setAttribute("errors", validate);
            return "register.jsp";
        }
        MyDAOFactory myDAOFactory = MyDAOFactoryImpl.getFactory();
        IEmployeeDao employeeDao = myDAOFactory.getEmployeeDao();
        Set<Employee> employees = new HashSet<>(employeeDao.getAll());
        String page;
        if (!employees.contains(employee)) {
            employeeDao.add(employee);
            request.getSession().setAttribute("employee", employee);
            page = "controller?action=main";
        } else {
            request.setAttribute("notAdd", "This employee exists");
            page = "register.jsp";
        }
        return page;
    }
}