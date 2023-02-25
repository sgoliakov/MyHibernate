package MyProject.Intefaces.intefacesDAO;

import MyProject.entity.WorkingShift;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface WorkingShiftDao {
    Optional<WorkingShift> getById(int id);

    List<WorkingShift> getAll();

    void update(int id, LocalTime start,LocalTime end);

    void createAll();

    void deleteAll();
}
