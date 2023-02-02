package MyProject.commandHelper;

import MyProject.entity.FreeSchedule;
import MyProject.entity.Schedule;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class MySortedFreeSchedule {

    public static synchronized List<FreeSchedule> getAvailableFreeSchedule(List<Schedule> mySchedule,List<FreeSchedule> freeSchedules){

        List<FreeSchedule> list = freeSchedules.stream()
                .filter(s -> s.getDay().getDay().isAfter(LocalDate.now()))
                .filter(s -> {
                    boolean flag = true;

                    for (int i = 0; i < mySchedule.size(); i++) {
                        if (s.getDay().getDay().equals(mySchedule.get(i).getFk().getWorkDay().getDay()))
                            flag = false;
                    }

                    return flag;
                })

                 .sorted(Comparator.comparing(s -> s.getDay().getDay()))
                 .toList();
        return list;
    }

}
