import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime d = LocalTime.parse("00:00",format);
        System.out.println(d);

//        System.out.pr
//        ScheduleDao scheduleDao = factory.getScheduleDao();
//        List<WrapperSchedule> wrapperScheduleById = scheduleDao.getWrapperScheduleById(4);
//        wrapperScheduleById.forEach(System.out::println);

    }
}
