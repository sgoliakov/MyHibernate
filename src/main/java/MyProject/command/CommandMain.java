package MyProject.command;

import MyProject.Intefaces.intefacesCommand.CommandInfo;
import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.Intefaces.intefacesDAO.WorkDaysDao;
import MyProject.commandHelper.MySortedFreeSchedule;
import MyProject.entity.Employee;
import MyProject.entity.FreeSchedule;
import MyProject.entity.Schedule;
import MyProject.entity.WorkDays;
import MyProject.factory.FactoryDAO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class CommandMain implements CommandInfo {

    @Override
    public String execute(HttpServletRequest request) {
        MyDAOFactory myDAOFactory = FactoryDAO.getFactory();
//получаем Работника из сессии
        Employee employee = (Employee) request.getSession().getAttribute("employee");
//если работник не пустой то мы назначаем в атрибут тот функционал что надо
        if (employee != null){//
        //например список его смен и способ дабавить новую и плюс посмотреть какие доступные
            List<FreeSchedule> allFreeSchedules = myDAOFactory.getFreeScheduleDao().getAll();
            List<Schedule> schedules = myDAOFactory.getScheduleDao().getById(employee.getId());
            List<FreeSchedule> freeSchedules = MySortedFreeSchedule.getAvailableFreeSchedule(schedules, allFreeSchedules);
            request.setAttribute("freeSchedules", freeSchedules);

        }else //если null то например список всех дней
        {
            WorkDaysDao workDaysDao = myDAOFactory.getWorkDaysDao();
            List<WorkDays> days = workDaysDao.getAll();
            request.setAttribute("workDays",days);
        }


        return "main.jsp";
    }
}
