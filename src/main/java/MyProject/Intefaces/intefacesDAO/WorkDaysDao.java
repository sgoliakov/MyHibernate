package MyProject.Intefaces.intefacesDAO;

import MyProject.entity.WorkDays;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WorkDaysDao {
    List<WorkDays> getAll();

    void add(WorkDays day);

    Optional<WorkDays> getById(int id);

    void delete(int id);

    void deleteAll();

    void createFromDate(LocalDate date);
}