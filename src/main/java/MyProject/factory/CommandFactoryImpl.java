package MyProject.factory;

import MyProject.Intefaces.intefacesCommand.CommandFactoryInfo;
import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.command.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class CommandFactoryImpl implements CommandFactoryInfo {
    private static CommandFactoryInfo factory;
    private final Map<String, CommandInfo> comands = new HashMap<>();

    private CommandFactoryImpl() {
        comands.put("register", new RegisterCommand());
        comands.put("logout", new LogoutCommand());
        comands.put("main", new CommandMain());
        comands.put("login", new LoginCommand());
        comands.put("edit", new EditProfileCommand());
        comands.put("update", new UpdateProfileCommand());
        comands.put("add_shift_into_my_schedule", new AddShiftCommand());
        comands.put("schedule_by_id", new ShowMyScheduleCommand());
        comands.put("edit_schedule", new EditScheduleEmployee());
        comands.put("delete_schedule_employee", new DeleteScheduleEmployee());
        comands.put("free_schedule_shifts", new FreeScheduleShiftsCommand());
        comands.put("show_plan", new ShowPlanCommand());
        comands.put("show_employees", new ShowEmployeesCommand());
        comands.put("delete_employee", new DeleteEmployeeCommand());
        comands.put("add_new_shift", new AddNewShiftCommand());
        comands.put("remove_shift", new RemoveShiftCommand());
        comands.put("create_month_plan", new CreateMonthPlanCommand());
        comands.put("create_report", new ReportCommand());
    }

    public static synchronized CommandFactoryInfo getCommandFactory() {
        if (factory == null) {
            factory = new CommandFactoryImpl();
        }
        return factory;
    }

    public CommandInfo getCommandInfo(HttpServletRequest request) {
        String action = request.getParameter("action");
        return comands.get(action);
    }
}
