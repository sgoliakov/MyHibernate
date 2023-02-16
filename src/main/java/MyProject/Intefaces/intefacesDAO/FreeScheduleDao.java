package MyProject.Intefaces.intefacesDAO;

import MyProject.entity.FreeSchedule;
import MyProject.entity.Plan;

import java.util.List;
import java.util.Optional;

public interface FreeScheduleDao {
    List<FreeSchedule> getAll();

    void createAll();

    void addFree(Plan p);

    void removeByID(int id);

    void deleteAll();

    Optional<FreeSchedule> getById(int id);

}
