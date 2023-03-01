package MyProject.interfaces.intefacesDAO.overalInterfacesDAO;

import java.util.List;
import java.util.Optional;

public interface ICommon<T> {
    Optional<T> getById(int id);

    List<T> getAll();

    void deleteAll();
}