package MyProject.Intefaces.intefacesDAO;

import MyProject.entity.Employee;
import MyProject.entity.Schedule;
import MyProject.entity.WorkDays;
import MyProject.entity.wrapperEntity.WrapperSchedule;

import java.util.List;

public interface ScheduleDao {
    List<WrapperSchedule> getWrapperScheduleById(int id);

    List<Schedule> getAll();

    void add(Schedule schedule);

   int deleteByEmployeeDate(Employee emp, WorkDays day);

    void deleteAll();
}
