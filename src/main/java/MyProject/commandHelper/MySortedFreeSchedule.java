package MyProject.commandHelper;

import MyProject.entity.FreeSchedule;
import MyProject.entity.Schedule;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class MySortedFreeSchedule {
    public static synchronized List<FreeSchedule> getAvailableFreeSchedule(List<Schedule> mySchedule, List<FreeSchedule> freeSchedules) {
        return freeSchedules.stream()
                .filter(s -> s.getDay().getDay().isAfter(LocalDate.now()))
                .filter(s -> {
                    boolean flag = true;
                    for (Schedule schedule : mySchedule) {
                        if (s.getDay().getDay().equals(schedule.getFk().getWorkDay().getDay())) {
                            flag = false;
                            break;
                        }
                    }
                    return flag;
                })
                .sorted(Comparator.comparing(s -> s.getDay().getDay()))
                .toList();
    }
}
