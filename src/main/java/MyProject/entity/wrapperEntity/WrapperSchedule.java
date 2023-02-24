package MyProject.entity.wrapperEntity;

import MyProject.entity.WorkDays;
import MyProject.entity.WorkingShift;
import lombok.ToString;

@ToString
public class WrapperSchedule {
    private WorkingShift shift;
    private WorkDays day;

    public WrapperSchedule(WorkingShift shift, WorkDays day) {
        this.day = day;
        this.shift = shift;
    }

    public WorkDays getDay() {
        return day;
    }

    public WorkingShift getShift() {
        return shift;
    }
}
