package MyProject.Intefaces.intefacesDAO;

import MyProject.entity.WorkingShift;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface WorkingShiftDao {
    Optional<WorkingShift> getById(int id);
    List<WorkingShift> getAll();
    void add(WorkingShift shift);
    void update(int id, LocalTime[] params);
    void deleteByID (int id);
}
