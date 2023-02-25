import MyProject.Intefaces.intefacesDAO.EmployeeDao;
import MyProject.Intefaces.intefacesDAO.MyDAOFactory;
import MyProject.factory.MyDAOFactoryImpl;

public class Main {
    public static void main(String[] args) {

        MyDAOFactory factory = MyDAOFactoryImpl.getFactory();
        EmployeeDao employeeDao = factory.getEmployeeDao();
        Long aLong = employeeDao.amountEmp();
        System.out.println(aLong);
//        System.out.pr
//        ScheduleDao scheduleDao = factory.getScheduleDao();
//        List<WrapperSchedule> wrapperScheduleById = scheduleDao.getWrapperScheduleById(4);
//        wrapperScheduleById.forEach(System.out::println);

    }
}
