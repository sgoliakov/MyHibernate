package MyProject.commandHelper;

import MyProject.entity.FreeSchedule;
import MyProject.entity.wrapperEntity.WrapperSchedule;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class MySortedFreeSchedule {
    public static synchronized List<FreeSchedule> getAvailableFreeSchedule(List<WrapperSchedule> mySchedule, List<FreeSchedule> freeSchedules) {
        return freeSchedules.stream()
                .filter(s -> s.getDay().getDay().isAfter(LocalDate.now()))
                .filter(s -> {
                    boolean flag = true;
                    for (WrapperSchedule schedule : mySchedule) {
                        if (s.getDay().getDay().equals(schedule.getDay().getDay())) {
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
