import MyProject.Intefaces.intefacesDAO.ScheduleDao;
import MyProject.Intefaces.intefacesDAO.overalInterfacesDAO.MyDAOFactory;
import MyProject.entity.wrapperEntity.WrapperSchedule;
import MyProject.factory.MyDAOFactoryImpl;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        ScheduleDao scheduleDao = factory.getScheduleDao();
        Optional<List<WrapperSchedule>> wrapperScheduleById = scheduleDao.getWrapperScheduleById(12);
        List<WrapperSchedule> list = wrapperScheduleById.get();
        list.forEach(System.out::println);

//        ScheduleDao scheduleDao = factory.getScheduleDao();
//        List<WrapperSchedule> wrapperScheduleById = scheduleDao.getWrapperScheduleById(4);
//        wrapperScheduleById.forEach(System.out::println);

    }
}
