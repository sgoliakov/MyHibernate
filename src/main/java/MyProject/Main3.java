package MyProject;

import MyProject.Intefaces.intefacesDAO.*;
import MyProject.entity.Employee;
import MyProject.entity.FreeSchedule;
import MyProject.entity.Plan;
import MyProject.entity.Schedule;
import MyProject.factory.FactoryDAO;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main3 {
    public static void main(String[] args) {

//        MyDAOFactory factory = FactoryDAO.getFactory();
//
//        EmployeeDao employeeDao = factory.getEmployeeDao();
//        WorkDaysDao workDaysDao = factory.getWorkDaysDao();
//        ScheduleDao scheduleDao = factory.getScheduleDao();
//        WorkingShiftDao workingShiftDao = factory.getWorkingShiftDao();
//        FreeScheduleDao freeScheduleDao = factory.getFreeScheduleDao();
//        PlanDao planDao = factory.getPlanDao();
//
//        scheduleDao.deleteAll();
//        freeScheduleDao.deleteAll();
//        planDao.deleteAll();
//        workDaysDao.deleteAll();
//
//        workDaysDao.createFromDate(LocalDate.now());
//        freeScheduleDao.createAll();
//        planDao.createPlan();
//




        /** важно!пример работы стрима */
//        List<Schedule> completes = scheduleDao.getAll();
//                 completes.stream()
//                .sorted((Comparator.comparing(p -> p.getFk().getWorkDay())))
//                .forEach(System.out::println);


    }
}
