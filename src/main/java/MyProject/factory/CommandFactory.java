package MyProject.factory;

import MyProject.Intefaces.intefacesCommand.CommandFactoryInfo;
import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.command.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory implements CommandFactoryInfo {

    private static CommandFactoryInfo factory;
    private Map<String, CommandInfo> comands = new HashMap<>();

    private CommandFactory() {

    }

    public static synchronized CommandFactoryInfo getCommandFactory() {
        if (factory == null) {
            factory = new CommandFactory();
        }
        return factory;
    }

//добавляем в нашу мапу comands ключ-значение;// стринг в Map и будет наш action
    //мапа comands будет также содержать все реализации
   {
        comands.put("register", new RegisterCommand());
        comands.put("logout", new LogoutCommand());
        comands.put("main", new CommandMain());
        comands.put("login", new LoginCommand());
        comands.put("add_shift_into_my_schedule", new AddShiftCommand());
        comands.put("schedule_by_id", new ShowMyScheduleCommand());
        comands.put("free_schedule_shifts", new FreeScheduleShiftsCommand());

        comands.put("show_plan", new ShowPlanCommand());
        comands.put("show_employees", new ShowEmployeesCommand());
        comands.put("delete_employee", new DeleteEmployeeCommand());
        comands.put("add_new_shift", new AddNewShiftCommand());
        comands.put("remove_shift", new RemoveShiftCommand());
        comands.put("create_month_plan", new CreateMonthPlanCommand());
        comands.put("create_report", new ReportCommand());
    }

    public CommandInfo getCommandInfo(HttpServletRequest request) {
        //из запроса получим строковое значение параметра "action"
        String action = request.getParameter("action");//в запросе могут быть и другие параметры
//наша мапа содержит CommandInfo (Map<String, CommandInfo>),
// поэтому можем получить CommandInfo по ключу
//а реализацию CommandInfo будем писать в классах
        CommandInfo command = comands.get(action);
//и нам возвращаеться конкретный CommandInfo в зависимости от параметра запроса
        return command;
    }

}
