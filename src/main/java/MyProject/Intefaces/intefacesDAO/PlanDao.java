package MyProject.Intefaces.intefacesDAO;


import MyProject.entity.Plan;

import java.util.List;
import java.util.Optional;


public interface PlanDao {

    List<Plan> getAll();
    Optional<Plan> getById(int id);
    void deleteAll();
    void createPlan();
    void removeByID(int id);
}