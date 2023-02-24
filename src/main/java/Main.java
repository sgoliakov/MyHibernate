import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.Intefaces.intefacesDAO.WorkDaysDao;
import MyProject.factory.MyDAOFactoryImpl;

public class Main {
    public static void main(String[] args) {
        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        WorkDaysDao workDaysDao = factory.getWorkDaysDao();
//        WorkDays build = WorkDays.builder()
//                .day(LocalDate.of(2024,1,1))
//                .build();
       // workDaysDao.delete(61);

//        System.out.pr
//        ScheduleDao scheduleDao = factory.getScheduleDao();
//        List<WrapperSchedule> wrapperScheduleById = scheduleDao.getWrapperScheduleById(4);
//        wrapperScheduleById.forEach(System.out::println);

    }
}
