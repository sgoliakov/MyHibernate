package MyProject.Intefaces.intefacesDAO;

import MyProject.entity.Employee;
import MyProject.entity.Schedule;
import MyProject.entity.WorkDays;

import java.util.List;

public interface ScheduleDao {
    List<Schedule> getById(int id);

    List<Schedule> getAll();

    void add(Schedule schedule);

    int deleteByEmployeeDate(Employee emp, WorkDays day);

    void deleteAll();
}
